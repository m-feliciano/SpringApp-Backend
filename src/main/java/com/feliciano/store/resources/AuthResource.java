package com.feliciano.store.resources;

import com.feliciano.store.dto.EmailDTO;
import com.feliciano.store.resources.openapi.AuthResourceOpenApi;
import com.feliciano.store.security.JWTUtil;
import com.feliciano.store.security.SpringSecurityUser;
import com.feliciano.store.services.AuthService;
import com.feliciano.store.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthResource implements AuthResourceOpenApi {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		SpringSecurityUser user = UserService.authenticated();

        String token = null;
        if (user != null) token = jwtUtil.generateToken(user.getUsername());

        response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO dto) {
		authService.sendNewPassword(dto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
