package com.br.aerotool.incoming.rest.model.tool.request;

import com.br.aerotool.domain.entities.tool.ToolCategory;

import java.time.LocalDateTime;

public record ToolFilterRequest(
        Long id,
        String integrationId,
        String description,
        ToolCategory category,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean deleted,
        Integer page,
        Integer size
)
{}
