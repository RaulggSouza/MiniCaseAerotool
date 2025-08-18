package com.br.aerotool.application.interfaces.user;

import com.br.aerotool.application.interfaces.IReadEntity;
import com.br.aerotool.domain.entities.user.User;

import java.util.List;

public interface IReadUser extends IReadEntity<User, String> {
    List<User> findAll(Integer role_id, int offset, int limit);
}
