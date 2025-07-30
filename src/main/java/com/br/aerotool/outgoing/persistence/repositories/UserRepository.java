package com.br.aerotool.outgoing.persistence.repositories;

import com.br.aerotool.domain.entities.user.User;
import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserFilterRequest;
import com.br.aerotool.incoming.rest.model.user.request.UserRequest;

import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository {
    @Override
    public void create(UserRequest userRequest) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll(UserFilterRequest filter) {
        return List.of();
    }

    @Override
    public List<User> findAll() {
        return findAll(new UserFilterRequest(null, 0, 1000));
    }

    @Override
    public void update(UserRequest userRequest) {

    }

}
