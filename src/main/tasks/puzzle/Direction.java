package tasks.puzzle;

public enum Direction {
    NORTH, SOUTH, WEST, EAST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST;

    public boolean isDiagonal() {
        return this == Direction.NORTH_EAST ||
                this == Direction.NORTH_WEST ||
                this == Direction.SOUTH_EAST ||
                this == Direction.SOUTH_WEST;
    }
}
