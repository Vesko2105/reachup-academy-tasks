package tasks.basics.numbers;

import java.util.Scanner;

public class ReverseNumber {
    public static long reversed(long number) {
        int digit = (int) number % 10;
        if (number / 10 == 0) {
            return digit;
        }
        double powerOf10 = Math.log10(number);
        return digit * (long) Math.pow(10, powerOf10) + reversed(number / 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        long number = scanner.nextLong();
        System.out.printf("%d reversed is -> %d%n", number, reversed(number));
    }
}
