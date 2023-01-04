package tasks.puzzle.labyrinth;

import tasks.Pair;
import tasks.puzzle.Direction;
import tasks.puzzle.labyrinth.tiles.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class LabyrinthNode {
    private Tile[][] map;
    private List<LabyrinthNode> children;
    private Pair<Integer> playerCoordinates;
    private Pair<Integer> goalCoordinates;
    public final LabyrinthNode parent;
    public final double pathCost;

    public final double cost;

    public LabyrinthNode(Tile[][] map, Pair<Integer> playerCoordinates, Pair<Integer> goalCoordinates, LabyrinthNode parent, double moveCost) {
        this.map = map;
        this.children = null;
        this.playerCoordinates = playerCoordinates;
        this.goalCoordinates = goalCoordinates;
        this.parent = parent;
        this.pathCost = parent == null ? moveCost : parent.pathCost + moveCost;
        this.cost = moveCost;
    }

    public List<LabyrinthNode> getChildren() {
        if (children == null) {
            children = new LinkedList<>();
            tryMoveUp();
            tryMoveRight();
            tryMoveDown();
            tryMoveLeft();
            tryMoveUpRight();
            tryMoveDownRight();
            tryMoveDownLeft();
            tryMoveUpLeft();
        }
        return children;
    }

    private Tile[][] advanceMapTo(Pair<Integer> newCoordinates) {
        int oldRow = playerCoordinates.getValue1();
        int oldColumn = playerCoordinates.getValue2();
        int newRow = newCoordinates.getValue1();
        int newColumn = newCoordinates.getValue2();
        Tile[][] newMap = new Tile[map.length][map[0].length];
        for (int i = 0; i < newMap.length; i++) {
            newMap[i] = Arrays.stream(map[i]).map(Tile::clone).toArray(Tile[]::new);
        }
        Tile oldTile = newMap[oldRow][oldColumn];
        Tile newTile = newMap[newRow][newColumn];
        oldTile.setHasBeenTraversed(true);
        oldTile.setCurrentlyOccupied(false);
        newTile.setCurrentlyOccupied(true);
        return newMap;
    }

    private void tryMoveInDirection(
            Direction direction,
            Predicate<Pair<Integer>> predicate,
            Pair<Integer> newPlayerCoordinates
    ) {
        if (!predicate.test(newPlayerCoordinates)) {
            return;
        }
        Tile newTile = map[newPlayerCoordinates.getValue1()][newPlayerCoordinates.getValue2()];
        if (newTile.isOfType(Wall.class) || newTile.hasBeenTraversed()) {
            return;
        }
        Tile currentTile = map[playerCoordinates.getValue1()][playerCoordinates.getValue2()];
        LabyrinthNode newNode = new LabyrinthNode(
                advanceMapTo(newPlayerCoordinates),
                newPlayerCoordinates,
                goalCoordinates,
                this,
                currentTile.getCost(direction)
        );
        if (newNode.isGoal() || newNode.distanceFromStart() >= this.distanceFromStart()) {
            children.add(newNode);
        }
    }

    private void tryMoveUp() {
        tryMoveInDirection(
                Direction.NORTH,
                coordinates -> coordinates.getValue1() >= 0,
                new Pair<>(playerCoordinates.getValue1() - 1, playerCoordinates.getValue2())
        );
    }

    private void tryMoveDown() {
        tryMoveInDirection(
                Direction.SOUTH,
                coordinates -> coordinates.getValue1() <= map.length - 1,
                new Pair<>(playerCoordinates.getValue1() + 1, playerCoordinates.getValue2())
        );
    }

    private void tryMoveLeft() {
        tryMoveInDirection(
                Direction.WEST,
                coordinates -> coordinates.getValue2() >= 0,
                new Pair<>(playerCoordinates.getValue1(), playerCoordinates.getValue2() - 1)
        );
    }

    private void tryMoveRight() {
        tryMoveInDirection(
                Direction.EAST,
                coordinates -> coordinates.getValue2() <= map[0].length - 1,
                new Pair<>(playerCoordinates.getValue1(), playerCoordinates.getValue2() + 1)
        );
    }

    private void tryMoveUpRight() {
        tryMoveInDirection(
                Direction.NORTH_EAST,
                coordinates -> coordinates.getValue1() >= 0 && coordinates.getValue2() <= map[0].length - 1,
                new Pair<>(playerCoordinates.getValue1() - 1, playerCoordinates.getValue2() + 1)
        );
    }

    private void tryMoveDownRight() {
        tryMoveInDirection(
                Direction.SOUTH_EAST,
                coordinates -> coordinates.getValue1() <= map.length - 1 && coordinates.getValue2() <= map[0].length - 1,
                new Pair<>(playerCoordinates.getValue1() + 1, playerCoordinates.getValue2() + 1)
        );
    }

    private void tryMoveDownLeft() {
        tryMoveInDirection(
                Direction.SOUTH_WEST,
                coordinates -> coordinates.getValue1() <= map.length - 1 && coordinates.getValue2() >= 0,
                new Pair<>(playerCoordinates.getValue1() + 1, playerCoordinates.getValue2() - 1)
        );
    }

    private void tryMoveUpLeft() {
        tryMoveInDirection(
                Direction.NORTH_WEST,
                coordinates -> coordinates.getValue1() >= 0 && coordinates.getValue2() >= 0,
                new Pair<>(playerCoordinates.getValue1() - 1, playerCoordinates.getValue2() - 1)
        );
    }

    // TODO: 4.1.2023 г. Remove if not necessary
//    private Pair<Integer> getPosition(Class<? extends Tile> tileType) {
//        int rowNumber = 0;
//        int column;
//        for (Tile[] row : map) {
//            column = 0;
//            for (Tile tile : row) {
//                if (tile.isOfType(tileType)) {
//                    return new Pair<>(rowNumber, column);
//                }
//                column++;
//            }
//            rowNumber++;
//        }
//        return new Pair<>(-1, -1);
//    }

    public boolean isGoal() {
        return goalCoordinates.equals(playerCoordinates);
    }

    public double evaluateState() {
        return distanceFromGoal() + pathCost + cost;
    }

    public int distanceFromGoal() {
        return Math.abs(goalCoordinates.getValue1() - playerCoordinates.getValue1()) + Math.abs(goalCoordinates.getValue2() - playerCoordinates.getValue2());
    }

    public int distanceFromStart() {
        LabyrinthNode startingNode = this;
        while (startingNode.parent != null) {
            startingNode = startingNode.parent;
        }
        Pair<Integer> startingCoordinates = startingNode.playerCoordinates;
        return Math.abs(startingCoordinates.getValue1() - playerCoordinates.getValue1()) + Math.abs(startingCoordinates.getValue2() - playerCoordinates.getValue2());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Tile[] tiles : map) {
            StringJoiner stringJoiner = new StringJoiner("|", "|", "|");
            for (Tile tile : tiles) {
                stringJoiner.add(tile.toString());
            }
            stringBuilder.append(stringJoiner).append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
