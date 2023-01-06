package tasks.puzzle.labyrinth;

import tasks.Colors;
import tasks.Pair;

import java.io.FileNotFoundException;
import java.util.logging.*;

public class LabyrinthTest {
    public static void main(String[] args) {
        Logger consoleLogger = Logger.getLogger("consoleLogger");
        Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord logRecord) {
                return logRecord.getMessage();
            }
        };
        StreamHandler streamHandler = new StreamHandler(System.out, formatter);
        consoleLogger.setUseParentHandlers(false);
        consoleLogger.addHandler(streamHandler);

        try {
            Labyrinth labyrinth = new Labyrinth("./src/main/resources/labyrinth_map.csv", new Pair<>(6, 1), new Pair<>(5, 6));
            consoleLogger.info(labyrinth.findPath());

        } catch (FileNotFoundException e) {
            consoleLogger.info(e.getMessage());
        }
    }

}
