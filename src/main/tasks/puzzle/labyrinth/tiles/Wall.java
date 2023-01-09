package tasks.puzzle.labyrinth.tiles;

import tasks.colors.Colors;
import tasks.puzzle.Direction;

public class Wall extends Tile{
    @Override
    public String toString() {
        if(super.toString().isEmpty()) {
            return Colors.BACKGROUND_WHITE + " " + Colors.RESET;
        } else {
            return super.toString();
        }
    }

    @Override
    public double getCost(Direction direction) {
        return 1000;
    }

    @Override
    public Wall clone() {
        Wall clone = new Wall();
        clone.setCurrentlyOccupied(isCurrentlyOccupied());
        if (isStartingPosition()) {
            clone.setAsStartingPosition();
        }
        clone.setHasBeenTraversed(hasBeenTraversed());
        return clone;
    }
}
