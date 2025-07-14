package com.br.aerotool.application.useCases.tool;

import com.br.aerotool.application.interfaces.tool.IReadTool;
import com.br.aerotool.domain.entities.Tool;
import com.br.aerotool.domain.repositories.IToolRepository;
import com.br.aerotool.incoming.rest.model.tool.response.ToolResponse;

import java.util.Optional;

public class ReadTool implements IReadTool {
    private final IToolRepository iToolRepository;

    public ReadTool(IToolRepository iToolRepository) {
        this.iToolRepository = iToolRepository;
    }

    @Override
    public Optional<Tool> findById(Long id) {
        return iToolRepository.findById(id);
    }
}
