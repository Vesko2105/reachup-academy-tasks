package tasks.coin_game;

import tasks.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class CoinGame {
    private CoinGameNode startingNode;
    private int gameChecks;

    public CoinGame(boolean[] startingState) {
        startingNode = new CoinGameNode(startingState, true);
    }

    private Pair<CoinGameNode, Integer> minMax(CoinGameNode currentNode) {
        if (currentNode.isFinal()) {
            return new Pair<>(currentNode, currentNode.evaluate());
        }
        List<CoinGameNode> children = currentNode.getChildren();
        CoinGameNode firstChild = children.get(0);
        Pair<CoinGameNode, Integer> bestMove = new Pair<>(firstChild, minMax(firstChild).getValue2());

        for (int i = 1; i < children.size(); i++) {
            CoinGameNode child = children.get(i);
            Pair<CoinGameNode, Integer> bestChildMove = new Pair<>(
                    child,
                    minMax(child).getValue2()
            );
            bestMove = betterMove(bestMove, bestChildMove);
        }

        return bestMove;
    }

    private Pair<CoinGameNode, Integer> betterMove(Pair<CoinGameNode, Integer> move1, Pair<CoinGameNode, Integer> move2) {
        boolean isBetter;
        if (move1 == null) {
            return move2;
        }
        if (move2 == null) {
            return move1;
        }
        if (move1.getValue1().isMaximisingPlayerTurn) {
            isBetter = move1.getValue2() < move2.getValue2();
        } else {
            isBetter = move1.getValue2() > move2.getValue2();
        }

        return isBetter ? move1 : move2;
    }

    public String findOptimalMove() {
        StringJoiner solution = new StringJoiner(System.lineSeparator());
        CoinGameNode currentNode = startingNode;
        solution.add(Arrays.toString(currentNode.state));
        Pair<CoinGameNode, Integer> bestMove = minMax(startingNode);
        solution.add(String.format("Optimal move: %s | Score: %d", bestMove.getValue1().originMove.toString(), bestMove.getValue2()));
        solution.add(Arrays.toString(bestMove.getValue1().state));
        return solution.toString();
    }

    public String findOptimalGamePlaythrough() {
        StringJoiner solution = new StringJoiner(System.lineSeparator());
        CoinGameNode currentNode = startingNode;
        solution.add(Arrays.toString(currentNode.state));
        Pair<CoinGameNode, Integer> bestMove;
        do {
            bestMove = minMax(currentNode);
            solution.add(String.format("Optimal move: %s | Score: %d", bestMove.getValue1().originMove.toString(), bestMove.getValue2()));
            solution.add(Arrays.toString(bestMove.getValue1().state));
            currentNode = bestMove.getValue1();
        } while (!bestMove.getValue1().isFinal());
        return solution.toString();
    }
}
