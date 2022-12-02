package tasks.collections;

import tasks.Utils;

import java.util.List;

public class ReplaceNotPrime {
    static List<Integer> filterNotPrime(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> {
                    if (!Utils.isPrime(number)) {
                        return 1;
                    } else {
                        return number;
                    }
                })
                .toList();
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(filterNotPrime(numbers));
    }
}
