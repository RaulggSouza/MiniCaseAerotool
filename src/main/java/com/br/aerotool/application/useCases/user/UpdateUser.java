package com.br.aerotool.application.useCases.user;

import com.br.aerotool.domain.entities.user.Role;
import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserRow;
import org.springframework.stereotype.Service;

import static java.util.Map.entry;

@Service
public class UpdateUser {
    private final IUserRepository userRepository;

    public UpdateUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void update(String prontuario, String password, String name, String email, String role, String document){
        InputUtils.notBlank(
                entry("prontuario", prontuario)
        );
        Integer roleId = null;
        if(role != null){
            if(!role.trim().isEmpty()){
                roleId = Role.idFromName(role);
            }
        }
        userRepository.update(new UserRow(prontuario, password, name, email, roleId, document));
    }
}
