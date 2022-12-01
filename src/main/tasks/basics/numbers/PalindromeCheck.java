package tasks.basics.numbers;

import java.util.Scanner;

public class PalindromeCheck {

    static boolean isPalindrome(long number) {
        return number == ReverseNumber.reversed(number);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        int number = scanner.nextInt();
        System.out.println(number + (isPalindrome(number) ? " is a palindrome." : " is not a palindrome."));
        System.out.println(isPalindrome(number) ? 1 : 0);
    }
}