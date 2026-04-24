package org.example.lab8_compulsory;

import java.util.Random;

public class Maze {
    private final int rows;
    private final int cols;
    private final Cell[][] cells;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];

        initializeCells();
    }

    private void initializeCells() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void randomlyRemoveWalls() {
        Random random = new Random();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = cells[row][col];

                if (col < cols - 1 && random.nextBoolean()) {
                    cell.removeRightWall();
                    cells[row][col + 1].removeLeftWall();
                }

                if (row < rows - 1 && random.nextBoolean()) {
                    cell.removeBottomWall();
                    cells[row + 1][col].removeTopWall();
                }
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}