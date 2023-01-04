package tasks.puzzle.labyrinth;

import tasks.Pair;
import tasks.data_structures.PriorityQueue;
import tasks.puzzle.labyrinth.tiles.Tile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Labyrinth {
    LabyrinthNode startingNode;
    int nodesChecked;
    int maxNodes;

    int skippedNodes;

    public Labyrinth(Tile[][] map, Pair<Integer> playerCoordinates, Pair<Integer> goalCoordinates) {
        map[playerCoordinates.getValue1()][playerCoordinates.getValue2()].setAsStartingPosition();
        map[playerCoordinates.getValue1()][playerCoordinates.getValue2()].setCurrentlyOccupied(true);
        map[goalCoordinates.getValue1()][goalCoordinates.getValue2()].setGoal(true);

        startingNode = new LabyrinthNode(
                map,
                playerCoordinates,
                goalCoordinates,
                null,
                0
        );
    }

    public void findPath() {
        PriorityQueue<LabyrinthNode> front = new PriorityQueue<>(Comparator.comparingDouble(LabyrinthNode::evaluateState));
        PriorityQueue<Double> front2 = new PriorityQueue<>(Double::compareTo);
        LabyrinthNode currentNode = startingNode;
        front.add(currentNode);
        front2.add(currentNode.evaluateState());

        // TODO: 4.1.2023 г. Learn about logging, file handlers, levels etc.
        Logger consoleLogger = Logger.getLogger("consoleLogger");
        Logger fileLogger = Logger.getLogger("fileLogger");
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("./src/main/resources/labyrinth.log");
            fileLogger.addHandler(fileHandler);
            fileLogger.setUseParentHandlers(false);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            fileLogger.info(System::lineSeparator);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 1_000_000 && !front.isEmpty(); i++) {
            currentNode = front.pop();
            if (currentNode.isGoal()) {
                break;
            }
            String msg = String.format("%n%s", currentNode);
            fileLogger.info(msg);
            nodesChecked++;
            List<LabyrinthNode> children = currentNode.getChildren();
            children.forEach(front::add);
            children.forEach(c -> front2.add(c.evaluateState()));
            maxNodes = Math.max(front.size(), maxNodes);
        }
        if (currentNode.isGoal()) {
            consoleLogger.info(() -> String.format("Found solution by traversing %d tiles and skipping %d.%n", nodesChecked, skippedNodes));
            LabyrinthNode goalNode = currentNode;
            consoleLogger.info(() -> String.format("The path has a total cost of %.1f.%n", goalNode.pathCost));
            consoleLogger.info(() -> String.format("%n%s", goalNode));
        } else {
            consoleLogger.info(() -> String.format("Did not find solution after traversing %d nodes%n", nodesChecked));
        }
    }
}
