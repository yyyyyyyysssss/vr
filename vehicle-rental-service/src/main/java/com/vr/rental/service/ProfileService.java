package com.vr.rental.service;

import com.vr.rental.domain.dto.ChangePasswordDTO;
import com.vr.rental.domain.vo.UserInfoVO;

public interface ProfileService {

    UserInfoVO userInfo(Long userId);

    Boolean changePassword(Long userId, ChangePasswordDTO changePasswordDTO);

    Boolean changeAvatar(Long userId, String avatarUrl);

}
