package tasks.collections.employeeTask;

import java.time.LocalDate;
import java.util.List;

public class EmployeeAverageExperienceTest {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(
                        LocalDate.now(),
                        "312",
                        "Ivancho",
                        "Petrov",
                        LocalDate.of(2018, 6, 12),
                        Department.ACCOUNTING, Level.JUNIOR
                ),
                new Employee(
                        LocalDate.now(),
                        "312",
                        "Ivancho",
                        "Petrov",
                        LocalDate.of(2017, 10, 5),
                        Department.ACCOUNTING, Level.JUNIOR
                ),
                new Employee(
                        LocalDate.now(),
                        "312",
                        "Ivancho",
                        "Petrov",
                        LocalDate.of(2015, 4, 1),
                        Department.ADMINISTRATION, Level.JUNIOR
                ),
                new Employee(
                        LocalDate.now(),
                        "312",
                        "Ivancho",
                        "Petrov",
                        LocalDate.of(2010, 9, 21),
                        Department.ADMINISTRATION, Level.JUNIOR),
                new Employee(
                        LocalDate.now(),
                        "312",
                        "Ivancho",
                        "Petrov",
                        LocalDate.of(2010, 9, 21),
                        Department.IT, Level.INTERN),
                new Employee(
                        LocalDate.now(),
                        "312",
                        "Ivancho",
                        "Petrov",
                        LocalDate.of(2010, 9, 21),
                        Department.ADMINISTRATION, Level.JUNIOR),
                new Employee(
                        LocalDate.now(),
                        "312",
                        "Ivancho",
                        "Petrov",
                        LocalDate.of(2010, 9, 21),
                        Department.ADMINISTRATION, Level.JUNIOR),
                new Employee(
                        LocalDate.now(),
                        "312",
                        "Ivancho",
                        "Petrov",
                        LocalDate.of(2010, 9, 21),
                        Department.HR, Level.JUNIOR),
                new Employee(
                        LocalDate.now(),
                        "312",
                        "Ivancho",
                        "Petrov",
                        LocalDate.of(2010, 9, 21),
                        Department.HR, Level.JUNIOR)
        );
        System.out.println(Employee.averageExperience(employees, LocalDate.now()));
    }
}
