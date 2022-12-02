package tasks.collections.typesTask;

import tasks.collections.typesTask.types.Type;
import tasks.collections.typesTask.types.TypeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldValidation {
    static Map<String, List<String>> validateFields(Map<String, String> fields) {
        Map<String, List<String>> map = new HashMap<>();
        fields.forEach((name, value) -> {
            List<String> exceptions = new ArrayList<>();
            Type currentType = TypeBuilder.getByName(name);
            if (currentType == null) {
                exceptions.add("Invalid field name");
            } else {
                exceptions.addAll(currentType.validate(value));
            }
            map.put(name, exceptions);
        });
        return map;
    }

    public static void main(String[] args) {
//        List<Map<String, String>> fieldsList = new ArrayList<>();
//        fieldsList.add(Map.of("name", "Ivan", "email", "username@domain.com"));
//        fieldsList.add(Map.of("name", "i", "email", "username@domain.com.bg"));
//        fieldsList.add(Map.of("name", "Ivan_The_Boneless"));
//        fieldsList.add(Map.of("name", "_", "ianshd", "jkasdh"));
//        fieldsList.forEach(fields -> {
//            System.out.println("Fields: ");
//            System.out.println(fields);
//            System.out.println("Validations: ");
//            System.out.println(validateFields(fields));
//            System.out.println("-------------");
//        });
    }
}
