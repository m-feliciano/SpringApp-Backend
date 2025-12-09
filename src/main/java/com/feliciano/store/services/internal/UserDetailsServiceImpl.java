package com.feliciano.store.services.internal;

import com.feliciano.store.repositories.ClientRepository;
import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.security.SpringSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository repository;

    @Autowired
    public UserDetailsServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = repository.findByEmail(email);
        if (client == null) throw new UsernameNotFoundException(email);
		return new SpringSecurityUser(client.getId(), client.getEmail(), client.getPassword(), client.getPerfils());
	}

}
