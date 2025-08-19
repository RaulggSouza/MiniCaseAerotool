package com.br.aerotool.outgoing.persistence.repositories;

import com.br.aerotool.config.DatabaseConfig;
import com.br.aerotool.domain.entities.user.Role;
import com.br.aerotool.domain.entities.user.User;
import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserRow;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    @Override
    public void create(UserRow userRow) {
        String sql = "INSERT INTO users (prontuario, password, name, email, role_id, document) VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, userRow.prontuario());
            stmt.setString(2, userRow.password());
            stmt.setString(3, userRow.name());
            stmt.setString(4, userRow.email());
            stmt.setInt(5, userRow.roleId());
            stmt.setString(6, userRow.document());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "UPDATE users SET deleted_at = CURRENT_TIMESTAMP WHERE prontuario = ?";
        try (var conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(String id) {
        String sql = "SELECT * FROM users WHERE prontuario = ? and deleted_at IS NULL";
        try(var conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ){
            stmt.setString(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    return Optional.of(userMapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll(Integer role, int offset, int limit) {
        List<User> users = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM users WHERE deleted_at IS NULL");
        boolean hasRoleFilter = role != null;
        if (hasRoleFilter){
            sql.append(" AND role_id = ?");
        }
        sql.append(" ORDER BY prontuario ASC");
        sql.append(" LIMIT ? OFFSET ?");
        try(var conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql.toString())){
            int idx = 1;
            if (hasRoleFilter){
                stmt.setInt(idx++, role);
            }
            stmt.setInt(idx++, limit);
            stmt.setInt(idx, offset);
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    users.add(userMapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error querying users with pagination", e);
        }
        return users;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql =  "SELECT * FROM users WHERE deleted_at IS NULL ORDER BY prontuario ASC";
        try(var conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    users.add(userMapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error querying users", e);
        }
        return users;
    }

    @Override
    public void update(UserRow userRow) {
        String sql = """
                UPDATE users SET
                password = COALESCE(?, password),
                name     = COALESCE(?, name),
                email    = COALESCE(?, email),
                role_id     = COALESCE(?, role),
                document = COALESCE(?, document),
                updated_at = CURRENT_TIMESTAMP
                WHERE prontuario = ?;
                """;
        try(var conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, userRow.password());
            stmt.setString(2, userRow.name());
            stmt.setString(3, userRow.email());
            stmt.setInt(4, userRow.roleId());
            stmt.setString(5, userRow.document());
            stmt.setString(6, userRow.document());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static User userMapper(ResultSet rs) throws SQLException{
        Timestamp ts = rs.getTimestamp("deleted_at");
        LocalDateTime deletedAt = ts != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null;
        return new User(
                rs.getString("prontuario"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("document"),
                Role.fromId(rs.getInt("role_id")),
                deletedAt
        );
    }

}
