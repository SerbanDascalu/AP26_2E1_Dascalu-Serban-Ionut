package org.example.lab8_compulsory;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawingPanel extends JPanel {
    private Maze maze;
    private final int cellSize = 35;

    public DrawingPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(700, 550));
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
        repaint();
    }

    public void resetMaze() {
        maze = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (maze == null) {
            return;
        }

        Graphics2D g = (Graphics2D) graphics;
        g.setStroke(new BasicStroke(2));

        int offsetX = 40;
        int offsetY = 40;

        Cell[][] cells = maze.getCells();

        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                Cell cell = cells[row][col];

                int x = offsetX + col * cellSize;
                int y = offsetY + row * cellSize;

                g.setColor(new Color(220, 240, 255));
                g.fillRect(x, y, cellSize, cellSize);

                g.setColor(Color.BLACK);

                if (cell.hasTopWall()) {
                    g.drawLine(x, y, x + cellSize, y);
                }

                if (cell.hasRightWall()) {
                    g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                }

                if (cell.hasBottomWall()) {
                    g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                }

                if (cell.hasLeftWall()) {
                    g.drawLine(x, y, x, y + cellSize);
                }
            }
        }
    }
}