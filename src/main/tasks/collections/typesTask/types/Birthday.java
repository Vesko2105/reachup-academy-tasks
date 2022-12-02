package tasks.collections.typesTask.types;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Birthday implements Type{
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static String exception = "Invalid date for the birthday field";
    @Override
    public List<String> validate(String value) {
        try {
            dateTimeFormatter.parse(value);
            return List.of();
        } catch (Exception e) {
            return List.of(exception);
        }
    }
}
