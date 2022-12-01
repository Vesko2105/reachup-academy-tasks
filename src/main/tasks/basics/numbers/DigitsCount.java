package tasks.basics.numbers;

import java.util.Scanner;

public class DigitsCount {
    static byte digitsCount(long num) {
        if (num / 10 == 0) {
            return 1;
        } else {
            return (byte) (digitsCount(num / 10) + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        int number = scanner.nextInt();
        System.out.printf("%d has %d digits.", number, digitsCount(number));
    }
}
