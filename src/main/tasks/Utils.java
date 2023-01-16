package tasks;

import tasks.basics.numbers.Factorial;
import tasks.basics.numbers.IsPrime;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

public class Utils {

    private static int loggerID = 0;

    private Utils() {

    }

    public static boolean isPrime(int number) {
        return IsPrime.isPrime(number);
    }

    public static double myRound(double value, int places) {
        System.out.println("value = " + value + ", places = " + places);
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public static long fact(int number) {
        return Factorial.fact(number);
    }

    public static char[][] deepCopyOf(char[][] matrix) {
        char[][] matrixClone = new char[matrix.length][matrix[0].length];
        for (int i = 0; i < matrixClone.length; i++) {
            matrixClone[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return matrixClone;
    }

    public static <T> void deepCopyOf(T[][] matrix, T[][] inputMatrix) {
        for (int i = 0; i < inputMatrix.length; i++) {
            inputMatrix[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
    }

    public static Logger getConsoleLogger() {
        Logger logger = Logger.getLogger("logger" + loggerID);
        loggerID++;
        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + System.lineSeparator();
            }
        };
        consoleHandler.setFormatter(formatter);
        logger.addHandler(consoleHandler);
        return logger;
    }

    public static Logger getFileLogger(String filePath) {
        Logger logger = Logger.getLogger("logger" + loggerID);
        loggerID++;
        logger.setUseParentHandlers(false);
        FileHandler fileHandler;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileHandler = new FileHandler(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord logRecord) {
                return logRecord.getMessage() + System.lineSeparator();
            }
        };
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);
        return logger;
    }
}
