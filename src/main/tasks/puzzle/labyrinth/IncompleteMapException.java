package tasks.puzzle.labyrinth;

public class IncompleteMapException extends RuntimeException {
    @Override
    public String getMessage() {
        return "The provided map has missing tiles!";
    }
}
