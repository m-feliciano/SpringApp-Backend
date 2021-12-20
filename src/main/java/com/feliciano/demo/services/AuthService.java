package com.feliciano.demo.services;

import com.feliciano.demo.repositories.ClienteRepository;
import com.feliciano.demo.resources.domain.Cliente;
import com.feliciano.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email){
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw  new ObjectNotFoundException("Email não encontrado!");
        }

        String newPass = newPassword();
        cliente.setSenha(passwordEncoder.encode(newPass));

        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];

        for (int i = 0; i< 10; i++){
            vet[i] = ramdomChar();
        }
        return new String(vet);
    }

    private char ramdomChar() {
        int opt = rand.nextInt(3);
        if(opt ==0){ //gera um digito (unicode: 48, 57)
            return (char) (rand.nextInt(10)+48); // numero aleatorio entre 0-10 + unicode
        } else if(opt ==1){ //letra maiuscula (65 - 91)
            return (char) (rand.nextInt(26)+65);
        } else{ //letra minuscula
            return (char) (rand.nextInt(26)+97);
        }
    }
}
