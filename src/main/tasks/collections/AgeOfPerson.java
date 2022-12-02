package tasks.collections;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AgeOfPerson {
//    Get from console input a birth date in format “1990:08:04”.
//    Calculate the person’s age in years, months and days and
//    return in a unified String “30 years, 3 months and 12 days”

    static String calculateAge(String date) {
        LocalDate birthDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        Period age = Period.between(birthDate, LocalDate.now());
        return String.format("%d years, %d months, %d days", age.getYears(), age.getMonths(), age.getDays());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date: ");
        String date = scanner.next();
        System.out.printf("The person is %s old.", calculateAge(date));
    }
}
