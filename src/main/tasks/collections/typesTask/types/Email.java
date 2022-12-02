package tasks.collections.typesTask.types;

import java.util.List;

public class Email implements Type{
    private static final String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String exception = "Invalid email address";
    @Override
    public List<String> validate(String value) {
        if (!value.matches(regex)) {
            return List.of(exception);
        } else {
            return List.of();
        }
    }
}
