package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO
{
    public void create(String name) throws SQLException
    {
        String sql = "INSERT INTO actors (name) VALUES (?) ON CONFLICT (name) DO NOTHING";

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    public Actor findById(int id) throws SQLException
    {
        String sql = "SELECT id, name FROM actors WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    return new Actor(rs.getInt("id"), rs.getString("name"));
                }
            }
        }

        return null;
    }

    public Actor findByName(String name) throws SQLException
    {
        String sql = "SELECT id, name FROM actors WHERE name = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    return new Actor(rs.getInt("id"), rs.getString("name"));
                }
            }
        }

        return null;
    }

    public List<Actor> findAll() throws SQLException
    {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT id, name FROM actors ORDER BY id";

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                actors.add(new Actor(rs.getInt("id"), rs.getString("name")));
            }
        }

        return actors;
    }
}