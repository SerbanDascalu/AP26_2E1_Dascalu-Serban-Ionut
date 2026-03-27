package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    private static DatabaseConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/moviesdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private DatabaseConnection() throws SQLException
    {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static DatabaseConnection getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection()
    {
        return connection;
    }
}