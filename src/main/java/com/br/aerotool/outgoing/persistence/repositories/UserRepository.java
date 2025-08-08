package com.br.aerotool.outgoing.persistence.repositories;

import com.br.aerotool.config.DatabaseConfig;
import com.br.aerotool.domain.entities.user.Role;
import com.br.aerotool.domain.entities.user.User;
import com.br.aerotool.domain.repositories.IUserRepository;
import com.br.aerotool.incoming.rest.model.user.request.UserFilterRequest;
import com.br.aerotool.incoming.rest.model.user.request.UserRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    @Override
    public void create(UserRequest userRequest) {
        String sql = "INSERT INTO users (prontuario, password, name, email, role_id, document) VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, userRequest.prontuario());
            stmt.setString(2, userRequest.password());
            stmt.setString(3, userRequest.name());
            stmt.setString(4, userRequest.email());
            stmt.setInt(5, Role.idFromName(userRequest.role()));
            stmt.setString(6, userRequest.document());
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
        String sql = "SELECT * FROM users WHERE prontuario = ?";
        try(var conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return userMapper(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll(UserFilterRequest filter) {
        return List.of();
    }

    @Override
    public List<User> findAll() {
        return findAll(new UserFilterRequest(null, 0, Integer.MAX_VALUE));
    }

    @Override
    public void update(UserRequest userRequest) {
        String sql = """
                UPDATE users SET
                password = COALESCE(?, password),
                name     = COALESCE(?, name),
                email    = COALESCE(?, email),
                role     = COALESCE(?, role),
                document = COALESCE(?, document),
                updatedAt = CURRENT_TIMESTAMP
                WHERE prontuario = ?;
                """;
        try(var conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, userRequest.password());
            stmt.setString(2, userRequest.name());
            stmt.setString(3, userRequest.email());
            stmt.setInt(4, Role.idFromName(userRequest.role()));
            stmt.setString(5, userRequest.document());
            stmt.setString(6, userRequest.document());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Optional<User> userMapper(ResultSet rs) throws SQLException{
        return Optional.of(new User(
                rs.getString("prontuario"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("document"),
                Role.fromId(rs.getInt("role_id")),
                LocalDateTime.parse(rs.getString("deleted_at"))
        ));
    }

}
