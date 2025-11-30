package com.vr.rental.utils;

import com.vr.rental.domain.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.function.Function;

/**
 * @Description
 * @Author ys
 * @Date 2025/10/16 15:36
 */
public class SecurityUtils {

    private SecurityUtils() {}

    public static User currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                return (User) principal;
            }
        }
        return null;
    }

    public static <T> T currentUser(Function<User, T> function) {
        User user = currentUser();
        return user != null ? function.apply(user) : null;
    }

}
