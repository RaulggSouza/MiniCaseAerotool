package com.br.aerotool.incoming.rest.model.user.request;

public record UserRequest(String prontuario,
                          String password,
                          String name,
                          String email,
                          String role,
                          String document) {
}
