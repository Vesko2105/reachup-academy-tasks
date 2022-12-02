package tasks.collections.employeeTask;

import java.time.LocalDate;
import java.util.List;

public class PersonAgeSortTest {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person(
                        LocalDate.of(2001, 1, 1),
                        "1",
                        "Ivan",
                        "Petrov"
                ),
                new Person(
                        LocalDate.of(2012, 1, 1),
                        "2",
                        "Petar",
                        "Ivanov"
                ),
                new Person(
                        LocalDate.of(2001, 1, 2),
                        "3",
                        "Georgi",
                        "Georgiev"
                )
        );
        List<Person> sorted = Person.sortPeopleByAge(people);
        sorted.forEach(System.out::println);
    }

}
