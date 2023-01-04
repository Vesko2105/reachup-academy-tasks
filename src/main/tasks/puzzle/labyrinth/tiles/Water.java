package tasks.puzzle.labyrinth.tiles;

import tasks.puzzle.Direction;

public class Water extends Tile{

    @Override
    public String toString() {
        if(super.toString().isEmpty()) {
            return "~";
        } else {
            return super.toString();
        }
    }

    @Override
    public double getCost(Direction direction) {
        return 2;
    }

    @Override
    public Water clone() {
        Water clone = new Water();
        clone.setCurrentlyOccupied(isCurrentlyOccupied());
        if (isStartingPosition()) {
            clone.setAsStartingPosition();
        }
        clone.setHasBeenTraversed(hasBeenTraversed());
        return clone;
    }
}
