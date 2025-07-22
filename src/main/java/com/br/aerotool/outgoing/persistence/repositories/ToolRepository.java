package com.br.aerotool.outgoing.persistence.repositories;

import com.br.aerotool.config.DatabaseConfig;
import com.br.aerotool.domain.entities.tool.Tool;
import com.br.aerotool.domain.entities.tool.ToolCategory;
import com.br.aerotool.domain.repositories.IToolRepository;
import com.br.aerotool.incoming.rest.model.tool.request.ToolFilterRequest;
import com.br.aerotool.incoming.rest.model.tool.request.ToolRequest;
import com.br.aerotool.incoming.rest.model.tool.response.ToolResponse;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToolRepository implements IToolRepository {
    @Override
    public void create(ToolRequest request) {
        String sql = "INSERT INTO tool (integration_id, description, category) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, request.integrationId());
            ps.setString(2, request.description());
            ps.setString(3, request.category());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar a ferramenta no banco de dados.", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "UPDATE tool SET deleted_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar a ferramenta com id: " + id, e);
        }
    }

    @Override
    public Optional<Tool> findById(Long id) {
        String sql = "SELECT * FROM tool WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return Optional.of(mapResultSetToTool(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar ferramenta por ID.", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Tool> findAll(ToolFilterRequest filter) {
        StringBuilder sql = new StringBuilder("SELECT * FROM tool WHERE 1 = 1");
        List<Object> params = new ArrayList<>();
        List<Tool> tools = new ArrayList<>();

        if (filter.id() != null) {
            sql.append(" AND id = ?");
            params.add(filter.id());
        }
        if (filter.integrationId() != null) {
            sql.append(" AND integration_id = ?");
            params.add(filter.integrationId());
        }
        if (filter.description() != null && !filter.description().isEmpty()) {
            sql.append(" AND description ILIKE ?");
            params.add("%" + filter.description() + "%");
        }
        if (filter.category() != null) {
            sql.append(" AND category = ?");
            params.add(filter.category().name());
        }
        if (filter.startDate() != null) {
            sql.append(" AND created_at >= ?");
            params.add(Timestamp.valueOf(filter.startDate()));
        }
        if (filter.endDate() != null) {
            sql.append(" AND created_at <= ?");
            params.add(Timestamp.valueOf(filter.endDate()));
        }
        if (filter.deleted() != null) {
            sql.append(filter.deleted() ? " AND deleted_at IS NOT NULL" : " AND deleted_at IS NULL");
        }

        int page = (filter.page() != null && filter.page() >= 0) ? filter.page() : 0;
        int size = (filter.size() != null && filter.size() > 0) ? filter.size() : 10;

        sql.append(" ORDER BY id ASC LIMIT ? OFFSET ?");
        params.add(size);
        params.add(page * size);

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for(int i = 0; i<params.size(); i++){
                ps.setObject(i + 1, params.get(i));
            }

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    tools.add(mapResultSetToTool(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar ferramenta com filtro.", e);
        }
        return tools;
    }

    @Override
    public List<Tool> findAll() {
        var defaultFilter = new ToolFilterRequest(null, null, null, null, null, null, false, 0, 1000);
        return this.findAll(defaultFilter);
    }

    @Override
    public void update(ToolResponse toolResponse) {
        String sql = "UPDATE tool SET integration_id = ?, description = ?, category = ?, updated_at = CURRENT_TIMESTAMP" +
                ", deleted_at = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, toolResponse.integrationId());
            ps.setString(2, toolResponse.description());
            ps.setString(3, toolResponse.category());
            ps.setTimestamp(4, toolResponse.deletedAt() != null ? Timestamp.valueOf(toolResponse.deletedAt()) : null);
            ps.setLong(5, toolResponse.id());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar a ferramenta no banco de dados", e);
        }
    }

    private Tool mapResultSetToTool(ResultSet rs) throws SQLException {
        LocalDateTime deletedAt = rs.getObject("deleted_at", LocalDateTime.class);

        return new Tool(
                rs.getLong("id"),
                rs.getString("integration_id"),
                rs.getString("description"),
                ToolCategory.valueOf(rs.getString("category").toUpperCase()),
                deletedAt
        );
    }
}