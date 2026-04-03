package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database
{
    private static final HikariDataSource dataSource;

    static
    {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/moviesdb");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setPoolName("MoviePool");

        dataSource = new HikariDataSource(config);
    }

    private Database()
    {
    }

    public static Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

    public static void close()
    {
        dataSource.close();
    }
}