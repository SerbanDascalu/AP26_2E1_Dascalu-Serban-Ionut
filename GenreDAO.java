package org.example;
import java.sql.*;

public class GenreDAO
{
    private final Connection connection;

    public GenreDAO() throws SQLException
    {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void create(String name) throws SQLException
    {
        String sql = "INSERT INTO genres (name) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    public Genre findById(int id) throws SQLException
    {
        String sql = "SELECT id, name FROM genres WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    return new Genre(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }
        }

        return null;
    }

    public Genre findByName(String name) throws SQLException
    {
        String sql = "SELECT id, name FROM genres WHERE name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    return new Genre(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }
        }

        return null;
    }
}