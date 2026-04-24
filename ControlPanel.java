package org.example.lab8_compulsory;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class ControlPanel extends JPanel {
    private final JButton createButton;
    private final JButton resetButton;
    private final JButton exitButton;

    public ControlPanel(MainFrame frame) {
        setLayout(new FlowLayout());

        createButton = new JButton("Create");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        createButton.addActionListener(e -> frame.createRandomMaze());

        resetButton.addActionListener(e -> frame.resetMaze());

        exitButton.addActionListener(e -> System.exit(0));

        add(createButton);
        add(resetButton);
        add(exitButton);
    }
}