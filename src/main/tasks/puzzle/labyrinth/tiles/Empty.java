package tasks.puzzle.labyrinth.tiles;

import tasks.puzzle.Direction;

public class Empty extends Tile {
    @Override
    public String toString() {
        if(super.toString().isEmpty()) {
            return " ";
        } else {
            return super.toString();
        }
    }

    @Override
    public double getCost(Direction direction) {
        return direction.isDiagonal() ? 1.5 : 1;
    }

    @Override
    public Empty clone() {
        Empty clone = new Empty();
        clone.setCurrentlyOccupied(isCurrentlyOccupied());
        if (isStartingPosition()) {
            clone.setAsStartingPosition();
        }
        clone.setHasBeenTraversed(hasBeenTraversed());
        return clone;
    }
}
