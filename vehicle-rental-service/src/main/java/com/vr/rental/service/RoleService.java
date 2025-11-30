package com.vr.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.vr.rental.domain.dto.RoleCreateDTO;
import com.vr.rental.domain.dto.RoleQueryDTO;
import com.vr.rental.domain.dto.RoleUpdateDTO;
import com.vr.rental.domain.entity.Role;
import com.vr.rental.domain.vo.RoleVO;

import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Author ys
 * @Date 2025/6/3 15:36
 */
public interface RoleService extends IService<Role> {

    Long createRole(RoleCreateDTO roleCreateDTO);

    Integer updateRole(RoleUpdateDTO roleUpdateDTO, Boolean isFullUpdate);

    PageInfo<RoleVO> queryList(RoleQueryDTO queryDTO);

    RoleVO details(Long id);

    Boolean deleteRole(Long roleId);

    Boolean bindRoleAuthorities(Long roleId, List<Long> authorityIds);

    Boolean unbindRoleAuthorities(Long roleId);

    Boolean unbindAuthorityRole(Long authorityId);

    Boolean unbindAuthorityRole(Collection<Long> authorityIds);

    Boolean bindRoleUsers(Long roleId, List<Long> userIds);

    Boolean unbindRoleUsers(Long roleId);

    List<RoleVO> findByUserId(Long userId);

    Boolean bindUserRole(Long userId, Collection<Long> roleIds);

    Boolean unbindUserRoles(Long userId);

    List<RoleVO> listRoleOptions();

}
