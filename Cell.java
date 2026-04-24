package org.example.lab8_compulsory;

public class Cell {
    private final int row;
    private final int col;

    private boolean topWall = true;
    private boolean rightWall = true;
    private boolean bottomWall = true;
    private boolean leftWall = true;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasTopWall() {
        return topWall;
    }

    public boolean hasRightWall() {
        return rightWall;
    }

    public boolean hasBottomWall() {
        return bottomWall;
    }

    public boolean hasLeftWall() {
        return leftWall;
    }

    public void removeTopWall() {
        topWall = false;
    }

    public void removeRightWall() {
        rightWall = false;
    }

    public void removeBottomWall() {
        bottomWall = false;
    }

    public void removeLeftWall() {
        leftWall = false;
    }
}