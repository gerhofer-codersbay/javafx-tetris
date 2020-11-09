package sample;

import java.util.ArrayList;
import java.util.List;

public class TetrisBoard {

    private int width;
    private int height;
    private List<Cell> cells;

    public TetrisBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                this.cells.add(new Cell(row, col));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
