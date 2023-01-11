package tasks.collections.employeeTask;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class PersonAgeSortTest {
    public static void main(String[] args) {
        Person[] people = {
                new Person(
                        "1",
                        LocalDate.of(2001, 1, 1),
                        "Ivan",
                        "Aetrov"
                ),
                new Person(
                        "2",
                        LocalDate.of(2012, 1, 1),
                        "Petar",
                        "Ivanov"
                ),
                new Person(
                        "3",
                        LocalDate.of(2001, 1, 2),
                        "Georgi",
                        "Georgiev"
                ),
                new Person(
                        "4",
                        LocalDate.of(2001, 1, 1),
                        "Georgi",
                        "Pashjkbf"
                ),
                new Person(
                        "5",
                        LocalDate.of(2001, 1, 2),
                        "Ivan",
                        "Georgiev"
                ),
                new Person(
                        "6",
                        LocalDate.of(2005, 1, 2),
                        "Jasd",
                        "Ianfba"
                ),
                new Person(
                        "7",
                        LocalDate.of(1998, 1, 2),
                        "Jasd",
                        "Ianfba"
                )
        };
        StringJoiner stringJoiner = new StringJoiner(",");
        Person.sortByAge(Arrays.asList(people)).forEach(person -> stringJoiner.add(person.toString()));
        System.out.println(stringJoiner);
        StringJoiner stringJoiner2 = new StringJoiner(",");
        Person.filterAdult(Arrays.asList(people), LocalDate.now()).forEach(person -> stringJoiner2.add(person.toString()));
        System.out.println(stringJoiner2);
    }

}
