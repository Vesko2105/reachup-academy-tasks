package tasks.puzzle.tile_puzzle;

import tasks.Pair;
import tasks.Utils;
import tasks.puzzle.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

public class TilePuzzleNode {
    public final int DIMENSION;
    public final char[][] goalState;

    public final int stepAt;

    public Direction directionTakenFromParentNode;

    private char[][] state;

    private final TilePuzzleNode parent;

    private List<TilePuzzleNode> children;

    public TilePuzzleNode(
            int dimension,
            char[][] goalState,
            TilePuzzleNode parent,
            int stepAt,
            char[][] state,
            Direction directionTakenFromParentNode
    ) {
        this.DIMENSION = dimension;
        this.goalState = goalState;
        this.parent = parent;
        this.children = null;
        this.state = Utils.deepCopyOf(state);
        this.stepAt = stepAt;
        this.directionTakenFromParentNode = directionTakenFromParentNode;
    }

    public TilePuzzleNode getParent() {
        return parent;
    }

    public boolean isGoal() {
        return Arrays.deepEquals(state, goalState);
    }

    public List<TilePuzzleNode> getChildren() {
        int position = findEmpty();
        this.children = new ArrayList<>();
        tryUp(position);
        tryRight(position);
        tryDown(position);
        tryLeft(position);
        return this.children;
    }

    private Pair<Integer, Integer> getSymbolCoordinates(char symbol, char[][] state) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (symbol == state[i][j]) {
                    return new Pair<>(i, j);
                }
            }
        }
        return new Pair<>(-1, -1);
    }

    public int distanceFromGoal() {
        int distance = 0;
        for (char[] row : goalState) {
            for (char symbol : row) {
                Pair<Integer, Integer> goalCoordinates = getSymbolCoordinates(symbol, goalState);
                Pair<Integer, Integer> stateCoordinates = getSymbolCoordinates(symbol, state);
                distance += Math.abs(goalCoordinates.getValue1() - stateCoordinates.getValue1()) + Math.abs(goalCoordinates.getValue2() - stateCoordinates.getValue2());
            }
        }
        return distance;
    }

    private int findEmpty() {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] == 'X') {
                    return i * DIMENSION + j;
                }
            }
        }
        return -1;
    }

    private char[][] swap(int oldPosition, int newPosition) {
        int oldX = oldPosition % DIMENSION;
        int oldY = oldPosition / DIMENSION;
        int newX = newPosition % DIMENSION;
        int newY = newPosition / DIMENSION;
        char temp = state[oldY][oldX];
        char[][] toReturn = Utils.deepCopyOf(state);
        toReturn[oldY][oldX] = toReturn[newY][newX];
        toReturn[newY][newX] = temp;
        return toReturn;
    }

    private void tryMove(IntPredicate predicate, int positionOfEmpty, int newPositionOfEmpty, Direction direction) {
        if (predicate.test(positionOfEmpty)) {
            TilePuzzleNode newNode = new TilePuzzleNode(
                    DIMENSION,
                    goalState,
                    this,
                    stepAt + 1,
                    swap(positionOfEmpty, newPositionOfEmpty),
                    direction
            );
            if (!newNode.equals(parent)) {
                children.add(newNode);
            }
        }
    }

    private void tryUp(int positionOfEmpty) {
        tryMove((position) -> position / DIMENSION > 0,
                positionOfEmpty,
                positionOfEmpty - DIMENSION,
                Direction.NORTH
        );
    }

    private void tryDown(int positionOfEmpty) {
        tryMove((position) -> position / DIMENSION < DIMENSION - 1,
                positionOfEmpty,
                positionOfEmpty + DIMENSION,
                Direction.SOUTH
        );
    }

    private void tryLeft(int positionOfEmpty) {
        tryMove((position) -> position % DIMENSION > 0,
                positionOfEmpty,
                positionOfEmpty - 1,
                Direction.WEST
        );
    }

    private void tryRight(int positionOfEmpty) {
        tryMove((position) -> position % DIMENSION < DIMENSION - 1,
                positionOfEmpty,
                positionOfEmpty + 1,
                Direction.EAST
        );
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : state) {
            for (char symbol : row) {
                stringBuilder.append(symbol).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof TilePuzzleNode otherNode) {
            return Arrays.deepEquals(otherNode.state, this.state);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(state);
    }
}
