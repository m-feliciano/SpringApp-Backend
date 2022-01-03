package com.feliciano.demo.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.feliciano.demo.resources.domain.enums.Perfil;

import lombok.Getter;

public class SpringSecurityUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Getter
	private Integer id;
	@Getter
	private String email;
	@Getter
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public SpringSecurityUser() {
		super();
	}

	public SpringSecurityUser(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.password = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescription()))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescription()));
	}

	@Override
	public String getUsername() {
		return email;
	}
}
