package tasks.coin_game;

import tasks.Utils;

import java.util.logging.Logger;

public class CoinGameTest {
    public static void main(String[] args) {
        CoinGame game = new CoinGame(
                new boolean[]{true, true, true, true, false, false, false}
        );
        Logger logger = Utils.getConsoleLogger();
        logger.info(game::findOptimalGamePlaythrough);
    }
}