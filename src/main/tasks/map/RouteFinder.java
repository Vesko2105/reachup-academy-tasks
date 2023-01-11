package tasks.map;


import tasks.data_structures.PriorityQueue;

import java.util.Comparator;
import java.util.StringJoiner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class RouteFinder {
    CityDataProvider cityDataProvider;

    MapNode startingNode;
    MapNode destinationNode;

    private int nodesChecked;

    public RouteFinder(String citiesFilePath, String startingCity, String destinationCity) {
        this.cityDataProvider = new CityDataProvider(citiesFilePath);
        this.startingNode = new MapNode(cityDataProvider.getCity(startingCity), cityDataProvider.getCity(destinationCity));
    }

    public void findPath() {
        PriorityQueue<MapNode> frontForward = new PriorityQueue<>(Comparator.comparingInt(MapNode::evaluate));
        frontForward.add(startingNode);
        MapNode currentForwardNode;
        for (int i = 0; i < 100_000 && !frontForward.isEmpty(); i++) {
            currentForwardNode = frontForward.pop();
            nodesChecked++;
            if (currentForwardNode.isGoal()) {
                printPath(currentForwardNode);
                return;
            }
            currentForwardNode.getChildren(cityDataProvider).forEach(frontForward::add);
        }
    }

    private void printPath(MapNode goalNode) {
        Logger logger = Logger.getLogger("logger");
        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord logRecord) {
                return logRecord.getMessage();
            }
        };
        consoleHandler.setFormatter(formatter);
        logger.addHandler(consoleHandler);

        if (goalNode.isGoal()) {
            logger.info(() -> String.format("Found a route! Took %d steps.%n", nodesChecked));
            StringJoiner stringJoiner = new StringJoiner(" -> ");
            goalNode.getVisited().forEach(node -> stringJoiner.add(node.toString()));
            logger.info(() -> String.format("%s%n", stringJoiner));
            return;
        }
        logger.info("Could not find a route!");
    }
}
