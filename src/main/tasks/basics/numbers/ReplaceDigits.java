package tasks.basics.numbers;

public class ReplaceDigits {
    static long replaceDigits(long num, int searched, int replacement) {
        if(num / 10 == 0) {
            return (byte)(num == searched ? replacement : num);
        } else {
            return replaceDigits(num / 10, searched, replacement) * 10 + (num%10 == searched ? replacement : num % 10);
        }
    }



    public static void main(String[] args) {
        long num = 99999923499L;
        System.out.println(replaceDigits(num, 9, 1));
    }
}