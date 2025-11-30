package com.vr.rental.controller;

import com.vr.rental.domain.dto.ChangeAvatarDTO;
import com.vr.rental.domain.dto.ChangePasswordDTO;
import com.vr.rental.domain.entity.User;
import com.vr.rental.domain.vo.UserInfoVO;
import com.vr.rental.service.ProfileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import com.vr.common.core.response.Result;
import com.vr.common.core.response.ResultGenerator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 当前登录用户的个人信息管理控制器
 * @Author ys
 * @Date 2025/5/19 11:38
 */
@RequestMapping("/api/profile")
@RestController
@Slf4j
public class ProfileController extends BaseController {

    @Resource
    private ProfileService profileService;

    @GetMapping("/user/info")
    public Result<?> userInfo() {
        Long userId = getCurrentUser(User::getId);
        UserInfoVO userInfoVO = profileService.userInfo(userId);
        return ResultGenerator.ok(userInfoVO);
    }

    @PutMapping("/password")
    public Result<?> changePassword(@RequestBody @Validated ChangePasswordDTO changePasswordDTO) {
        Long userId = getCurrentUser(User::getId);
        Boolean b = profileService.changePassword(userId, changePasswordDTO);
        return ResultGenerator.ok(b);
    }

    @PutMapping("/avatar")
    public Result<?> changeAvatar(@RequestBody @Validated ChangeAvatarDTO changeAvatarDTO) {
        Long userId = getCurrentUser(User::getId);
        Boolean b = profileService.changeAvatar(userId, changeAvatarDTO.getNewAvatarUrl());
        return ResultGenerator.ok(b);
    }

}
