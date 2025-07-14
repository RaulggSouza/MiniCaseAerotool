package com.br.aerotool.domain.entities;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Tool {
    private final long id;
    private final String integrationId;
    private final String description;
    private final ToolCategory category;

    public Tool(long id, String integrationId, String description, ToolCategory category) {
        this.id = id;
        this.integrationId = integrationId;
        this.description = description;
        this.category = category;
    }

    public Tool(String integrationId, String description, ToolCategory category) {
        this(-1L, integrationId, description, category);
    }

    public long getId() {
        return id;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public String getDescription() {
        return description;
    }

    public ToolCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tool tool)) return false;
        return id == tool.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
