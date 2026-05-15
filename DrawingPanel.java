package org.example.tema_lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingPanel extends JPanel {
    private Maze maze;
    private final int cellSize = 35;
    private final int offsetX = 40;
    private final int offsetY = 40;

    public DrawingPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleWall(e.getX(), e.getY());
            }
        });
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
        repaint();
    }

    public Maze getMaze() {
        return maze;
    }

    private void toggleWall(int mouseX, int mouseY) {
        if (maze == null) return;

        int col = (mouseX - offsetX) / cellSize;
        int row = (mouseY - offsetY) / cellSize;

        if (!maze.isInside(row, col)) return;

        int x = offsetX + col * cellSize;
        int y = offsetY + row * cellSize;

        int localX = mouseX - x;
        int localY = mouseY - y;
        int tolerance = 7;

        Cell cell = maze.getCells()[row][col];

        if (localY <= tolerance) {
            cell.setTopWall(!cell.hasTopWall());
            if (row > 0) {
                maze.getCells()[row - 1][col].setBottomWall(cell.hasTopWall());
            }
        } else if (localX >= cellSize - tolerance) {
            cell.setRightWall(!cell.hasRightWall());
            if (col < maze.getCols() - 1) {
                maze.getCells()[row][col + 1].setLeftWall(cell.hasRightWall());
            }
        } else if (localY >= cellSize - tolerance) {
            cell.setBottomWall(!cell.hasBottomWall());
            if (row < maze.getRows() - 1) {
                maze.getCells()[row + 1][col].setTopWall(cell.hasBottomWall());
            }
        } else if (localX <= tolerance) {
            cell.setLeftWall(!cell.hasLeftWall());
            if (col > 0) {
                maze.getCells()[row][col - 1].setRightWall(cell.hasLeftWall());
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (maze == null) return;

        Graphics2D g = (Graphics2D) graphics;
        g.setStroke(new BasicStroke(2));

        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                Cell cell = maze.getCells()[row][col];

                int x = offsetX + col * cellSize;
                int y = offsetY + row * cellSize;

                g.setColor(new Color(220, 240, 255));
                g.fillRect(x, y, cellSize, cellSize);

                if (row == 0 && col == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x + 5, y + 5, cellSize - 10, cellSize - 10);
                }

                if (row == maze.getRows() - 1 && col == maze.getCols() - 1) {
                    g.setColor(Color.RED);
                    g.fillRect(x + 5, y + 5, cellSize - 10, cellSize - 10);
                }

                g.setColor(Color.BLACK);

                if (cell.hasTopWall()) g.drawLine(x, y, x + cellSize, y);
                if (cell.hasRightWall()) g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                if (cell.hasBottomWall()) g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                if (cell.hasLeftWall()) g.drawLine(x, y, x, y + cellSize);
            }
        }
    }
}