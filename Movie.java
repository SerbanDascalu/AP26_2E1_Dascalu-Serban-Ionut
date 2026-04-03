package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie
{
    private int id;
    private String title;
    private LocalDate releaseDate;
    private int duration;
    private double score;
    private Genre genre;
    private List<Actor> actors;

    public Movie(int id, String title, LocalDate releaseDate, int duration, double score, Genre genre)
    {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
        this.genre = genre;
        this.actors = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public LocalDate getReleaseDate()
    {
        return releaseDate;
    }

    public int getDuration()
    {
        return duration;
    }

    public double getScore()
    {
        return score;
    }

    public Genre getGenre()
    {
        return genre;
    }

    public List<Actor> getActors()
    {
        return actors;
    }

    public void addActor(Actor actor)
    {
        actors.add(actor);
    }

    @Override
    public String toString()
    {
        return "Movie{id=" + id + ", title='" + title + '\'' + ", releaseDate=" + releaseDate +
                ", duration=" + duration + ", score=" + score + ", genre=" + genre + ", actors=" + actors + '}';
    }
}