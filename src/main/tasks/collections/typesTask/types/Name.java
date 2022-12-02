package tasks.collections.typesTask.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Name implements Type{
    private Map<String, String> regexes = Map.of(
            ".{2,50}", "Invalid length for name field",
            "[a-zA-Z\\s]*", "Invalid tokens used for name field"
    );

    @Override
    public List<String> validate(String value) {
        List<String> exceptions = new ArrayList<>(regexes.size());
        regexes.forEach((regex, exception) -> {
            if (!value.matches(regex)) {
                exceptions.add(exception);
            }
        });
        return exceptions;
    }
}
