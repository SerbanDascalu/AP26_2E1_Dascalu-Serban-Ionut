package org.example.tema_lab8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

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

    public void generateMazeAnimated() {
        int rows = configPanel.getRowsValue();
        int cols = configPanel.getColsValue();

        if (rows <= 0 || cols <= 0) {
            JOptionPane.showMessageDialog(this, "Invalid dimensions.");
            return;
        }

        maze = new Maze(rows, cols);
        drawingPanel.setMaze(maze);

        new Thread(() -> {
            boolean[][] visited = new boolean[rows][cols];
            generateDFSAnimated(0, 0, visited);

            SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(this,
                            "Generated maze is valid: one connected component and unique path between cells.")
            );
        }).start();
    }

    private void generateDFSAnimated(int row, int col, boolean[][] visited) {
        visited[row][col] = true;

        java.util.List<int[]> directions = new ArrayList<>();
        directions.add(new int[]{-1, 0});
        directions.add(new int[]{0, 1});
        directions.add(new int[]{1, 0});
        directions.add(new int[]{0, -1});

        Collections.shuffle(directions);

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (maze.isInside(newRow, newCol) && !visited[newRow][newCol]) {
                maze.removeWallBetween(row, col, newRow, newCol);

                SwingUtilities.invokeLater(drawingPanel::repaint);

                try {
                    Thread.sleep(configPanel.getSpeed());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                generateDFSAnimated(newRow, newCol, visited);
            }
        }
    }

    public void validateMaze() {
        if (maze == null) {
            JOptionPane.showMessageDialog(this, "No maze available.");
            return;
        }

        if (maze.isTraversable()) {
            JOptionPane.showMessageDialog(this, "Maze is traversable from start to end.");
        } else {
            JOptionPane.showMessageDialog(this, "Maze is NOT traversable.");
        }
    }

    public void exportPNG() {
        if (maze == null) {
            JOptionPane.showMessageDialog(this, "No maze to export.");
            return;
        }

        try {
            BufferedImage image = new BufferedImage(
                    drawingPanel.getWidth(),
                    drawingPanel.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );

            Graphics2D g2d = image.createGraphics();
            drawingPanel.paint(g2d);
            g2d.dispose();

            ImageIO.write(image, "png", new File("maze.png"));

            JOptionPane.showMessageDialog(this, "Maze exported as maze.png");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Export failed.");
        }
    }

    public void saveMaze() {
        if (maze == null) {
            JOptionPane.showMessageDialog(this, "No maze to save.");
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("maze.ser"))) {
            out.writeObject(maze);
            JOptionPane.showMessageDialog(this, "Maze saved as maze.ser");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Save failed.");
        }
    }

    public void loadMaze() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("maze.ser"))) {
            maze = (Maze) in.readObject();
            drawingPanel.setMaze(maze);
            JOptionPane.showMessageDialog(this, "Maze loaded.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Load failed.");
        }
    }

    public void resetMaze() {
        maze = null;
        drawingPanel.setMaze(null);
    }
}