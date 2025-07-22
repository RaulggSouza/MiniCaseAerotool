package com.br.aerotool.incoming.rest.model.mapper;

import com.br.aerotool.domain.entities.user.Role;
import com.br.aerotool.domain.entities.user.User;
import com.br.aerotool.incoming.rest.model.user.request.UserRequest;
import com.br.aerotool.incoming.rest.model.user.response.UserResponse;
import org.apache.coyote.BadRequestException;

public class UserMapper {
    public static User requestToEntity(UserRequest userRequest) {
        return new User(
                userRequest.prontuario(),
                userRequest.password(),
                userRequest.name(),
                userRequest.email(),
                userRequest.document()
        );
    }

    public static UserResponse entityToResponse(User user){
        return new UserResponse(
                user.getProntuario(),
                user.getName(),
                user.getEmail(),
                user.getDocument(),
                user.getRole().getName(),
                user.getDeletedAt()
        );
    }

    public static UserRequest entityToRequest(User user){
        return new UserRequest(
                user.getProntuario(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getDocument()
        );
    }
}
