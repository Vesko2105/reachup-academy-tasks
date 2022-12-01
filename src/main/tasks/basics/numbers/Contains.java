package tasks.basics.numbers;

import java.util.Scanner;

public class Contains {
    //For-loop
    static boolean containsForLoop(long number, long searched) {
        while (number != 0) {
            long copy = number;
            int devider = 10;
            while (copy != 0) {
                if (copy % devider == searched) {
                    return true;
                } else {
                    devider *= 10;
                    copy /= 10;
                }
            }
            number /= 10;
        }
        return true;
    }

    //Tail-recursion
    static boolean containsTailRecursionHelper(long number, long searched, int divider) {
        if (number < searched) {
            return false;
        } else if (number % divider == searched) {
            return true;
        } else if (divider > searched) {
            return containsTailRecursionHelper(number/10, searched, 10);
        } else {
            return containsTailRecursionHelper(number, searched, divider * 10);
        }
    }

    static boolean containsTailRecursion(long number, long searched) {
        return containsTailRecursionHelper(number, searched, 10);
    }

    //Recursion
    static boolean containsRecursion(long number, long searched) {
        if (searched > number) {
            return false;
        } else if (isLast(number, searched)) {
            return true;
        } else {
            return containsRecursion(number / 10, searched);
        }
    }

    static boolean isLast(long number, long searched) {
        if (searched > number) {
            return false;
        } else if (number % 10 == searched) {
            return true;
        } else if (number % 10 == searched % 10) {
            return isLast(number / 10, searched / 10);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        long number = scanner.nextLong();
        System.out.print("Enter searched: ");
        long searched = scanner.nextLong();
        boolean result = containsRecursion(number, searched);
        System.out.printf("%d %s in %d%n", searched, result ? "is contained" : "is not contained", number);
    }
}
