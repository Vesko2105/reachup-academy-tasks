package tasks.collections;

import java.util.*;
import java.util.stream.Collectors;

public class DigitOccurrence {
    static HashMap<Byte, Byte> countDigits(long number) {
        HashMap<Byte, Byte> map = new LinkedHashMap<>();
        number = Math.abs(number);
        while (number > 0) {
            byte currentDigit = (byte) (number % 10);
            byte occurrences = map.getOrDefault(currentDigit, (byte) 0);
            map.put(currentDigit, (byte) (occurrences + 1));
            number /= 10;
        }
        List<Map.Entry<Byte, Byte>> entries = new ArrayList<>(map.entrySet().stream().toList());
        Collections.reverse(entries);
        map = entries.stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(Collectors.toMap(Map.Entry::getKey,
                                          Map.Entry::getValue,
                                          (k1, k2) -> k1,
                                          LinkedHashMap::new));
        return map;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        long number = scanner.nextLong();
        HashMap<Byte, Byte> map = countDigits(number);
        map.forEach((key, value) -> {
            System.out.printf("%d -> %d%n", key, value);
        });
    }
}
