package sample;

import java.util.ArrayList;
import java.util.List;

// L Z O T I
public enum Tetromino {
    L(
            List.of(new Cell(0, 1),
                    new Cell(1, 1),
                    new Cell(2, 0),
                    new Cell(2, 1)
            ),
            List.of(),
            List.of(),
            List.of());

    private List<Cell> up;
    private List<Cell> right;
    private List<Cell> down;
    private List<Cell> left;

    Tetromino(List<Cell> up,
              List<Cell> right,
              List<Cell> down,
              List<Cell> left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }

    public List<Integer> getPositions(int width) {
        List<Cell> currentCells = up;
        List<Integer> positions = new ArrayList<>();
        for (Cell cell : currentCells) {
            positions.add(cell.getRow()*width + cell.getColumn());
        }
        return positions;
    }

}
