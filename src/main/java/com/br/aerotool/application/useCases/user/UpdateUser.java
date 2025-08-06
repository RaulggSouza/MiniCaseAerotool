package com.br.aerotool.application.useCases.user;

import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserRequest;
import org.springframework.stereotype.Service;

import static java.util.Map.entry;

@Service
public class UpdateUser {
    private final IUserRepository userRepository;

    public UpdateUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void update(String prontuario, String password, String name, String email, String document, String role){
        InputUtils.notBlank(
                entry("prontuario", prontuario),
                entry("password", password),
                entry("name", name),
                entry("email", email),
                entry("document", document),
                entry("role", role)
        );
        userRepository.update(new UserRequest(prontuario, password, name, email, role, document));
    }
}
