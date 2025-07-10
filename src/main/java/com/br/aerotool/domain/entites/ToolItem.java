package com.br.aerotool.domain.entites;

import lombok.Setter;

public class ToolItem {
    private final long id;
    private final String integrationId;
    @Setter
    private ToolStatus status;
    private final String color;
    private final Tool toolId;

    public ToolItem(long id, String integrationId, ToolStatus status, String color, Tool toolId) {
        this.id = id;
        this.integrationId = integrationId;
        this.status = status;
        this.color = color;
        this.toolId = toolId;
    }

    public ToolItem(String integrationId, ToolStatus status, String color, Tool toolId) {
        this(-1L, integrationId, status, color, toolId);
    }

    public long getId() {
        return id;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public ToolStatus getStatus() {
        return status;
    }

    public String getColor() {
        return color;
    }

    public Tool getToolId() {
        return toolId;
    }

}
