package tasks.coin_game;

import tasks.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public class CoinGame {
    private CoinGameNode startingNode;
    private int gameChecks;

    public CoinGame(boolean[] startingState) {
        startingNode = new CoinGameNode(startingState, true);
    }

    private Pair<CoinGameNode, Integer> minMaxAB(CoinGameNode currentNode, int alpha, int beta) {
        gameChecks++;
        if (currentNode.isFinal()) {
            return new Pair<>(currentNode, currentNode.evaluate());
        }
        Pair<CoinGameNode, Integer> bestMove = null;
        List<CoinGameNode> children = currentNode.getChildren();
        children.sort(
                currentNode.isMaximisingPlayerTurn ?
                        Comparator.comparingInt(CoinGameNode::evaluate) :
                        Comparator.comparingInt(CoinGameNode::evaluate).reversed()
        );
        for (CoinGameNode child : children) {
            Pair<CoinGameNode, Integer> bestChildMove = new Pair<>(
                    child,
                    minMaxAB(child, alpha, beta).getValue2()
            );
            bestMove = betterMove(bestMove, bestChildMove);
            CoinGameNode bestState = bestMove.getValue1();
            if (bestState.isMaximisingPlayerTurn) {
                alpha = Math.max(alpha, bestState.evaluate());
            } else {
                beta = Math.min(beta, bestState.evaluate());
            }
            if (alpha >= beta) {
                break;
            }
        }

        return bestMove;
    }

    private Pair<CoinGameNode, Integer> minMax(CoinGameNode currentNode) {
        gameChecks++;
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
            bestMove = minMaxAB(currentNode, -1000, 1000);
//            bestMove = minMax(currentNode);

            solution.add(String.format("Optimal move: %s | Score: %d", bestMove.getValue1().originMove.toString(), bestMove.getValue2()));
            solution.add(Arrays.toString(bestMove.getValue1().state));
            currentNode = bestMove.getValue1();
        } while (!bestMove.getValue1().isFinal());
        solution.add("Game checks: " + gameChecks);
        return solution.toString();
    }
}
