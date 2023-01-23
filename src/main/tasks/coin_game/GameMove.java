package tasks.coin_game;

public class GameMove {
    private int firstCoinIndex;
    private int coinCount;

    private boolean isMaximisingPlayerTurn;

    public GameMove(int firstCoinIndex, int coinCount, boolean isMaximisingPlayerTurn) {
        this.firstCoinIndex = firstCoinIndex;
        this.coinCount = coinCount;
        this.isMaximisingPlayerTurn = isMaximisingPlayerTurn;
    }

    @Override
    public String toString() {
        return String.format("%s takes %d coins starting from the %d",
                isMaximisingPlayerTurn ? "Player" : "Opponent",
                coinCount,
                firstCoinIndex
        );
    }
}
