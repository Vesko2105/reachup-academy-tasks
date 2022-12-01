package tasks.basics.numbers;

import java.util.Set;
import java.util.TreeSet;

public class SimpleDivisors {
    static Set<Long> getAllDivisors(long number, long currentDivisor) {
        if (currentDivisor == 1) {
            Set<Long> divisors = new TreeSet<>();
            divisors.add(currentDivisor);
            divisors.addAll(getAllDivisors(number / currentDivisor, currentDivisor + 1));
            return divisors;
        } else if (currentDivisor > Math.sqrt(number)) {
            return Set.of(number);
        } else if(number % currentDivisor == 0) {
            Set<Long> divisors = new TreeSet<>();
            divisors.add(currentDivisor);
            divisors.addAll(getAllDivisors(number / currentDivisor, currentDivisor));
            return divisors;
        } else {
            return getAllDivisors(number, currentDivisor + 1);
        }
    }

    static long smallestPrimeDivisor(long number, int currentDivisor) {
        if (currentDivisor > Math.sqrt(number)) {
            return number;
        } else {
            return smallestPrimeDivisor(number, currentDivisor + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(getAllDivisors(91208317, 1));
    }
}
