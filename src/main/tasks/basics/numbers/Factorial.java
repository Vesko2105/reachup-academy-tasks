package tasks.basics.numbers;

import java.util.Scanner;

public class Factorial {
    public static long fact(int number) {
        if (number == 0) {
            return 1;
        }  else if (number < 0) {
            return fact(number * -1);
        } else if (number == 1) {
            return 1;
        } else {
            return number * fact(number - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        System.out.printf("fact(%d) = %d%n", number, fact(number));

    }
}
