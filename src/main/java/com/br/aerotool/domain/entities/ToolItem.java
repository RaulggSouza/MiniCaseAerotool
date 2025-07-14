package com.br.aerotool.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ToolItem {
    private final long id;
    private final String integrationId;
    @Setter
    private ToolItemStatus status;
    private final String color;
    private final Tool tool;

    public ToolItem(long id, String integrationId, ToolItemStatus status, String color, Tool tool) {
        this.id = id;
        this.integrationId = integrationId;
        this.status = status;
        this.color = color;
        this.tool = tool;
    }

    public ToolItem(String integrationId, ToolItemStatus status, String color, Tool tool) {
        this(-1L, integrationId, status, color, tool);
    }

}
