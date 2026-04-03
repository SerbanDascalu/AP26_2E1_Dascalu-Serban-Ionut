package org.example;

import java.time.LocalDate;

public class MovieReportRow
{
    private int id;
    private String title;
    private LocalDate releaseDate;
    private int duration;
    private double score;
    private String genre;
    private String actors;

    public MovieReportRow(int id, String title, LocalDate releaseDate, int duration, double score, String genre, String actors)
    {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
        this.genre = genre;
        this.actors = actors;
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

    public String getGenre()
    {
        return genre;
    }

    public String getActors()
    {
        return actors;
    }
}