package tasks.basics.numbers;

import java.util.ArrayList;
import java.util.Scanner;

public class Split {
    static ArrayList<Byte> split(long number) {
        ArrayList<Byte> list = new ArrayList<>();
        if (number / 10 == 0) {
            list.add((byte) number);
        } else {
            list.addAll(split(number / 10));
            list.add((byte) (number % 10));
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number = scanner.nextLong();
        System.out.println(split(number));
    }
}
