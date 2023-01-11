package tasks.oop.fraction;

public class InvalidDenominatorException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Denominator cannot be 0!";
    }
}
