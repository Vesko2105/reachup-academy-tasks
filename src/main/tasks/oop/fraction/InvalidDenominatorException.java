package tasks.oop.fraction;

public class InvalidDenominatorException extends Exception{
    private final String message = "Denominator cannot be 0!";

    @Override
    public String getMessage() {
        return message;
    }
}
