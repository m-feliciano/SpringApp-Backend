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
import com.feliciano.demo.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	private JWTUtil jwtUtil;
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
		super();
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			CredenciaisDTO creds = new ObjectMapper()
	                .readValue(req.getInputStream(), CredenciaisDTO.class); //convert from request data to credenciais.class
	
	        UsernamePasswordAuthenticationToken authToken = 
	        		new UsernamePasswordAuthenticationToken(
	        				creds.getEmail(), creds.getSenha(), new ArrayList<>());
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
			String username = ((SpringSecurityUser) auth.getPrincipal()).getUsername();
	        String token = jwtUtil.generateToken(username);
	        res.addHeader("Authorization", "Bearer " + token);
	}
	
	 public void onAuthenticationFailure(HttpServletRequest req, 
			 HttpServletResponse res, AuthenticationException exception)
             throws IOException, ServletException {
         res.setStatus(401);
         res.setContentType("application/json"); 
         res.getWriter().append(json());
     }
     
     private String json() {
         long date = new Date().getTime();
         return "{\"timestamp\": " + date + ", "
             + "\"status\": 401, "
             + "\"error\": \"Não autorizado\", "
             + "\"message\": \"Email ou senha inválidos\", "
             + "\"path\": \"/login\"}";
     }
}