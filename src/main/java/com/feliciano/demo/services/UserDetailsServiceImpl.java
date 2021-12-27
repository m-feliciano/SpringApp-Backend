package com.feliciano.demo.services;

import com.feliciano.demo.repositories.ClienteRepository;
import com.feliciano.demo.resources.domain.Cliente;
import com.feliciano.demo.security.SpringSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = repo.findByEmail(email);

        if (cliente == null) {
            throw new UsernameNotFoundException(email);
        }

        return new SpringSecurityUser(cliente.getId(), cliente.getEmail(),
                cliente.getSenha(), cliente.getPerfils());
    }

}
