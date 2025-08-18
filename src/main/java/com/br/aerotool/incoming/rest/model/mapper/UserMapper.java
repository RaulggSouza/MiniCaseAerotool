package com.br.aerotool.incoming.rest.model.mapper;

import com.br.aerotool.domain.entities.user.User;
import com.br.aerotool.incoming.rest.model.user.request.UserCreateRequest;
import com.br.aerotool.incoming.rest.model.user.response.UserResponse;

public class UserMapper {
    public static User requestToEntity(UserCreateRequest userRequest) {
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

    public static UserCreateRequest entityToRequest(User user){
        return new UserCreateRequest(
                user.getProntuario(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getDocument()
        );
    }
}
