package com.br.aerotool.domain.entities.user;

import com.br.aerotool.domain.entities.tool.ToolCategory;
import lombok.Getter;
import org.apache.coyote.BadRequestException;

import java.util.Arrays;

@Getter
public enum Role {
    ADMIN(1, "admin", "administrador"),
    TECHNICIAN(2, "technician", "técnico que habilita ferramentas"),
    PROFESSOR(3, "professor", "professor que pede ferramente");

    private final int id;
    private final String name;
    private final String description;

    Role(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Role fromId(int id){
        return Arrays.stream(Role.values()).filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public static int idFromName(String name){
        return Role.valueOf(name).getId();
    }
}
