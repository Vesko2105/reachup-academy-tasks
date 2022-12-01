package tasks.basics.numbers;

import java.util.Scanner;

class OddOrEven {
    static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static void oddOrEven(int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        int num = scanner.nextInt();
        System.out.println(isEven(num) ? "even" : "odd");
    }

    public static void main(String[] args) {
        oddOrEven(5);
    }
}