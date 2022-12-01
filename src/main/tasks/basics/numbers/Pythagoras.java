package tasks.basics.numbers;

import java.util.Scanner;

public class Pythagoras {
    static double findThirdSide(float sideA, float sideB) {
        return Math.sqrt(sideA * sideA + sideB * sideB);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter side A: ");
        int sideA = scanner.nextInt();
        System.out.print("Enter side B: ");
        int sideB = scanner.nextInt();
        System.out.println("Side C is " + findThirdSide(sideA, sideB));
    }
}
