package tasks.coin_game;

import tasks.Utils;
import tasks.data_structures.Queue;
import tasks.data_structures.Stack;

import java.util.Comparator;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class CoinGame {
    private CoinGameNode startingNode;

    public CoinGame(boolean[] startingState) {
        startingNode = new CoinGameNode(startingState, true);
    }

    private CoinGameNode minMax(CoinGameNode currentNode) {
        if (currentNode.isGoal()) {
            return currentNode;
        }
        if (currentNode.isMaximisingPlayerTurn) {
            return currentNode.getChildren()
                    .stream()
                    .map(this::minMax)
                    .max(Comparator.comparingInt(CoinGameNode::evaluate))
                    .get();
        } else {
            return currentNode.getChildren()
                    .stream()
                    .map(this::minMax)
                    .min(Comparator.comparingInt(CoinGameNode::evaluate))
                    .get();
        }
    }

    public String findOptimalMove() {
        Queue<CoinGameNode> front = new Queue<>();
        Logger logger = Utils.getFileLogger("./src/main/resources/min_max.log");
        logger.info("All children:");
        front.add(startingNode);
        while (!front.isEmpty()) {
            CoinGameNode currentNode = front.pop();
            logger.info(currentNode::toString);
            currentNode.getChildren().forEach(front::push);
        }
        StringJoiner solution = new StringJoiner(System.lineSeparator());
        Stack<CoinGameNode> stack = new Stack<>();
        CoinGameNode currentNode = minMax(startingNode);
        while (currentNode.parent != null) {
            stack.push(currentNode);
            currentNode = currentNode.parent;
        }
        stack.add(currentNode);
        while (!stack.isEmpty()) {
            solution.add(stack.pop().toString());
        }
        return solution.toString();
    }
}
