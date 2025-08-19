package com.br.aerotool.incoming.rest.model.user.request;

public record UserRow(String prontuario,
                      String password,
                      String name,
                      String email,
                      Integer roleId,
                      String document) {
}
