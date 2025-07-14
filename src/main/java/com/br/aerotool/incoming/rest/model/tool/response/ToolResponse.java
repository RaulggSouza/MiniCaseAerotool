package com.br.aerotool.incoming.rest.model.tool.response;

import java.time.LocalDateTime;

public record ToolResponse(long id, String integrationId, String description, String category, LocalDateTime deletedAt) { }
