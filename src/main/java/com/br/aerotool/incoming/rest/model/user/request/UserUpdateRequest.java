package com.br.aerotool.incoming.rest.model.user.request;

import java.time.LocalDateTime;

public record UserUpdateRequest(String password,
                                String name,
                                String email,
                                String role,
                                String document) {
}
