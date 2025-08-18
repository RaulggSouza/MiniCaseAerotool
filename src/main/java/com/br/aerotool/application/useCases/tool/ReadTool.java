package com.br.aerotool.application.useCases.tool;

import com.br.aerotool.application.interfaces.tool.IReadTool;
import com.br.aerotool.domain.entities.tool.Tool;
import com.br.aerotool.domain.repositories.IToolRepository;
import com.br.aerotool.incoming.rest.model.tool.request.ToolFilterRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadTool implements IReadTool {
    private final IToolRepository iToolRepository;

    public ReadTool(IToolRepository iToolRepository) {
        this.iToolRepository = iToolRepository;
    }

    @Override
    public Optional<Tool> findById(Long id) {
        return iToolRepository.findById(id);
    }

    public List<Tool> findByFilter(ToolFilterRequest filter) {
        return iToolRepository.findAll(filter);
    }

    @Override
    public List<Tool> findAll() {
        var defaultFilter = new ToolFilterRequest(
                null,
                null,
                null,
                null,
                null,
                null,
                false,
                0,
                20
        );
        return iToolRepository.findAll(defaultFilter);
    }
}
