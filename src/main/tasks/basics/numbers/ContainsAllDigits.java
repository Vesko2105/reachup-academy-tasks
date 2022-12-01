package tasks.basics.numbers;

public class ContainsAllDigits {
    static boolean containsAll(long number, long searched) {
        if (searched / 10 == 0) {
            return containsDigit(number, (int) searched);
        } else if (containsDigit(number, (int) searched % 10)) {
            return containsAll(number, searched / 10);
        } else {
            return false;
        }

    }

    static boolean containsDigit(long number, int digit) {
        if (number / 10 == 0) {
            return number == digit;
        } else if (number % 10 == digit) {
            return true;
        } else {
            return containsDigit(number / 10, digit);
        }
    }

    public static void main(String[] args) {
        System.out.println(containsAll(873254893, 0));
    }
}
