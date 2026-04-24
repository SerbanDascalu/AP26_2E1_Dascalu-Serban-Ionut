package org.example.lab8_compulsory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.SwingUtilities;

@SpringBootApplication
public class MazeApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        SpringApplication.run(MazeApplication.class, args);

        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}