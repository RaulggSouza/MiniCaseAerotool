package com.br.aerotool.domain.repositories;

import com.br.aerotool.application.interfaces.tool.ICreateTool;
import com.br.aerotool.application.interfaces.tool.IDeleteTool;
import com.br.aerotool.application.interfaces.tool.IReadTool;
import com.br.aerotool.application.interfaces.tool.IUpdateTool;
import com.br.aerotool.domain.entities.tool.Tool;
import com.br.aerotool.incoming.rest.model.tool.request.ToolFilterRequest;

import java.util.List;

public interface IToolRepository extends
        ICreateTool,
        IUpdateTool,
        IReadTool,
        IDeleteTool
{
    List<Tool> findAll(ToolFilterRequest filter);
}
