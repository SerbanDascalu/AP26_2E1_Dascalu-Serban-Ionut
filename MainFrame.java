package org.example.lab8_compulsory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
    private final ConfigPanel configPanel;
    private final DrawingPanel drawingPanel;
    private final ControlPanel controlPanel;

    private Maze maze;

    public MainFrame() {
        super("Maze Generator");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createEmptyMaze(int rows, int cols) {
        maze = new Maze(rows, cols);
        drawingPanel.setMaze(maze);
    }

    public void createRandomMaze() {
        if (maze == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "First press Draw Maze."
            );
            return;
        }

        maze.randomlyRemoveWalls();
        drawingPanel.setMaze(maze);
    }

    public void resetMaze() {
        maze = null;
        drawingPanel.resetMaze();
    }
}