package tasks.puzzle.labyrinth;

import tasks.Pair;
import tasks.csv_reader.CSVReader;
import tasks.data_structures.PriorityQueue;
import tasks.puzzle.labyrinth.tiles.Tile;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Labyrinth {
    LabyrinthNode startingNode;
    int nodesChecked;
    int maxNodes;

    public Labyrinth(String mapFilePath, Pair<Integer> playerCoordinates, Pair<Integer> goalCoordinates) throws FileNotFoundException {
        Tile[][] map = parseMap(mapFilePath);
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

    private Tile[][] parseMap(String filePath) throws FileNotFoundException {
        Tile[][] tiles;
        try (CSVReader csvReader = new CSVReader(filePath, ",")){
            List<List<String>> tileStrings = csvReader.read(0, csvReader.gerRecordsCount());
            boolean hasMissingTiles = tileStrings.stream().map(List::size).distinct().count() != 1;
            if (hasMissingTiles) {
                throw new IncompleteMapException();
            }
            tiles = new Tile[tileStrings.size()][tileStrings.get(0).size()];
            int rowIndex = 0;
            int columnIndex = 0;
            for (List<String> row : tileStrings) {
                for (String tileString : row) {
                    tiles[rowIndex][columnIndex++] = Tile.getNew(tileString);
                }
                columnIndex = 0;
                rowIndex++;
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        return tiles;
    }

    public String findPath() {
        PriorityQueue<LabyrinthNode> front = new PriorityQueue<>(Comparator.comparingDouble(LabyrinthNode::evaluateState));
        LabyrinthNode currentNode = startingNode;
        front.add(currentNode);

        for (int i = 0; i < 1_000_000 && !front.isEmpty(); i++) {
            currentNode = front.pop();
            if (currentNode.isGoal()) {
                break;
            }
            nodesChecked++;
            List<LabyrinthNode> children = currentNode.getChildren();
            children.forEach(front::add);
            maxNodes = Math.max(front.size(), maxNodes);
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (currentNode == null || !currentNode.isGoal()) {
            stringBuilder.append(String.format("Did not find solution after traversing %d nodes%n", nodesChecked));
        } else {
            stringBuilder.append(String.format("Found solution by traversing %d.%n", nodesChecked));
            stringBuilder.append(String.format("The path has a total cost of %.1f.%n", currentNode.pathCost));
            stringBuilder.append(String.format("%n%s", currentNode.pathFromStart()));
        }
        return stringBuilder.toString();
    }
}
