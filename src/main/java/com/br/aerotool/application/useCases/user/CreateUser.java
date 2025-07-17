package com.br.aerotool.application.useCases.user;

import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserRequest;

import java.util.Objects;

public class CreateUser {
    private final IUserRepository userRepository;

    public CreateUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void create(String prontuario, String password, String name, String email){
        Objects.requireNonNull(prontuario, "prontuario must not be null");
        Objects.requireNonNull(password, "password must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(email, "email must not be null");

        if(prontuario.isBlank()) throw new IllegalArgumentException("prontuario must not be blank");
        if(password.isBlank()) throw new IllegalArgumentException("password must not be blank");
        if(name.isBlank()) throw new IllegalArgumentException("name must not be blank");
        if(email.isBlank()) throw new IllegalArgumentException("email must not be blank");

        userRepository.create(new UserRequest(prontuario, password, name, email));
    }
}
