package com.br.aerotool.application.useCases;

import com.br.aerotool.domain.repositories.IToolRepository;
import com.br.aerotool.incoming.rest.model.tool.response.ToolResponse;

import java.util.Objects;

public class UpdateTool {
    private final IToolRepository iToolRepository;

    public UpdateTool(IToolRepository iToolRepository) {
        this.iToolRepository = iToolRepository;
    }

    public void update(long id, String integrationId, String description, String category){
        final var dto = new ToolResponse(
                id,
                Objects.requireNonNull(integrationId, "IntegrationId must not be null"),
                Objects.requireNonNull(description, "Description must not be null"),
                Objects.requireNonNull(category, "Category must not be null")
        );

        iToolRepository.update(dto);
    }
}
