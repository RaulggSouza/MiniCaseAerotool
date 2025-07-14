package com.br.aerotool.domain.repositories;

import com.br.aerotool.application.interfaces.tool.ICreateTool;
import com.br.aerotool.application.interfaces.tool.IDeleteTool;
import com.br.aerotool.application.interfaces.tool.IReadTool;
import com.br.aerotool.application.interfaces.tool.IUpdateTool;

public interface IToolRepository extends
        ICreateTool,
        IUpdateTool,
        IReadTool,
        IDeleteTool
{
}
