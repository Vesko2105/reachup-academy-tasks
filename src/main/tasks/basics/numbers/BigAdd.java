package tasks.basics.numbers;

import java.util.Scanner;

public class BigAdd {
    static String bigAdd(String number1, String number2) {
        if (number1.length() > 0 && number2.length() == 0) {
            return number1;
        } else if (number2.length() > 0 && number1.length() == 0) {
            return number2;
        } else if (number1.length() == 0 && number2.length() == 0) {
            return "";
        }
        int currentDigit1 = Byte.parseByte(number1.substring(number1.length() - 1));
        int currentDigit2 = Byte.parseByte(number2.substring(number2.length() - 1));
        int currentDigitsSum = currentDigit1 + currentDigit2;
        if (currentDigitsSum > 9) {
            return bigAdd(number1.substring(0, number1.length() - 1), bigAdd(number2.substring(0, number2.length() - 1), "1")) + (currentDigitsSum - 10);
        } else {
            return bigAdd(number1.substring(0, number1.length() - 1), number2.substring(0, number2.length() - 1)) + currentDigitsSum;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(bigAdd(scanner.next(), scanner.next()));
    }
}
