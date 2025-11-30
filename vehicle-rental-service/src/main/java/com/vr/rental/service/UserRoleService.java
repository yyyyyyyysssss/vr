package com.vr.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vr.rental.domain.entity.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {


    List<Long> findRoleIdByUserId(Long userId);

    List<Long> findUserIdByRoleId(Long roleId);

}
