package tasks.puzzle.labyrinth;

import tasks.Pair;
import tasks.puzzle.labyrinth.tiles.*;

public class LabyrinthTest {
    public static void main(String[] args) {
        Tile[][] map = {
                {new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty()},
                {new Empty(), new Empty(), new Water(), new Empty(), new Empty(), new Empty(), new Empty(), new Wall(), new Wall(), new Wall(), new Empty(), new Empty(), new Empty()},
                {new Empty(), new Water(), new Wall(), new Wall(), new Empty(), new Empty(), new Wall(), new Wall(), new Empty(), new Wall(), new Empty(), new Empty(), new Empty()},
                {new Empty(), new Water(), new Empty(), new Wall(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Wall(), new Wall(), new Wall(), new Empty()},
                {new Empty(), new Water(), new Empty(), new Wall(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Wall(), new Empty(), new Empty(), new Empty()},
                {new Empty(), new Empty(), new Empty(), new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Empty(), new Empty(), new Empty()},
                {new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty()},
                {new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty()}
        };
        Labyrinth labyrinth = new Labyrinth(map, new Pair<>(5, 0), new Pair<>(2, map[0].length - 3));
        labyrinth.findPath();
    }

}
