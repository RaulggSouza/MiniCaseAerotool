package com.br.aerotool.application.useCases.user;

import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserRequest;
import org.springframework.stereotype.Service;

import static java.util.Map.entry;

@Service
public class CreateUser {
    private final IUserRepository userRepository;

    public CreateUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void create(String prontuario, String password, String name, String email, String document){
        InputUtils.notBlank(
                entry("prontuario", prontuario),
                entry("password", password),
                entry("name", name),
                entry("email", email),
                entry("document", document)
        );
        userRepository.create(new UserRequest(prontuario, password, name, email, document));
    }
}
