package tasks.puzzle.labyrinth.tiles;

import tasks.puzzle.Direction;

public abstract class Tile {
    private boolean hasBeenTraversed;
    private boolean isCurrentlyOccupied;
    private boolean isStartingPosition;
    private boolean isGoal;

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
            return "X";
        } else if (isCurrentlyOccupied) {
            return "M";
        } else if (isStartingPosition) {
            return "S";
        } else if (hasBeenTraversed) {
            return "*";
        } else {
            return "";
        }
    }

    @Override
    public Tile clone() {
        throw new UnsupportedOperationException();
    }
}
