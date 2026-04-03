package org.example;

public class Movie
{
    private int id;
    private String title;
    private int year;
    private String genre;

    public Movie(int id, String title, int year, String genre)
    {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public int getYear()
    {
        return year;
    }

    public String getGenre()
    {
        return genre;
    }
}