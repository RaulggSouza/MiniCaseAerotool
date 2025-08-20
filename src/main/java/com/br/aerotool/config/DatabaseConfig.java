package com.br.aerotool.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Configuration(proxyBeanMethods = false)
public class DatabaseConfig {

    private static final HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();

            String dbName = getEnvOrDefault("POSTGRES_DB", "aerotool_dev");
            String dbUser = getEnvOrDefault("DATABASE_USERNAME", "dev_user");
            String dbPassword = getEnvOrDefault("DATABASE_PASSWORD", "dev_pass");

            String dbUrl = "jdbc:postgresql://localhost:5432/" + dbName;

            config.setJdbcUrl(dbUrl);
            config.setUsername(dbUser);
            config.setPassword(dbPassword);

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(5);
            config.setConnectionTimeout(30000);

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            System.err.println("Falha fatal ao inicializar o pool de conexões: " + e.getMessage());
            throw new RuntimeException("Erro na configuração do DataSource", e);
        }
    }

    private static String getEnvOrDefault(String name, String defaultValue) {
        return Optional.ofNullable(System.getenv(name)).orElse(defaultValue);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private DatabaseConfig() {}
}