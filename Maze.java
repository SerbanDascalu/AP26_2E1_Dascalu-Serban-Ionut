package org.example;

import java.util.*;

public class Maze {
    private final int rows;
    private final int cols;
    private final Cell[][] cells;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }

        generatePerfectMaze();
    }

    private void generatePerfectMaze() {
        boolean[][] visited = new boolean[rows][cols];
        dfs(0, 0, visited);
    }

    private void dfs(int row, int col, boolean[][] visited) {
        visited[row][col] = true;

        List<int[]> directions = new ArrayList<>();
        directions.add(new int[]{-1, 0});
        directions.add(new int[]{0, 1});
        directions.add(new int[]{1, 0});
        directions.add(new int[]{0, -1});

        Collections.shuffle(directions);

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isInside(newRow, newCol) && !visited[newRow][newCol]) {
                removeWallBetween(row, col, newRow, newCol);
                dfs(newRow, newCol, visited);
            }
        }
    }

    private void removeWallBetween(int row1, int col1, int row2, int col2) {
        Cell a = cells[row1][col1];
        Cell b = cells[row2][col2];

        if (row2 == row1 - 1) {
            a.removeTopWall();
            b.removeBottomWall();
        } else if (col2 == col1 + 1) {
            a.removeRightWall();
            b.removeLeftWall();
        } else if (row2 == row1 + 1) {
            a.removeBottomWall();
            b.removeTopWall();
        } else if (col2 == col1 - 1) {
            a.removeLeftWall();
            b.removeRightWall();
        }
    }

    public List<Position> getAccessibleNeighbors(Position position) {
        List<Position> neighbors = new ArrayList<>();

        int row = position.row();
        int col = position.col();
        Cell cell = cells[row][col];

        if (!cell.hasTopWall() && isInside(row - 1, col)) {
            neighbors.add(new Position(row - 1, col));
        }

        if (!cell.hasRightWall() && isInside(row, col + 1)) {
            neighbors.add(new Position(row, col + 1));
        }

        if (!cell.hasBottomWall() && isInside(row + 1, col)) {
            neighbors.add(new Position(row + 1, col));
        }

        if (!cell.hasLeftWall() && isInside(row, col - 1)) {
            neighbors.add(new Position(row, col - 1));
        }

        return neighbors;
    }

    public boolean isInside(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public Position getExit() {
        return new Position(rows - 1, cols - 1);
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
}