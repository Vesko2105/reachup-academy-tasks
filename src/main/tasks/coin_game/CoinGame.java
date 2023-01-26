package tasks.coin_game;

import tasks.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public class CoinGame {
    private final State startingState;
    private int gameChecks;

    public CoinGame(boolean[] startingCoins) {
        startingState = new State(startingCoins, true);
    }

    private Pair<State, Integer> minMaxAB(State currentState, int alpha, int beta) {
        gameChecks++;
        if (currentState.isFinal()) {
            return new Pair<>(currentState, currentState.evaluate());
        }
        Pair<State, Integer> bestMove = null;
        List<State> children = currentState.getChildren();
        children.sort(
                currentState.isMaximisingPlayerTurn ?
                        Comparator.comparingInt(State::evaluate).reversed() :
                        Comparator.comparingInt(State::evaluate)
        );
        for (State child : children) {
            Pair<State, Integer> bestChildMove = new Pair<>(
                    child,
                    minMaxAB(child, alpha, beta).getValue2()
            );
            bestMove = betterMove(bestMove, bestChildMove);
            State bestState = bestMove.getValue1();
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

    private Pair<State, Integer> minMax(State currentState) {
        gameChecks++;
        if (currentState.isFinal()) {
            return new Pair<>(currentState, currentState.evaluate());
        }
        List<State> children = currentState.getChildren();
        State firstChild = children.get(0);
        Pair<State, Integer> bestMove = new Pair<>(firstChild, minMax(firstChild).getValue2());

        for (int i = 1; i < children.size(); i++) {
            State child = children.get(i);
            Pair<State, Integer> bestChildMove = new Pair<>(
                    child,
                    minMax(child).getValue2()
            );
            bestMove = betterMove(bestMove, bestChildMove);
        }

        return bestMove;
    }

    private Pair<State, Integer> betterMove(Pair<State, Integer> move1, Pair<State, Integer> move2) {
        boolean isBetter;
        if (move1 == null) {
            return move2;
        }
        if (move2 == null) {
            return move1;
        }
        if (move1.getValue1().isMaximisingPlayerTurn) {
            isBetter = move1.getValue2() <= move2.getValue2();
        } else {
            isBetter = move1.getValue2() >= move2.getValue2();
        }

        return isBetter ? move1 : move2;
    }

    public String findOptimalMove() {
        StringJoiner solution = new StringJoiner(System.lineSeparator());
        State currentState = startingState;
        solution.add(Arrays.toString(currentState.coins));
        Pair<State, Integer> bestMove = minMax(startingState);
        solution.add(String.format("Optimal move: %s | Score: %d", bestMove.getValue1().originMove.toString(), bestMove.getValue2()));
        solution.add(Arrays.toString(bestMove.getValue1().coins));
        return solution.toString();
    }

    public String findOptimalGamePlaythrough() {
        StringJoiner solution = new StringJoiner(System.lineSeparator());
        State currentState = startingState;
        solution.add(currentState.toString());
        Pair<State, Integer> bestMove;
        do {
            bestMove = minMaxAB(currentState, -1000, 1000);
            currentState = bestMove.getValue1();
            solution.add(String.format("Optimal move: %s | Score: %d", currentState.originMove.toString(), bestMove.getValue2()));
            solution.add(currentState.toString());
        } while (!bestMove.getValue1().isFinal());
        solution.add("Game checks: " + gameChecks);
        return solution.toString();
    }
}
