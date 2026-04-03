package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO
{
    public List<MovieReportRow> findAllFromView() throws SQLException
    {
        List<MovieReportRow> rows = new ArrayList<>();

        String sql = """
                SELECT id, title, release_date, duration, score, genre, actors
                FROM movie_report_view
                ORDER BY id
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                rows.add(new MovieReportRow(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDate("release_date").toLocalDate(),
                        rs.getInt("duration"),
                        rs.getDouble("score"),
                        rs.getString("genre"),
                        rs.getString("actors")
                ));
            }
        }

        return rows;
    }
}