package tasks.coin_game;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class State {
    public final boolean[] coins;
    public final boolean isMaximisingPlayerTurn;

    private final int totalTakes;

    private List<State> children;

    public final State parent;

    public final Move originMove;

    public State(boolean[] coins, boolean isMaximisingPlayerTurn) {
        this.coins = coins;
        this.originMove = null;
        this.isMaximisingPlayerTurn = isMaximisingPlayerTurn;
        this.totalTakes = 0;
        this.parent = null;
    }

    public State(boolean[] coins, Move originMove, boolean isMaximisingPlayerTurn, int totalTakes, State parent) {
        this.coins = coins;
        this.originMove = originMove;
        this.isMaximisingPlayerTurn = isMaximisingPlayerTurn;
        this.totalTakes = totalTakes;
        this.parent = parent;
    }

    public List<State> getChildren() {
        if (children != null) {
            return children;
        }
        children = new LinkedList<>();
        children.addAll(take1Coin());
        children.addAll(take2Coins());
        children.addAll(take3Coins());
        return children;
    }

    private List<State> take1Coin() {
        List<State> newChildren = new LinkedList<>();
        for (int i = 0; i < coins.length; i++) {
            if (!coins[i]) {
                boolean[] child = coins.clone();
                child[i] = true;
                newChildren.add(new State(child, new Move(i, 1, isMaximisingPlayerTurn), !isMaximisingPlayerTurn, totalTakes + 1, this));
            }
        }
        return newChildren;
    }
    private List<State> take2Coins() {
        List<State> newChildren = new LinkedList<>();
        for (int i = 0; i < coins.length; i++) {
            if (!coins[i] && !coins[(i + 1) % coins.length]) {
                boolean[] child = coins.clone();
                child[i] = true;
                child[(i + 1) % coins.length] = true;
                newChildren.add(new State(child, new Move(i, 2, isMaximisingPlayerTurn), !isMaximisingPlayerTurn, totalTakes + 1, this));
            }
        }
        return newChildren;
    }
    private List<State> take3Coins() {
        List<State> newChildren = new LinkedList<>();
        for (int i = 0; i < coins.length; i++) {
            if (!coins[i] && !coins[(i + 1) % coins.length] && !coins[(i + 2) % coins.length ]) {
                boolean[] child = coins.clone();
                child[i] = true;
                child[(i + 1) % coins.length] = true;
                child[(i + 2) % coins.length] = true;
                newChildren.add(new State(child, new Move(i, 3, isMaximisingPlayerTurn), !isMaximisingPlayerTurn, totalTakes + 1, this));
            }
        }
        return newChildren;
    }

    public boolean isFinal() {
        for (boolean b : coins) {
            if(!b) {
                return false;
            }
        }
        return true;
    }

    public int evaluate() {
        return (isMaximisingPlayerTurn ? -1 : 1) * (coins.length - totalTakes + coinsCount());
    }

    private int coinsCount() {
        int coinsCount = 0;
        for (boolean b : coins) {
            if(!b) {
                coinsCount++;
            }
        }
        return coinsCount;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (boolean b : coins) {
            stringJoiner.add(b ? " " : "\u25EF");
        }
        return stringJoiner.toString();
    }
}
