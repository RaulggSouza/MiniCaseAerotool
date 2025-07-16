package com.br.aerotool.domain.entities.user;

import com.br.aerotool.domain.entities.tool.ToolCategory;
import lombok.Getter;
import org.apache.coyote.BadRequestException;

@Getter
public enum Role {
    ADMIN("admin", "administrador"),
    TECHNICIAN("technician", "técnico que habilita ferramentas"),
    PROFESSOR("professor", "professor que pede ferramente");

    private final String name;
    private final String description;

    Role(String name, String description){
        this.name = name;
        this.description = description;
    }

    public static Role getRole(String name) throws BadRequestException {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Input may not be empty");
        }
        String key = name.trim().toUpperCase();
        try {
            return Role.valueOf(key);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("Invalid Role: "+ name);
        }
    }
}
