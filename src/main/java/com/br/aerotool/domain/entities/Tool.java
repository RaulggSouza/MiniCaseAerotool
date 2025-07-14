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
    private final LocalDateTime deletedAt;

    public Tool(long id, String integrationId, String description, ToolCategory category, LocalDateTime deletedAt) {
        this.id = id;
        this.integrationId = integrationId;
        this.description = description;
        this.category = category;
        this.deletedAt = deletedAt;
    }

    public Tool(String integrationId, String description, ToolCategory category, LocalDateTime deletedAt) {
        this(-1L, integrationId, description, category, deletedAt);
    }

    public Tool markAsDeleted() {
        return new Tool(id, integrationId, description, category, LocalDateTime.now());
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
