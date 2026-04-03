package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController
{
    @GetMapping("/movies")
    public List<Movie> getMovies()
    {
        return List.of(
                new Movie(1, "The Matrix", 1999, "Action"),
                new Movie(2, "The Godfather", 1972, "Drama"),
                new Movie(3, "Inception", 2010, "Sci-Fi")
        );
    }
}