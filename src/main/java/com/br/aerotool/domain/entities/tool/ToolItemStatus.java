package com.br.aerotool.domain.entities.tool;

import lombok.Getter;
import org.apache.coyote.BadRequestException;

@Getter
public enum ToolItemStatus {
    FUNCIONAL("funcional", "ferramenta utilizável"),
    MANUTENCAO("manutencao", "ferramenta em manutenção"),
    QUEBRADA("quebrada", "ferramente quebrada"),
    DEFEITUOSA("defeituosa", "ferramenta defeituosa");

    private final String name;
    private final String description;

    ToolItemStatus(String name, String description){
        this.name = name;
        this.description = description;
    }
}