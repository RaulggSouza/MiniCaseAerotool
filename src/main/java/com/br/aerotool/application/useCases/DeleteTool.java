package com.br.aerotool.application.useCases;

import com.br.aerotool.application.interfaces.IDeleteTool;
import com.br.aerotool.domain.entities.Tool;
import com.br.aerotool.domain.repositories.IToolRepository;
import com.br.aerotool.incoming.rest.model.mapper.ToolMapper;

import java.util.NoSuchElementException;
import java.util.Optional;

public class DeleteTool implements IDeleteTool {
    private final IToolRepository iToolRepository;

    public DeleteTool(IToolRepository iToolRepository) {
        this.iToolRepository = iToolRepository;
    }

    @Override
    public void delete(Long id) {
        Optional<Tool> toolOptional = iToolRepository.findById(id);

        Tool tool = toolOptional
                .orElseThrow(() -> new NoSuchElementException("Tool not found"));

        Tool toolDeleted = tool.markAsDeleted();
        iToolRepository.update(ToolMapper.toResponse(toolDeleted));
    }
}
