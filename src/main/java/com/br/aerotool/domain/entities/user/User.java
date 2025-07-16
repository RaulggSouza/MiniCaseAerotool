package com.br.aerotool.domain.entities.user;

import lombok.*;

import java.util.Objects;

@Getter
public class User{
    private final String prontuario;
    @Setter
    private String password;
    @Setter
    private String name;
    @Setter
    private String email;
    @Setter
    private Role role;

    public User(String prontuario, String password, String name, String email, Role role) {
        this.prontuario = prontuario;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User(String prontuario) {
        this.prontuario = prontuario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(prontuario, user.prontuario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prontuario);
    }
}
