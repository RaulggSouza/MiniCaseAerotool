package com.br.aerotool.application.useCases.user;

import com.br.aerotool.domain.entities.user.User;
import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserFilterRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReadUser {
    private final IUserRepository userRepository;

    public ReadUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(String prontuario){
        Objects.requireNonNull(prontuario, "prontuario must not be null");
        if(prontuario.isBlank()) throw new IllegalArgumentException("prontuario must not be blank");

        return userRepository.findById(prontuario);
    }

    public List<User> findAll(String role, int page, int size){
        if (page < 0) throw new IllegalArgumentException("Page must be greater than or equal 0");
        if (size <= 0) throw new IllegalArgumentException("Size must be greater than 0");

        int offset = page * size;
        String filterRole = role != null && !role.isBlank() ? role.toUpperCase() : null;

        return userRepository.findAll(new UserFilterRequest(filterRole, page, size));
    }
}
