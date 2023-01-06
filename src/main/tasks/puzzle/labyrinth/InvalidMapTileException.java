package tasks.puzzle.labyrinth;

public class InvalidMapTileException extends RuntimeException {
    private String tileString;

    public InvalidMapTileException(String tileString) {
        this.tileString = tileString;
    }

    @Override
    public String getMessage() {
        return String.format("Invalid tile used in map! -> \"%s\"", tileString);
    }
}
