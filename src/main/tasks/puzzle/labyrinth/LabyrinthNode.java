package tasks.puzzle.labyrinth;

import tasks.Pair;
import tasks.data_structures.Stack;
import tasks.puzzle.Direction;
import tasks.puzzle.labyrinth.tiles.*;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class LabyrinthNode {
    private Tile[][] map;
    private List<LabyrinthNode> children;
    private final Pair<Integer, Integer> playerCoordinates;
    private final Pair<Integer, Integer> goalCoordinates;
    public final LabyrinthNode parent;
    public final double pathCost;

    public LabyrinthNode(Tile[][] map, Pair<Integer, Integer> playerCoordinates, Pair<Integer, Integer> goalCoordinates, LabyrinthNode parent, double moveCost) {
        this.map = map;
        this.children = null;
        this.playerCoordinates = playerCoordinates;
        this.goalCoordinates = goalCoordinates;
        this.parent = parent;
        this.pathCost = parent == null ? moveCost : parent.pathCost + moveCost;
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

    private Tile[][] traverseMap() {
        int oldRow = playerCoordinates.getValue1();
        int oldColumn = playerCoordinates.getValue2();
        map[oldRow][oldColumn].setHasBeenTraversed(true);
        return map;
    }

    private void tryMoveInDirection(
            Direction direction,
            Predicate<Pair<Integer, Integer>> predicate,
            Pair<Integer, Integer> newPlayerCoordinates
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
                traverseMap(),
                newPlayerCoordinates,
                goalCoordinates,
                this,
                currentTile.getCost(direction)
        );
        children.add(newNode);
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

    public boolean isGoal() {
        return goalCoordinates.equals(playerCoordinates);
    }

    public double evaluateState() {
        return distanceFromGoal() + pathCost;
    }

    public int distanceFromGoal() {
        return Math.abs(goalCoordinates.getValue1() - playerCoordinates.getValue1()) + Math.abs(goalCoordinates.getValue2() - playerCoordinates.getValue2());
    }

    public String pathFromStart() {
        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {
                tile.setHasBeenTraversed(false);
            }
        }
        LabyrinthNode currentNode = this;
        while (currentNode != null) {
            currentNode.traverseMap();
            currentNode = currentNode.parent;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("_".repeat(map[0].length * 2 + 1)).append(System.lineSeparator());
        for (Tile[] tiles : map) {
            StringJoiner stringJoiner = new StringJoiner("|", "|", "|");
            for (Tile tile : tiles) {
                stringJoiner.add(tile.toString());
            }
            stringBuilder.append(stringJoiner).append(System.lineSeparator());
        }
        stringBuilder.append("\u203E".repeat(map[0].length * 2 + 1)).append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
