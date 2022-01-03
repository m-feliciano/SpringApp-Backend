package com.feliciano.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feliciano.demo.dto.CredentialsDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final JWTUtil jwtUtil;
	private final AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
		super();
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			CredentialsDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredentialsDTO.class); // convert
																												// from
																												// request
																												// data
																												// to
																												// credenciais.class

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),
					creds.getPassword(), new ArrayList<>());

			return authenticationManager.authenticate(authToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((SpringSecurityUser) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		res.addHeader("Authorization", "Bearer " + token);
		res.addHeader("access-control-expose-headers", "Authorization");
	}

	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException exception) throws IOException, ServletException {
		res.setStatus(401);
		res.setContentType("application/json");
		res.getWriter().append(json());
	}

	private String json() {
		long date = new Date().getTime();
		return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"\", "
				+ "\"message\": \"Email or password invalid\", " + "\"path\": \"/login\"}";
	}
}