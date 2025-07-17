package com.br.aerotool.incoming.rest.model.user.response;

import java.time.LocalDateTime;

public record UserResponse (String prontuario,
                            String name,
                            String email,
                            String role,
                            LocalDateTime deletedAt){
}
