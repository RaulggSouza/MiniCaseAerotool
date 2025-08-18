package com.br.aerotool.incoming.rest.model.user.request;

public record UserCreateRequest(String prontuario,
                                String password,
                                String name,
                                String email,
                                String role,
                                String document) {
}
