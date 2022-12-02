package tasks.algorithms;

public class Fibonacci {
    static int fibonacciNumberMemoization(int index, int[] fibonacciNumbers) {
        int arrayIndex = index - 1;
        if (index == 1) {
            return 0;
        } else if (index == 2) {
            return 1;
        } else if (fibonacciNumbers[arrayIndex] != 0){
            return fibonacciNumbers[arrayIndex];
        } else {
            fibonacciNumbers[arrayIndex] = fibonacciNumberMemoization(index - 1, fibonacciNumbers) + fibonacciNumberMemoization(index - 2, fibonacciNumbers);
            return fibonacciNumbers[arrayIndex];
        }
    }
    static int fibonacciNumber(int index) {
        return fibonacciNumberHelper(index, 3, 0, 1);
    }

    private static int fibonacciNumberHelper(int searchedIndex, int currentIndex, int fibonacci1, int fibonacci2) {

        if (searchedIndex == 1) {
            return 0;
        } else if (searchedIndex == 2) {
            return 1;
        } else if (currentIndex == searchedIndex){
            return fibonacci1 + fibonacci2;
        } else {
            return fibonacciNumberHelper(searchedIndex, ++currentIndex, fibonacci2, fibonacci1 + fibonacci2);
        }
    }

    public static void main(String[] args) {
        System.out.println(fibonacciNumberMemoization(10, new int[10]));
    }
}
