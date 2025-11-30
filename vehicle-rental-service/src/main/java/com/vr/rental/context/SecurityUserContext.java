package com.vr.rental.context;

import com.vr.common.core.context.UserContext;
import com.vr.rental.domain.entity.User;
import com.vr.rental.utils.SecurityUtils;

public class SecurityUserContext implements UserContext {

    @Override
    public Long getUserId() {
        return SecurityUtils.currentUser(User::getId);
    }

    @Override
    public String getUsername() {
        return SecurityUtils.currentUser(User::getUsername);
    }

    @Override
    public String getFullName() {
        return SecurityUtils.currentUser(User::getFullName);
    }
}
