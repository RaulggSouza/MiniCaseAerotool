package com.br.aerotool.incoming.rest.controllers;

import com.br.aerotool.application.useCases.user.CreateUser;
import com.br.aerotool.application.useCases.user.DeleteUser;
import com.br.aerotool.application.useCases.user.ReadUser;
import com.br.aerotool.application.useCases.user.UpdateUser;
import com.br.aerotool.domain.entities.user.User;
import com.br.aerotool.incoming.rest.model.mapper.UserMapper;
import com.br.aerotool.incoming.rest.model.user.request.UserRequest;
import com.br.aerotool.incoming.rest.model.user.request.UserUpdateRequest;
import com.br.aerotool.incoming.rest.model.user.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest){
        createUserUseCase.create(userRequest.prontuario(),
                userRequest.password(),
                userRequest.name(),
                userRequest.email(),
                userRequest.document());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{prontuario}")
                .buildAndExpand(userRequest.prontuario())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{prontuario}")
    public ResponseEntity<UserResponse> findById(@PathVariable String prontuario) {
        Optional<User> maybeUser = readUserUseCase.findById(prontuario);
        return maybeUser.map(user -> ResponseEntity.ok(UserMapper.entityToResponse(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<User> users = readUserUseCase.findAll(role, page, size);

        if (users.isEmpty()) return ResponseEntity.noContent().build();

        List<UserResponse> usersResponses = users.stream()
                .map(UserMapper::entityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usersResponses);
    }

    @PutMapping("/{prontuario}")
    public ResponseEntity<Void> updateUser(@PathVariable String prontuario, @RequestBody UserUpdateRequest userRequest){
        updateUserUseCase.update(prontuario,
                userRequest.password(),
                userRequest.name(),
                userRequest.email(),
                userRequest.role(),
                userRequest.document()
        );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{prontuario}")
    public ResponseEntity<Void> deleteUser(@PathVariable String prontuario){
        deleteUserUseCase.delete(prontuario);
        return ResponseEntity.noContent().build();
    }
}
