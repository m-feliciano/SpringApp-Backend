package com.feliciano.demo.services;

import com.feliciano.demo.security.SpringSecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static SpringSecurityUser authenticated() {
        try {
            return (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
