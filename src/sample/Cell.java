package sample;

public class Cell {

    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPosition(int width) {
        return row * width + column;
    }

    public void moveDown() {
        this.row++;
    }

    public void moveLeft() { this.column--; }

    public void moveRight() { this.column++;}
}
