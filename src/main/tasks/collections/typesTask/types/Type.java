package tasks.collections.typesTask.types;

import java.util.List;

public interface Type {
    List<String> validate(String value);
//
//    NAME("name", Map.of(".{2,50}", "Invalid length", "[a-zA-Z\\s]*", "Invalid tokens used")),
//    EMAIL("email", Map.of("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", "Invalid email address")),
//    BIRTHDAY("birthday", Map.of("[\\d]{4}-(0[1-9]|1[0-2])-", "")),
//    PASSWORD("password", Map.of("", "")),
//    PHONE_NUMBER("email", Map.of("", ""));
//
//    String name;
//    Map<Predicate<String>, String> regexes;
//
//    Type(String name, Map<Predicate<String>, String> regexes) {
//        this.name = name;
//        this.regexes = regexes;
//    }
//
//    public static Type getByName(String name) {
//        try {
//            return valueOf(name.toUpperCase(Locale.ROOT));
//        } catch (Exception e) {
//            return null;
//        }
//    }
}