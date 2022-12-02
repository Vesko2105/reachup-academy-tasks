package tasks.collections.typesTask.types;

import java.util.Locale;

public class TypeBuilder {
    public static Type getByName(String fieldName) {
        String fieldNameLowercase = fieldName.toLowerCase(Locale.ROOT);
        return switch (fieldNameLowercase) {
            case "name" : yield new Name();
            case "email" : yield new Email();
            case "birthday" : yield new Birthday();
            case "phonenumber" : yield new PhoneNumber();
            case "password" : yield new Password();
            default : yield null;
        };
    }
}
