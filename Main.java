package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.sql.SQLException;


public class Main
{
    public static void main(String[] args)
    {
        try
        {
            GenreDAO genreDAO = new GenreDAO();

            genreDAO.create("Action");
            genreDAO.create("Drama");

            Genre g1 = genreDAO.findById(1);
            System.out.println("Find by id: " + g1);

            Genre g2 = genreDAO.findByName("Drama");
            System.out.println("Find by name: " + g2);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}