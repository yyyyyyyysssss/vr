package com.vr.rental.service;

import com.vr.rental.domain.vo.AuthorityVO;
import com.vr.rental.domain.vo.RoleVO;
import com.vr.rental.domain.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author ys
 * @Date 2025/6/11 13:06
 */
@Service
public class AuthenticatedService {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private AuthorityService authorityService;

    public List<UserVO> listUserOptions() {

        return userService.listUserOptions();
    }

    public List<RoleVO> listRoleOptions() {
        return roleService.listRoleOptions();
    }

    public List<AuthorityVO> treeAuthorityOptions(){

        return authorityService.tree();
    }


}
