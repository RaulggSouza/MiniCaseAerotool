package com.br.aerotool.application.useCases.user;

import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserRequest;

import java.time.LocalDateTime;

import static java.util.Map.entry;

public class UpdateUser {
    private final IUserRepository userRepository;

    public UpdateUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void update(String prontuario, String password, String name, String email, String document, String role, LocalDateTime deletedAt){
        InputUtils.notBlank(
                entry("prontuario", prontuario),
                entry("password", password),
                entry("name", name),
                entry("email", email),
                entry("document", document)
        );
        userRepository.update(new UserRequest(prontuario, password, name, email, document));
    }
}
