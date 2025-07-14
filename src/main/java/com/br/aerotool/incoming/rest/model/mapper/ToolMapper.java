package com.br.aerotool.incoming.rest.model.mapper;

import com.br.aerotool.domain.entities.Tool;
import com.br.aerotool.domain.entities.ToolCategory;
import com.br.aerotool.incoming.rest.model.tool.request.ToolRequest;
import com.br.aerotool.incoming.rest.model.tool.response.ToolResponse;

public class ToolMapper {

    public static Tool toEntity(ToolRequest request){
        return new Tool(
                -1L,
                request.integrationId(),
                request.description(),
                ToolCategory.valueOf(request.category()),
                null
        );
    }

    public static ToolResponse toResponse(Tool tool){
        return new ToolResponse(
                tool.getId(),
                tool.getIntegrationId(),
                tool.getDescription(),
                tool.getCategory().toString(),
                tool.getDeletedAt()
        );
    }
}
