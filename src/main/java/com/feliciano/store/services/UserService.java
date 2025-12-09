package com.feliciano.store.services;

import com.feliciano.store.security.SpringSecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public static SpringSecurityUser authenticated() {
		try {
			return (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
