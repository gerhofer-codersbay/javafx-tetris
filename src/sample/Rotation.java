package sample;

public enum Rotation {
    UP,
    LEFT,
    DOWN,
    RIGHT;

    public static Rotation next(Rotation rotation) {
        return switch (rotation) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }
}
