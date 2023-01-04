package tasks;

import java.io.*;

class FileSystem {
    // TODO: 20.12.2022 г. Fix exception handling and resource cleaning. Extract logic into methods
    // TODO: 20.12.2022 г. Check out System.Logger

    static void printFiles(File directory, int tabulationCount, Writer writer) {
        try {
            writer.write("\uD83D\uDCC1 " + directory.getName() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Could not write to " + writer.getClass().getName());
        }
        File[] files = directory.listFiles();
        for(File file : files) {
            try {
                writer.write("\t".repeat(tabulationCount));
            } catch (IOException e) {
                throw new RuntimeException("Could not write to " + writer.getClass().getName());
            }
            if(file.isDirectory()) {
                printFiles(file, tabulationCount + 1, writer);
            } else {
                try {
                    writer.write(String.format("%s%s%n", "\uD83D\uDDCE ", file.getName()));
                } catch (IOException e) {
                    throw new RuntimeException("Could not write to " + writer.getClass().getName());
                }
            }
        }
    }

    public static void main(String[] args) {
        File directory = new File("./src");
        PrintWriter pr = new PrintWriter(System.out);
        File file = new File("./src/main/resources/file-system");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Could not ");
        }
        if (file.exists()) {
            FileWriter fr;
            try {
                fr = new FileWriter("./src/main/resources/file-system");
            } catch (IOException e) {
                throw new RuntimeException("Could not open " + file.getAbsolutePath());
            }
            printFiles(directory, 1, pr);
            printFiles(directory, 1, fr);
            pr.close();
            try {
                fr.close();
            } catch (IOException e) {
                throw new RuntimeException("Could not close connection to file " + file.getAbsolutePath());
            }
        }
    }
}