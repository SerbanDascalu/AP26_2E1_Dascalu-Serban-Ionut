package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO
{
    public void create(String title, LocalDate releaseDate, int duration, double score, int genreId) throws SQLException
    {
        String sql = """
                INSERT INTO movies (title, release_date, duration, score, genre_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, title);
            stmt.setDate(2, Date.valueOf(releaseDate));
            stmt.setInt(3, duration);
            stmt.setDouble(4, score);
            stmt.setInt(5, genreId);
            stmt.executeUpdate();
        }
    }

    public void addActorToMovie(int movieId, int actorId) throws SQLException
    {
        String sql = """
                INSERT INTO movie_actors (movie_id, actor_id)
                VALUES (?, ?)
                ON CONFLICT DO NOTHING
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, movieId);
            stmt.setInt(2, actorId);
            stmt.executeUpdate();
        }
    }

    public Movie findById(int id) throws SQLException
    {
        String sql = """
                SELECT m.id, m.title, m.release_date, m.duration, m.score,
                       g.id AS genre_id, g.name AS genre_name
                FROM movies m
                LEFT JOIN genres g ON m.genre_id = g.id
                WHERE m.id = ?
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    Genre genre = new Genre(
                            rs.getInt("genre_id"),
                            rs.getString("genre_name")
                    );

                    Movie movie = new Movie(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getDate("release_date").toLocalDate(),
                            rs.getInt("duration"),
                            rs.getDouble("score"),
                            genre
                    );

                    for (Actor actor : findActorsForMovie(id))
                    {
                        movie.addActor(actor);
                    }

                    return movie;
                }
            }
        }

        return null;
    }

    public List<Movie> findAll() throws SQLException
    {
        List<Movie> movies = new ArrayList<>();
        String sql = """
                SELECT m.id, m.title, m.release_date, m.duration, m.score,
                       g.id AS genre_id, g.name AS genre_name
                FROM movies m
                LEFT JOIN genres g ON m.genre_id = g.id
                ORDER BY m.id
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                Genre genre = new Genre(
                        rs.getInt("genre_id"),
                        rs.getString("genre_name")
                );

                Movie movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDate("release_date").toLocalDate(),
                        rs.getInt("duration"),
                        rs.getDouble("score"),
                        genre
                );

                for (Actor actor : findActorsForMovie(movie.getId()))
                {
                    movie.addActor(actor);
                }

                movies.add(movie);
            }
        }

        return movies;
    }

    private List<Actor> findActorsForMovie(int movieId) throws SQLException
    {
        List<Actor> actors = new ArrayList<>();

        String sql = """
                SELECT a.id, a.name
                FROM actors a
                JOIN movie_actors ma ON a.id = ma.actor_id
                WHERE ma.movie_id = ?
                ORDER BY a.name
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, movieId);

            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    actors.add(new Actor(rs.getInt("id"), rs.getString("name")));
                }
            }
        }

        return actors;
    }
}