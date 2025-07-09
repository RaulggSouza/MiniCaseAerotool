package com.br.aerotool.domain.entites;

import lombok.Setter;

public class ToolItem {
    private final long id;
    private final long integrationID;
    @Setter
    private ToolStatus status;
    private final String color;
    private final Tool toolId;

    public ToolItem(long id, long integrationID, ToolStatus status, String color, Tool toolId) {
        this.id = id;
        this.integrationID = integrationID;
        this.status = status;
        this.color = color;
        this.toolId = toolId;
    }

    public long getId() {
        return id;
    }

    public long getIntegrationID() {
        return integrationID;
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
