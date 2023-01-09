package tasks.puzzle.labyrinth.tiles;

import tasks.colors.Colors;
import tasks.puzzle.Direction;
import tasks.puzzle.labyrinth.InvalidMapTileException;

public abstract class Tile {
    private boolean hasBeenTraversed;
    private boolean isCurrentlyOccupied;
    private boolean isStartingPosition;
    private boolean isGoal;

    public static Tile getNew(String tileString) {
        return switch (tileString) {
            case " " -> new Empty();
            case "W" -> new Wall();
            case "~" -> new Water();
            default -> throw new InvalidMapTileException(tileString);
        };
    }

    public final boolean isOfType(Class<? extends Tile> classType) {
        return this.getClass().equals(classType);
    }

    public boolean hasBeenTraversed() {
        return hasBeenTraversed;
    }

    public void setHasBeenTraversed(boolean hasBeenTraversed) {
        this.hasBeenTraversed = hasBeenTraversed;
    }

    public boolean isStartingPosition() {
        return isStartingPosition;
    }

    public void setAsStartingPosition() {
        isStartingPosition = true;
    }

    public boolean isCurrentlyOccupied() {
        return isCurrentlyOccupied;
    }

    public void setCurrentlyOccupied(boolean isCurrentlyOccupied) {
        this.isCurrentlyOccupied = isCurrentlyOccupied;
    }

    public boolean isGoal() {
        return isGoal;
    }

    public void setGoal(boolean goal) {
        isGoal = goal;
    }

    public abstract double getCost(Direction direction);

    @Override
    public String toString() {
        if (isGoal) {
            return Colors.BRIGHT_RED + "X" + Colors.RESET;
        } else if (isCurrentlyOccupied) {
            return Colors.BRIGHT_GREEN + "M" + Colors.RESET;
        } else if (hasBeenTraversed) {
            return Colors.getByCode(94) + "*" + Colors.RESET;
        } else {
            return "";
        }
    }

    @Override
    public Tile clone() {
        throw new UnsupportedOperationException();
    }
}
