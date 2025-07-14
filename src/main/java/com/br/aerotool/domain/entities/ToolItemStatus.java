package com.br.aerotool.domain.entities;

public enum ToolItemStatus {
    FUNCIONAL("funcional", "ferramenta utilizável"),
    MANUTENCAO("manutencao", "ferramenta em manutenção"),
    QUEBRADA("quebrada", "ferramente quebrada"),
    DEFEITUOSA("defeituosa", "ferramenta defeituosa");

    private final String name;
    private String description;

    ToolItemStatus(String name, String description){
        this.name = name;
        this.description = description;
    }

    public ToolCategory getToolItemStatus(String name){
        return ToolCategory.valueOf(name);
    }
}