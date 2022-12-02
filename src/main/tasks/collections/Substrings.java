package tasks.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Substrings {
    static HashMap<Integer, List<String>> anagramsFor(String word) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        int length = word.length();
        while (length > 0) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i <= word.length() - length; i++) {
                list.add(word.substring(i, i + length));
            }
            map.put(length, list);
            length--;
        }
        map.put(0, List.of(""));
        return map;
    }

    static HashMap<Integer, List<String>> anagrams(String word) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        for (int currentLength = 1; currentLength <= word.length(); currentLength++) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < word.length() - currentLength + 1; i++) {
                list.add(word.substring(i, currentLength + i));
            }
            map.put(currentLength, list);
        }
        map.put(0, List.of(""));
        return map;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter word: ");
        String word = scanner.next();
        HashMap<Integer, List<String>> map = anagrams(word);
        map.forEach((key, value) -> {
            System.out.print(key + " -> {");
            for (int i = 0; i < value.size() - 1; i++) {
                System.out.printf("%s, ", value.get(i));
            }
            System.out.printf("%s}", value.get(value.size() - 1));
            System.out.println();
        });
    }
}
