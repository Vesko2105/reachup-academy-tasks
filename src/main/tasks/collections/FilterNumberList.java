package tasks.collections;
import java.util.List;

public class FilterNumberList {
    static List<Integer> filterEven(List<Integer> numbers) {
        return numbers.stream().filter((number) -> number % 2 != 0).toList();
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(filterEven(numbers));
    }
}
