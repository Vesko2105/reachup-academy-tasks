package tasks.basics.numeral_systems;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumericSystems {
    public static String decimalToBinary(long number) {
        Deque<Character> binary = new ArrayDeque<>();
        while (number > 0) {
            binary.push((char) ('0' + number % 2));
            number/=2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while(!binary.isEmpty()) {
            stringBuilder.append(binary.pop());
        }
        return stringBuilder.toString();
    }

    public static long binaryToDecimal(String binary) {
        int power = 0;
        int result = 0;
        while(binary.length() > 0) {
            result += (binary.charAt(binary.length() - 1) - '0') * Math.pow(2, power);
            binary = binary.substring(0, binary.length() - 1);
            power++;
        }
        return result;
    }

    public static String decimalToPnary(long number, int systemBase) {
        Deque<Character> binary = new ArrayDeque<>();
        while (number > 0) {
            char newChar = (char) (number % systemBase < 10 ? '0' + number % systemBase : 'A' + number % systemBase - 10);
            binary.push(newChar);
            number/=systemBase;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while(!binary.isEmpty()) {
            stringBuilder.append(binary.pop());
        }
        return stringBuilder.toString();
    }

    public static String decimalToRoman(long number) {
        // TODO: 23.11.2022 г.
        return null;
    }

    public static void main(String[] args) {
        System.out.printf("DEC: %d -> BIN: %s%n", 5782, decimalToBinary(5782));
        System.out.printf("DEC: %d -> HEX: %s%n", 5799, decimalToPnary(5799, 21));
        System.out.printf("BIN: %s -> DEC: %d%n", "1011010010110", binaryToDecimal("1011010010110"));
    }
}
