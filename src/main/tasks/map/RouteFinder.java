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

    public RouteFinder(String citiesFilePath, String startingCity, String destinationCity) {
        this.cityDataProvider = new CityDataProvider(citiesFilePath);
        this.startingNode = new MapNode(cityDataProvider.getCity(startingCity), cityDataProvider.getCity(destinationCity));
    }

    public void findPath() {
        PriorityQueue<MapNode> front = new PriorityQueue<>(Comparator.comparingInt(MapNode::evaluate));
        front.add(startingNode);
        MapNode currentNode;
        for (int i = 0; i < 100_000 && !front.isEmpty(); i++) {
            currentNode = front.pop();
            if (currentNode.isGoal()) {
                printPath(currentNode);
                return;
            }
            currentNode.getChildren(cityDataProvider).forEach(front::add);
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

        StringJoiner stringJoiner = new StringJoiner(" -> ");
        goalNode.getVisited().forEach(node -> stringJoiner.add(node.toString()));
        logger.info(stringJoiner::toString);
        logger.info("Could not find a route!");
    }
}
