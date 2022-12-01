package tasks.basics.numbers;

public class IsPrime {
    private static boolean isPrimeHelper(int number, int divider) {
        if (number == 1 || number == 2)
            return true;
        if (number % 2 == 0) {
            return false;
        }
        if (divider == number) {
            return true;
        }
        if (number % divider == 0) {
            return false;
        }

        return isPrimeHelper(number, divider + 2);
    }

    public static boolean isPrime(int number) {
        return isPrimeHelper(number, 3);
    }

    public static void main(String[] args) {
        System.out.print(isPrime(37));
    }
}
