package com.br.aerotool.incoming.rest.controllers;

import com.br.aerotool.application.useCases.tool.CreateTool;
import com.br.aerotool.application.useCases.tool.DeleteTool;
import com.br.aerotool.application.useCases.tool.ReadTool;
import com.br.aerotool.application.useCases.tool.UpdateTool;
import com.br.aerotool.domain.entities.tool.Tool;
import com.br.aerotool.incoming.rest.model.tool.request.ToolFilterRequest;
import com.br.aerotool.incoming.rest.model.tool.request.ToolRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/tools")
public class ToolController {

    private final CreateTool createToolUseCase;
    private final ReadTool readToolUseCase;
    private final UpdateTool updateToolUseCase;
    private final DeleteTool deleteToolUseCase;

    public ToolController(CreateTool createToolUseCase, ReadTool readToolUseCase, UpdateTool updateToolUseCase, DeleteTool deleteToolUseCase){
        this.createToolUseCase = createToolUseCase;
        this.readToolUseCase = readToolUseCase;
        this.updateToolUseCase = updateToolUseCase;
        this.deleteToolUseCase = deleteToolUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ToolRequest request){
        createToolUseCase.create(request.integrationId(), request.description(), request.category());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tool> findById(@PathVariable Long id){
        return readToolUseCase.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Tool>> findByFilter(ToolFilterRequest filter){
        List<Tool> tools = readToolUseCase.findByFilter(filter);
        return ResponseEntity.ok(tools);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ToolRequest request){
        updateToolUseCase.update(id, request.integrationId(), request.description(), request.category(), null);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            deleteToolUseCase.delete(id);
            return ResponseEntity.noContent().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
