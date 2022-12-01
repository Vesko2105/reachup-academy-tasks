package tasks.basics.numbers;

import java.util.Scanner;

public class IsFibonacci {
    static boolean fibonacciHelper(int num, int lastFibonacci1, int lastFibonacci2) {
        if (num == 0 || num == 1) {
            return true;
        }
        int currentFibonacci = lastFibonacci1 + lastFibonacci2;
        if (num == currentFibonacci) {
            return false;
        } else if (num < currentFibonacci) {
            return false;
        } else {
            return fibonacciHelper(num, lastFibonacci2, currentFibonacci);
        }
    }

    static boolean isFibonacci(int num) {
        return fibonacciHelper(num, 1, 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        int number = scanner.nextInt();
        System.out.printf("%d is %sa fibonacci number.", number, isFibonacci(number) ? "" : "not s");
    }
}
