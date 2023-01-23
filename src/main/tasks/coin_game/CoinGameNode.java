package tasks.coin_game;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class CoinGameNode {
    public final boolean[] state;
    public final boolean isMaximisingPlayerTurn;

    private final int totalTakes;

    private List<CoinGameNode> children;

    public final CoinGameNode parent;

    public GameMove originMove;

    public CoinGameNode(boolean[] state, boolean isMaximisingPlayerTurn) {
        this.state = state;
        this.originMove = null;
        this.isMaximisingPlayerTurn = isMaximisingPlayerTurn;
        this.totalTakes = 0;
        this.parent = null;
    }

    public CoinGameNode(boolean[] state, GameMove originMove, boolean isMaximisingPlayerTurn, int totalTakes, CoinGameNode parent) {
        this.state = state;
        this.originMove = originMove;
        this.isMaximisingPlayerTurn = isMaximisingPlayerTurn;
        this.totalTakes = totalTakes;
        this.parent = parent;
    }

    public List<CoinGameNode> getChildren() {
        if (children != null) {
            return children;
        }
        children = new LinkedList<>();
        children.addAll(take1Coin());
        children.addAll(take2Coins());
        children.addAll(take3Coins());
        return children;
    }
    private List<CoinGameNode> take1Coin() {
        List<CoinGameNode> newChildren = new LinkedList<>();
        for (int i = 0; i < state.length; i++) {
            if (!state[i]) {
                boolean[] child = state.clone();
                child[i] = true;
                newChildren.add(new CoinGameNode(child, new GameMove(i, 1, isMaximisingPlayerTurn), !isMaximisingPlayerTurn, totalTakes + 1, this));
            }
        }
        return newChildren;
    }
    private List<CoinGameNode> take2Coins() {
        List<CoinGameNode> newChildren = new LinkedList<>();
        for (int i = 0; i < state.length; i++) {
            if (!state[i] && !state[(i + 1) % state.length]) {
                boolean[] child = state.clone();
                child[i] = true;
                child[(i + 1) % state.length] = true;
                newChildren.add(new CoinGameNode(child, new GameMove(i, 2, isMaximisingPlayerTurn), !isMaximisingPlayerTurn, totalTakes + 1, this));
            }
        }
        return newChildren;
    }
    private List<CoinGameNode> take3Coins() {
        List<CoinGameNode> newChildren = new LinkedList<>();
        for (int i = 0; i < state.length; i++) {
            if (!state[i] && !state[(i + 1) % state.length] && !state[(i + 2) % state.length ]) {
                boolean[] child = state.clone();
                child[i] = true;
                child[(i + 1) % state.length] = true;
                child[(i + 2) % state.length] = true;
                newChildren.add(new CoinGameNode(child, new GameMove(i, 3, isMaximisingPlayerTurn), !isMaximisingPlayerTurn, totalTakes + 1, this));
            }
        }
        return newChildren;
    }

    public boolean isFinal() {
        for (boolean b : state) {
            if(!b) {
                return false;
            }
        }
        return true;
    }

    public int evaluate() {
        return (isMaximisingPlayerTurn ? -1 : 1) * (state.length - totalTakes);
    }

    private int getCoinsLeft() {
        int coins = 0;
        for (boolean b : state) {
            if(!b) {
                coins++;
            }
        }
        return coins;
    }

    @Override
    public String toString() {
        return String.format(
                "%s Steps: %d | Player: %s | Score: %d",
                visualiseCoins(),
                totalTakes,
                isMaximisingPlayerTurn ? "Max" : "Min",
                evaluate()
        );
    }

    private String visualiseCoins() {
        StringJoiner coins = new StringJoiner(",", "[", "]");
        for (boolean b : state) {
            coins.add(b ? " " : "\u25EF");
        }
        return coins.toString();
    }
}
