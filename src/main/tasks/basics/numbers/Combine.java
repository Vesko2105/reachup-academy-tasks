package tasks.basics.numbers;

import java.util.Arrays;

public class Combine {
    static long combineNumbers(byte[] digits) {
        if (digits.length == 1) {
            return digits[0];
        } else {
            return combineNumbers(Arrays.copyOfRange(digits, 0, digits.length - 1)) * 10 + digits[digits.length - 1];
        }
    }

    public static void main(String[] args) {
        byte[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(combineNumbers(nums));
    }
}
