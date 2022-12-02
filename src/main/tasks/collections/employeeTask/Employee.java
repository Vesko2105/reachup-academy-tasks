package tasks.collections.employeeTask;

import tasks.Utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Employee extends Person {
    LocalDate entryDate;
    Department department;
    Level level;

    public Employee(LocalDate birthDate, String id, String firstName, String lastName, LocalDate entryDate, Department department, Level level) {
        super(birthDate, id, firstName, lastName);
        this.entryDate = entryDate;
        this.level = level;
        this.department = department;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public double experienceUpTo(LocalDate date) {
        return ChronoUnit.DAYS.between(entryDate, date) / 365.0;
    }

    static Map<Department, Double> averageExperience(List<Employee> employees, LocalDate date) {
        ToDoubleFunction<Employee> getExperience = employee -> employee.experienceUpTo(date);
        Collector<Employee, List<Double>, Double> averageExperienceCollector = new Collector<>() {
            @Override
            public Supplier<List<Double>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<List<Double>, Employee> accumulator() {
                return (accumulatedList, employee) -> accumulatedList.add(getExperience.applyAsDouble(employee));
            }

            @Override
            public BinaryOperator<List<Double>> combiner() {
                return null;
            }

            @Override
            public Function<List<Double>, Double> finisher() {
                return (accumulatedList) -> accumulatedList.stream()
                        .reduce((number1, number2) -> Utils.myRound(((number1 + number2) / 2.0), 2))
                        .orElse(0.0);
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Set.of(Characteristics.UNORDERED);
            }
        };

        return employees.stream()
                .filter(employee -> employee.getLevel() != Level.INTERN)
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        averageExperienceCollector))
                ;
    }
}
