package com.vr.rental.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vr.rental.config.exception.BusinessException;
import com.vr.rental.config.idwork.IdGen;
import com.vr.common.redis.utils.RedisHelper;
import com.vr.rental.config.security.SecurityProperties;
import com.vr.rental.domain.entity.User;
import com.vr.rental.domain.entity.UserTwoFactor;
import com.vr.rental.enums.TwoFactorType;
import com.vr.rental.mapper.UserTwoFactorMapper;
import com.vr.rental.service.TotpService;
import com.vr.rental.service.TwoFactorService;
import com.vr.common.core.utils.AesUtils;
import com.vr.rental.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.Duration;

@Service
@Slf4j
public class TwoFactorServiceImpl extends ServiceImpl<UserTwoFactorMapper, UserTwoFactor> implements TwoFactorService {

    @Resource
    private TotpService totpService;

    @Resource
    private RedisHelper redisHelper;

    @Resource
    private UserTwoFactorMapper userTwoFactorMapper;

    @Resource
    private SecurityProperties securityProperties;

    private final String totp_temp_key_prefix = "totp:temp_secret:";

    @Override
    public String totpSetup(String account) {
        UserTwoFactor record = this.lambdaQuery()
                .select(UserTwoFactor::getSecret)
                .eq(UserTwoFactor::getUsername, account)
                .eq(UserTwoFactor::getType, TwoFactorType.TOTP)
                .one();
        String secret;
        if(record != null){
            try {
                secret = AesUtils.decrypt(
                        record.getSecret(),
                        securityProperties.getTotp().getSecretKey()
                );
            } catch (Exception e) {
                throw new BusinessException("二次认证TOTP解密失败");
            }
        } else {
            secret = totpService.createSecret();
            // 暂存secret，验证成功后再写入数据库
            redisHelper.setValue(totp_temp_key_prefix + account, secret, Duration.ofMinutes(10)); // 10分钟过期
        }
        return totpService.buildOtpAuthUrl(account, secret);
    }

    @Override
    public BufferedImage totpSetupQrcode(String account) {
        String otpAuthUrl = totpSetup(account);
        return totpService.imageQrcode(otpAuthUrl);
    }

    @Override
    public boolean totpVerify(String account, String code) {
        // 1. 查询用户的 TOTP 记录
        UserTwoFactor record = this.lambdaQuery()
                .select(UserTwoFactor::getId, UserTwoFactor::getSecret, UserTwoFactor::getEnabled)
                .eq(UserTwoFactor::getUsername, account)
                .eq(UserTwoFactor::getType, TwoFactorType.TOTP)
                .one();
        String secret;
        if(record != null){
            try {
                secret = AesUtils.decrypt(
                        record.getSecret(),
                        securityProperties.getTotp().getSecretKey()
                );
            } catch (Exception e) {
                throw new BusinessException("二次认证TOTP密钥解密失败");
            }
        } else {
            secret = (String) redisHelper.getValue(totp_temp_key_prefix + account);
        }
        if (secret == null) {
            throw new BusinessException("二次认证TOTP密钥已过期或不存在");
        }
        int totpCode = Integer.parseInt(code);
        boolean ok = totpVerify(secret, totpCode);
        if (!ok) {
            throw new BusinessException("二次认证TOTP令牌不正确");
        }
        // 已存在则直接开启
        if(record != null){
            return this.lambdaUpdate()
                    .eq(UserTwoFactor::getId, record.getId())
                    .set(UserTwoFactor::getEnabled, true)
                    .update();
        }
        // secret写入数据库
        UserTwoFactor userMfa = new UserTwoFactor();
        userMfa.setId(IdGen.genId());
        userMfa.setUserId(SecurityUtils.currentUser(User::getId));
        userMfa.setUsername(account);
        userMfa.setType(TwoFactorType.TOTP);
        try {
            userMfa.setSecret(AesUtils.encrypt(secret, securityProperties.getTotp().getSecretKey()));
        } catch (Exception e) {
            log.error("totpVerify error:", e);
            throw new BusinessException("二次认证TOTP密钥加密失败");
        }
        userMfa.setEnabled(true);
        int i = userTwoFactorMapper.insert(userMfa);
        if (i <= 0) {
            throw new BusinessException("二次认证TOTP保存失败");
        }
        // 移除缓存的 secret
        redisHelper.delete(totp_temp_key_prefix + account);
        return true;
    }

    @Override
    public boolean enableTotp(String account, String code) {

        return toggleTotp(account, code, true);
    }

    @Override
    public boolean disableTotp(String account, String code) {

        return toggleTotp(account, code, false);
    }

    private boolean toggleTotp(String account, String code, boolean enable) {
        // 1. 查询用户的 TOTP 记录
        UserTwoFactor record = this.lambdaQuery()
                .select(UserTwoFactor::getId, UserTwoFactor::getSecret, UserTwoFactor::getEnabled)
                .eq(UserTwoFactor::getUsername, account)
                .eq(UserTwoFactor::getType, TwoFactorType.TOTP)
                .one();

        if (record == null) {
            throw new BusinessException("二次认证账号未开启TOTP认证");
        }
        // 状态一致，无需处理
        if (Boolean.valueOf(enable).equals(record.getEnabled())) {
            return true;
        }
        // 2. 校验 TOTP 动态码格式
        int totpCode = Integer.parseInt(code);
        String secret;
        try {
            // 3. 解密 secret
            secret = AesUtils.decrypt(
                    record.getSecret(),
                    securityProperties.getTotp().getSecretKey()
            );
        } catch (Exception e) {
            log.error("toggleTotp error: account={}", account, e);
            throw new BusinessException("二次认证TOTP解密异常");
        }
        // 4. 验证 TOTP 是否正确
        if (!totpVerify(secret, totpCode)) {
            throw new BusinessException("二次认证TOTP令牌不正确");
        }
        // 5. 更新状态
        boolean updated = this.lambdaUpdate()
                .eq(UserTwoFactor::getId, record.getId())
                .set(UserTwoFactor::getEnabled, enable)
                .update();
        if (!updated) {
            throw new BusinessException("二次认证TOTO更新失败");
        }
        log.info("TOTP {} success, account={}", enable ? "enabled" : "disabled", account);
        return true;
    }

    private boolean totpVerify(String secret, int code) {

        return totpService.verifyCode(secret, code);
    }

}
