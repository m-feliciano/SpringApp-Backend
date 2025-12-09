package com.feliciano.store.services;

import com.feliciano.store.repositories.ClientRepository;
import com.feliciano.store.resources.domain.Client;
import com.feliciano.store.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

	private final Random rand = new Random();
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public AuthService(ClientRepository clientRepository,
                       PasswordEncoder passwordEncoder,
                       EmailService emailService) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
        if (client == null) throw new ObjectNotFoundException("Email not found");

		String newPass = newPassword();
		client.setPassword(passwordEncoder.encode(newPass));

		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];

		for (int i = 0; i < 10; i++) {
			vet[i] = ramdomChar();
		}
		return new String(vet);
	}

	private char ramdomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito (unicode: 48, 57)
			return (char) (rand.nextInt(10) + 48); // numero aleatorio entre 0-10 + unicode
		} else if (opt == 1) { // letra maiuscula (65 - 91)
			return (char) (rand.nextInt(26) + 65);
		} else { // letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
