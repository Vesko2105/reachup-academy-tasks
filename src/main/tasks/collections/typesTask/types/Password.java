package tasks.collections.typesTask.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Password implements Type {
    //Should contain at least one lower case, one capital letter, one digit and one special character.
    //Should be at least 8 symbols long.
    private static final int passwordLength = 8;
    private static final Map<String, String> regexes = Map.of(
            "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)", "Password must contain at least one lower case, one capital letter, one digit and one special character",
            String.format(".{%d,})", passwordLength), String.format("Password must contain at least %d characters", passwordLength)
    );

    @Override
    public List<String> validate(String value) {
        List<String> exceptions = new ArrayList<>();
        regexes.forEach((regex, exception) -> {
            if (!value.matches(regex)) {
                exceptions.add(exception);
            }
        });
        return exceptions;
    }
}
