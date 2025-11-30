package com.vr.rental.controller;

import com.vr.rental.enums.ClientType;
import com.vr.rental.config.security.authentication.email.EmailAuthenticationToken;
import com.vr.rental.config.security.authentication.refreshtoken.RefreshAuthenticationToken;
import com.vr.rental.domain.dto.LoginDTO;
import com.vr.rental.domain.entity.TokenInfo;
import com.vr.rental.enums.LoginType;
import com.vr.rental.service.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import com.vr.common.core.response.Result;
import com.vr.common.core.response.ResultGenerator;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author ys
 * @Date 2024/7/15 9:26
 */
@RestController
@Slf4j
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> login(@RequestBody @Validated LoginDTO loginDTO) {
        TokenInfo tokenInfo;
        switch (loginDTO.getLoginType()) {
            case NORMAL:
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getCredential());
                tokenInfo = loginService.login(authenticationToken, loginDTO.rememberMe(), loginDTO.getClientType());
                break;
            case EMAIL:
                EmailAuthenticationToken emailAuthenticationToken = new EmailAuthenticationToken(loginDTO.getUsername(), loginDTO.getCredential());
                tokenInfo = loginService.login(emailAuthenticationToken, loginDTO.rememberMe(), loginDTO.getClientType());
                break;
            case OTT:
                OneTimeTokenAuthenticationToken oneTimeTokenAuthenticationToken = new OneTimeTokenAuthenticationToken(loginDTO.getCredential());
                tokenInfo = loginService.login(oneTimeTokenAuthenticationToken, loginDTO.rememberMe(), loginDTO.getClientType());
                break;
            default:
                throw new UnsupportedOperationException("不支持的登录方式:" + loginDTO.getLoginType());
        }
        if (tokenInfo == null) {
            throw new BadCredentialsException("Bad Credentials");
        }
        return ResultGenerator.ok(tokenInfo);
    }

    @GetMapping("/login/ott")
    public Result<?> login(@RequestParam("ottToken") String ottToken, @RequestParam(value = "clientType", required = false) ClientType clientType) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setLoginType(LoginType.OTT);
        loginDTO.setCredential(ottToken);
        if (clientType == null) {
            clientType = ClientType.WEB;
        }
        loginDTO.setClientType(clientType);
        return login(loginDTO);
    }


    @GetMapping("/refreshToken")
    public Result<?> refreshToken() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        TokenInfo tokenInfo = null;
        if (authentication instanceof RefreshAuthenticationToken refreshAuthenticationToken) {
            tokenInfo = loginService.login(authentication, false, refreshAuthenticationToken.getClientType());
        }
        if (tokenInfo == null) {
            throw new BadCredentialsException("Bad Credentials");
        }
        return ResultGenerator.ok(tokenInfo);
    }

}
