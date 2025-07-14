package com.br.aerotool.application.useCases.tool;

import com.br.aerotool.domain.repositories.IToolRepository;
import com.br.aerotool.incoming.rest.model.tool.request.ToolRequest;

import java.util.Objects;

public class CreateTool {
    private final IToolRepository iToolRepository;

    public CreateTool(IToolRepository iToolRepository) {
        this.iToolRepository = iToolRepository;
    }

    public void create(String integrationId, String description, String category){
        final var dto = new ToolRequest(
                Objects.requireNonNull(integrationId, "IntegrationId must not be null"),
                Objects.requireNonNull(description, "Description must not be null"),
                Objects.requireNonNull(category, "Category must not be null")
        );

        iToolRepository.create(dto);
    }
}
