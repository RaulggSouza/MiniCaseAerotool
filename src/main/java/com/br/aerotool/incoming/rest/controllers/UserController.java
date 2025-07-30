package com.br.aerotool.incoming.rest.controllers;

import com.br.aerotool.application.useCases.user.CreateUser;
import com.br.aerotool.application.useCases.user.DeleteUser;
import com.br.aerotool.application.useCases.user.ReadUser;
import com.br.aerotool.application.useCases.user.UpdateUser;
import com.br.aerotool.domain.entities.user.User;
import com.br.aerotool.incoming.rest.model.mapper.UserMapper;
import com.br.aerotool.incoming.rest.model.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUser createUserUseCase;
    private final DeleteUser deleteUserUseCase;
    private final ReadUser readUserUseCase;
    private final UpdateUser updateUserUseCase;

    public UserController(CreateUser createUserUseCase, DeleteUser deleteUserUseCase, ReadUser readUserUseCase, UpdateUser updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.readUserUseCase = readUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @GetMapping("/{prontuario}")
    public ResponseEntity<UserResponse> findById(@PathVariable String prontuario) {
        Optional<User> maybeUser = readUserUseCase.findById(prontuario);
        return maybeUser.map(user -> ResponseEntity.ok(UserMapper.entityToResponse(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserResponse> findAll(
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

    }
}
