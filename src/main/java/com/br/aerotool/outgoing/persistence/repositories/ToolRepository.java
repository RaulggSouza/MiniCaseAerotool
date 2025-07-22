package com.br.aerotool.outgoing.persistence.repositories;

import com.br.aerotool.domain.entities.tool.Tool;
import com.br.aerotool.domain.repositories.IToolRepository;
import com.br.aerotool.incoming.rest.model.tool.request.ToolRequest;
import com.br.aerotool.incoming.rest.model.tool.response.ToolResponse;

import java.util.List;
import java.util.Optional;

public class ToolRepository implements IToolRepository {
    @Override
    public void create(ToolRequest request) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Tool> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Tool> findAll() {
        return List.of();
    }

    @Override
    public void update(ToolResponse toolResponse) {

    }
}
