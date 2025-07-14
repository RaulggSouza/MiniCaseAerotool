package com.br.aerotool.domain.repositories;

import com.br.aerotool.application.interfaces.ICreateTool;
import com.br.aerotool.application.interfaces.IDeleteTool;
import com.br.aerotool.application.interfaces.IReadTool;
import com.br.aerotool.application.interfaces.IUpdateTool;

public interface IToolRepository extends
        ICreateTool,
        IUpdateTool,
        IReadTool,
        IDeleteTool
{
}
