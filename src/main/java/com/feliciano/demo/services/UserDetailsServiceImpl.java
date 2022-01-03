package com.feliciano.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.feliciano.demo.repositories.ClientRepository;
import com.feliciano.demo.resources.domain.Client;
import com.feliciano.demo.security.SpringSecurityUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = repo.findByEmail(email);

		if (client == null) {
			throw new UsernameNotFoundException(email);
		}

		return new SpringSecurityUser(client.getId(), client.getEmail(), client.getPassword(), client.getPerfils());
	}

}
