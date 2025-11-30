package com.vr.rental.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vr.rental.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description
 * @Author ys
 * @Date 2025/6/3 15:41
 */
@Getter
@Setter
public class RoleVO {

    private Long id;

    private String code;

    private String name;

    private Boolean enabled;

    private RoleType type;

    private String createTime;

    private String updateTime;

    private List<Long> userIds;

    private List<Long> authorityIds;

    @JsonIgnore
    public boolean isSuperAdmin() {
        return RoleType.SUPER_ADMIN.equals(this.type);
    }

}
