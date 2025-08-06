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
}