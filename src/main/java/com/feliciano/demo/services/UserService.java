package com.feliciano.demo.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.feliciano.demo.security.SpringSecurityUser;

public class UserService {

	public static SpringSecurityUser authenticated() {
		try {
			return (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
