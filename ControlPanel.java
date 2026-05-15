package org.example.tema_lab8;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel(MainFrame frame) {
        setLayout(new FlowLayout());

        JButton generateButton = new JButton("Generate");
        JButton validateButton = new JButton("Validate");
        JButton exportButton = new JButton("Export PNG");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");

        generateButton.addActionListener(e -> frame.generateMazeAnimated());
        validateButton.addActionListener(e -> frame.validateMaze());
        exportButton.addActionListener(e -> frame.exportPNG());
        saveButton.addActionListener(e -> frame.saveMaze());
        loadButton.addActionListener(e -> frame.loadMaze());
        resetButton.addActionListener(e -> frame.resetMaze());
        exitButton.addActionListener(e -> System.exit(0));

        add(generateButton);
        add(validateButton);
        add(exportButton);
        add(saveButton);
        add(loadButton);
        add(resetButton);
        add(exitButton);
    }
}