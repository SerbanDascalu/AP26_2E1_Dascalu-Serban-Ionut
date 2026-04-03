package org.example;

import java.time.LocalDate;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            GenreDAO genreDAO = new GenreDAO();
            ActorDAO actorDAO = new ActorDAO();
            MovieDAO movieDAO = new MovieDAO();
            ReportDAO reportDAO = new ReportDAO();
            ReportService reportService = new ReportService();

            genreDAO.create("Action");
            genreDAO.create("Drama");

            actorDAO.create("Keanu Reeves");
            actorDAO.create("Al Pacino");
            actorDAO.create("Carrie-Anne Moss");

            Genre action = genreDAO.findByName("Action");
            Genre drama = genreDAO.findByName("Drama");

            Actor keanu = actorDAO.findByName("Keanu Reeves");
            Actor alPacino = actorDAO.findByName("Al Pacino");
            Actor carrie = actorDAO.findByName("Carrie-Anne Moss");

            movieDAO.create("The Matrix", LocalDate.of(1999, 3, 31), 136, 8.7, action.getId());
            movieDAO.create("The Godfather", LocalDate.of(1972, 3, 24), 175, 9.2, drama.getId());

            Movie matrix = movieDAO.findById(1);
            Movie godfather = movieDAO.findById(2);

            movieDAO.addActorToMovie(matrix.getId(), keanu.getId());
            movieDAO.addActorToMovie(matrix.getId(), carrie.getId());
            movieDAO.addActorToMovie(godfather.getId(), alPacino.getId());

            System.out.println("Genres:");
            for (Genre genre : genreDAO.findAll())
            {
                System.out.println(genre);
            }

            System.out.println();
            System.out.println("Actors:");
            for (Actor actor : actorDAO.findAll())
            {
                System.out.println(actor);
            }

            System.out.println();
            System.out.println("Movies:");
            for (Movie movie : movieDAO.findAll())
            {
                System.out.println(movie);
            }

            List<MovieReportRow> rows = reportDAO.findAllFromView();
            reportService.generateReport(rows, "report.html");

            Database.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}