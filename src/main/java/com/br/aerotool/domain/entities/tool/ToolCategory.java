package com.br.aerotool.domain.entities.tool;

import lombok.Getter;
import org.apache.coyote.BadRequestException;

@Getter
public enum ToolCategory {
    MANUAL("manual", "ferramentas manuais"),
    ELETRICA("electrica", "ferramentas conectadas à rede ou bateria"),
    PRECISAO("precisao", "ferramentas com alta precisão"),
    MEDICAO("medicao", "ferramentas usadas para medir distâncias");

    private final String name;
    private final String description;

    ToolCategory(String name, String description){
        this.name = name;
        this.description = description;
    }

    public static ToolCategory getToolCategory(String name) throws BadRequestException {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Input may not be empty");
        }
        String key = name.trim().toUpperCase();
        try {
            return ToolCategory.valueOf(key);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("Invalid Tool Category: "+ name);
        }
    }
}