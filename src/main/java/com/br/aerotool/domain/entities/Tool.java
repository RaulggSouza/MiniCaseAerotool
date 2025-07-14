package com.br.aerotool.domain.entities;

import lombok.Getter;
import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Tool {
    private final long id;
    private final String integrationId;
    private final String description;
    private final String category;
    private final LocalDate deletedAt;

    public Tool(long id, String integrationId, String description, String category, LocalDate deletedAt) {
        this.id = id;
        this.integrationId = integrationId;
        this.description = description;
        this.category = category;
        this.deletedAt = deletedAt;
    }

    public Tool(String integrationId, String description, String category, LocalDate deletedAt) {
        this(-1L, integrationId, description, category, deletedAt);
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
