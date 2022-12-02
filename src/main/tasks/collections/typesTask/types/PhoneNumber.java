package tasks.collections.typesTask.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhoneNumber implements Type {
    private static final Map<String, String> regexes = Map.of(
            "+[\\d]{9}", "Invalid phone number"
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
