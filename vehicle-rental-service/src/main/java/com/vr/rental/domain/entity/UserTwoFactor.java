package com.vr.rental.domain.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vr.rental.enums.TwoFactorType;
import lombok.Getter;
import lombok.Setter;

@TableName("vr_user_2fa")
@Getter
@Setter
public class UserTwoFactor extends BaseIdEntity {

    @TableField("user_id")
    private Long userId;

    @TableField("username")
    private String username;

    @EnumValue
    private TwoFactorType type;

    @TableField("secret")
    private String secret;

    @TableField("enabled")
    private Boolean enabled;

}
