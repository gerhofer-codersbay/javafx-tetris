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
            List.of(new Cell(0, 0),
                    new Cell(1, 0),
                    new Cell(1, 1),
                    new Cell(1, 2)
            ),
            List.of(new Cell(0, 2),
                    new Cell(0, 1),
                    new Cell(1, 1),
                    new Cell(2, 1)
            ),
            List.of(new Cell(2, 2),
                    new Cell(1, 0),
                    new Cell(1, 1),
                    new Cell(1, 2)
            )
    ),
    Z(
            List.of(new Cell(0, 0),
                    new Cell(0, 1),
                    new Cell(1, 1),
                    new Cell(1, 2)
            ),
            List.of(new Cell(0, 2),
                    new Cell(1, 1),
                    new Cell(1, 2),
                    new Cell(2, 1)
            ),
            List.of(new Cell(0, 0),
                    new Cell(0, 1),
                    new Cell(1, 1),
                    new Cell(1, 2)
            ),
            List.of(new Cell(0, 2),
                    new Cell(1, 1),
                    new Cell(1, 2),
                    new Cell(2, 1)
            )
    ),
    T(
            List.of(new Cell(0, 0),
                    new Cell(0, 1),
                    new Cell(0, 2),
                    new Cell(1, 1)),
            List.of(new Cell(0, 2),
                    new Cell(1, 1),
                    new Cell(1, 2),
                    new Cell(2, 2)),
            List.of(new Cell(1, 1),
                    new Cell(2, 0),
                    new Cell(2, 1),
                    new Cell(2, 2)),
            List.of(new Cell(0, 0),
                    new Cell(1, 0),
                    new Cell(1, 1),
                    new Cell(2, 0))
    ),
    O(
            List.of(new Cell(0, 0),
                    new Cell(0, 1),
                    new Cell(1, 0),
                    new Cell(1, 1)),
            List.of(new Cell(0, 1), new Cell(0, 2), new Cell(1, 1), new Cell(1, 2)),
            List.of(new Cell(1, 0), new Cell(1, 1), new Cell(2, 0), new Cell(2, 1)),
            List.of(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0), new Cell(1, 1))
    ),
    I(
            List.of(new Cell(0, 0),
                    new Cell(0, 1),
                    new Cell(0, 2),
                    new Cell(0, 3)
            ),
            List.of(new Cell(0, 0),
                    new Cell(1, 0),
                    new Cell(2, 0),
                    new Cell(3, 0)
            ),
            List.of(new Cell(3, 0),
                    new Cell(3, 1),
                    new Cell(3, 2),
                    new Cell(3, 3)
            ),
            List.of(new Cell(0, 3),
                    new Cell(1, 3),
                    new Cell(2, 3),
                    new Cell(3, 3)
            )
    );

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

    public List<Integer> getPositions(Rotation rotation, int width) {
        List<Cell> currentCells = switch (rotation) {
            case DOWN -> down;
            case UP -> up;
            case RIGHT -> right;
            case LEFT -> left;
        };
        List<Integer> positions = new ArrayList<>();
        for (Cell cell : currentCells) {
            positions.add(cell.getRow() * width + cell.getColumn());
        }
        return positions;
    }

}
