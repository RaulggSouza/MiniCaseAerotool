package com.br.aerotool.domain.entites;


import java.util.Objects;

public class Tool {
    private final long id;
    private final long integrationId;
    private final String description;
    private final String category;

    public Tool(long id, long integrationId, String description, String category) {
        this.id = id;
        this.integrationId = integrationId;
        this.description = description;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public long getIntegrationId() {
        return integrationId;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
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
