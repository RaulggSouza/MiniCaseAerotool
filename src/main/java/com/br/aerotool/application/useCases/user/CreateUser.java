package com.br.aerotool.application.useCases.user;

import com.br.aerotool.domain.entities.user.Role;
import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserCreateRequest;
import com.br.aerotool.incoming.rest.model.user.request.UserRow;
import org.springframework.stereotype.Service;

import static java.util.Map.entry;

@Service
public class CreateUser {
    private final IUserRepository userRepository;

    public CreateUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void create(String prontuario, String password, String name, String email, String role, String document){
        InputUtils.notBlank(
                entry("prontuario", prontuario),
                entry("password", password),
                entry("name", name),
                entry("email", email),
                entry("document", document)
        );
        Integer roleId = null;
        if(role != null){
            if(!role.trim().isEmpty()){
                roleId = Role.idFromName(role);
            }
        }
        userRepository.create(new UserRow(prontuario, password, name, email, roleId, document));
    }
}
