package com.vr.rental.controller;

import com.vr.common.core.response.Result;
import com.vr.common.core.response.ResultGenerator;
import com.vr.rental.domain.vo.AuthorityVO;
import com.vr.rental.domain.vo.RoleVO;
import com.vr.rental.domain.vo.UserVO;
import com.vr.rental.service.AuthenticatedService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 对外提供需要身份认证无需授权的接口
 * @Author ys
 * @Date 2025/6/11 11:41
 */
@RestController
@RequestMapping("/api/authenticated")
public class AuthenticatedController {

    @Resource
    private AuthenticatedService authenticatedService;

    @GetMapping("/user/options")
    public Result<?> userOptions() {
        List<UserVO> userOptions = authenticatedService.listUserOptions();
        return ResultGenerator.ok(userOptions);
    }

    @GetMapping("/role/options")
    public Result<?> roleOptions() {
        List<RoleVO> roleVOS = authenticatedService.listRoleOptions();
        return ResultGenerator.ok(roleVOS);
    }

    @GetMapping("/authority/options")
    public Result<?> authorityOptions() {
        List<AuthorityVO> authorityVOList = authenticatedService.treeAuthorityOptions();
        return ResultGenerator.ok(authorityVOList);
    }


}
