package com.br.aerotool.application.useCases.user;

import com.br.aerotool.domain.ElementAlreadyDeleted;
import com.br.aerotool.domain.entities.user.User;
import com.br.aerotool.domain.repositories.IUserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class DeleteUser {
    private final IUserRepository userRepository;

    public DeleteUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void delete(String prontuario){
        Objects.requireNonNull(prontuario, "prontuario must not be null");
        if(prontuario.isBlank()) throw new IllegalArgumentException("prontuario must not be blank");

        Optional<User> maybeUser = userRepository.findById(prontuario);
        if(maybeUser.isEmpty()) throw new NoSuchElementException("User not found. Prontuario: " + prontuario);
        User user = maybeUser.get();
        if (user.getDeletedAt() == null) throw new ElementAlreadyDeleted("User already has been deleted. Prontuario: " + prontuario);
        user.markAsDeleted();
        userRepository.delete(prontuario);
    }
}
