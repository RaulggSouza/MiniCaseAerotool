package com.br.aerotool.domain.entities.user;

import lombok.*;

import java.time.LocalDateTime;
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
    private final String document;
    @Setter
    private Role role;
    private LocalDateTime deletedAt;

    public User(String prontuario, String password, String name, String email, String document, Role role, LocalDateTime deletedAt) {
        this.prontuario = prontuario;
        this.password = password;
        this.name = name;
        this.email = email;
        this.document = document;
        this.role = role;
        this.deletedAt = deletedAt;
    }

    public User(String prontuario, String password, String name, String email, String document) {
        this.prontuario = prontuario;
        this.password = password;
        this.name = name;
        this.email = email;
        this.document = document;
    }

    public void markAsDeleted() {
        if (this.deletedAt == null) this.deletedAt = LocalDateTime.now();
    }

    public void changeRole(Role role) {
        if (this.role == null) this.role = role;
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
