package com.vr.rental.domain.vo;

import com.vr.rental.config.jackson.Sensitive;
import com.vr.rental.config.jackson.SensitiveType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description
 * @Author ys
 * @Date 2025/5/16 23:38
 */
@Getter
@Setter
public class UserVO {

    private Long id;

    private String username;

    private String fullName;

    private Boolean enabled;

    private String avatar;

    @Sensitive(SensitiveType.EMAIL)
    private String email;

    @Sensitive(SensitiveType.MOBILE)
    private String phone;

    private String createTime;

    private String updateTime;

    private List<Long> roleIds;

}
