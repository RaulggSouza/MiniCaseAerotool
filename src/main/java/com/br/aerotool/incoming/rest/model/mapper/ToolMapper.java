package com.br.aerotool.incoming.rest.model.mapper;

import com.br.aerotool.domain.entities.tool.Tool;
import com.br.aerotool.domain.entities.tool.ToolCategory;
import com.br.aerotool.incoming.rest.model.tool.request.ToolRequest;
import com.br.aerotool.incoming.rest.model.tool.response.ToolResponse;
import org.apache.coyote.BadRequestException;

public class ToolMapper {

    public static Tool toEntity(ToolRequest request) throws BadRequestException {
        return new Tool(
                -1L,
                request.integrationId(),
                request.description(),
                ToolCategory.getToolCategory(request.category()),
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
