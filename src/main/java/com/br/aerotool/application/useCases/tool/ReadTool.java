package com.br.aerotool.application.useCases.tool;

import com.br.aerotool.application.interfaces.tool.IReadTool;
import com.br.aerotool.domain.entities.Tool;
import com.br.aerotool.domain.repositories.IToolRepository;
import com.br.aerotool.incoming.rest.model.tool.request.ToolFilterRequest;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public List<Tool> findAll() {
        return iToolRepository.findAll();
    }

    private List<Tool> paginate(List<Tool> tools, ToolFilterRequest filter){
        return tools.stream()
                .skip((long) resolvePage(filter.page()) * resolveSize(filter.size()))
                .limit(filter.size() != null ? filter.size() : 10)
                .toList();
    }

    public List<Tool> filterTools(List<Tool> allTools, ToolFilterRequest filter){
        List<Tool> filtered = allTools.stream()
                .filter(tool -> filterById(filter, tool))
                .filter(tool -> filterByIntegrationId(filter, tool))
                .filter(tool -> filterByCategory(filter, tool))
                .filter(tool -> filterByBegin(filter, tool))
                .filter(tool -> filterByEnd(filter, tool))
                .filter(tool -> filterByDeleted(filter, tool))
                .toList();

                return paginate(filtered, filter);

    }

    private static int resolveSize(Integer size) {
        return size != null && size > 0 ? size : 10 ;
    }

    private static int resolvePage(Integer page) {
        return page != null && page >=0 ? page : 0;
    }

    private static boolean filterByDeleted(ToolFilterRequest filter, Tool tool) {
        if(filter.deleted()==null) return true;
        return filter.deleted() == (tool.getDeletedAt() != null);
    }

    private static boolean filterByBegin(ToolFilterRequest filter, Tool tool) {
        if(filter.startDate()==null) return true;
        LocalDateTime deletedAt = tool.getDeletedAt();
        return deletedAt == null || !deletedAt.isBefore(filter.startDate());
    }

    private static boolean filterByEnd(ToolFilterRequest filter, Tool tool) {
        if(filter.endDate()==null) return true;
        LocalDateTime deletedAt = tool.getDeletedAt();
        return deletedAt == null || !deletedAt.isAfter(filter.endDate());
    }

    private static boolean filterByCategory(ToolFilterRequest filter, Tool tool) {
        return filter.category() == null || tool.getCategory() == filter.category();
    }

    private static boolean filterByIntegrationId(String integrationId, Tool tool){
        return integrationId == null ||
                (tool.getIntegrationId()!=null && tool.getIntegrationId().equals(integrationId));
    }

    private static boolean filterByIntegrationId(ToolFilterRequest filter, Tool tool) {
        return filter.integrationId() == null ||
                (tool.getIntegrationId() != null && tool.getIntegrationId().equals(filter.integrationId()));
    }

    private static boolean filterById(Long id, Tool tool) {
        return id == null || tool.getId()==id;
    }

    private static boolean filterById(ToolFilterRequest filter, Tool tool){
        return filterById(filter.id(), tool);
    }

}
