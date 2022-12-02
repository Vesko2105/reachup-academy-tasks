package tasks.algorithms;

import java.util.*;

public class Sortings {
    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    static int[] selectionSort(int[] numbers) {
        int currentPutIndex = 0;
        for (int i = 0; i < numbers.length; i++) {
            int minIndex = i;
            for (int j = minIndex; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            swap(numbers, currentPutIndex++, minIndex);
        }
        return numbers;
    }

    static int[] merge(int[] sortedArray1, int[] sortedArray2) {
        int[] merged = new int[sortedArray1.length + sortedArray2.length];
        int index1 = 0;
        int index2 = 0;
        for (int index = 0; index < merged.length; index++) {
            if (index1 == sortedArray1.length) {
                merged[index] = sortedArray2[index2++];
            } else if (index2 == sortedArray2.length) {
                merged[index] = sortedArray1[index1++];
            } else if (sortedArray1[index1] < sortedArray2[index2]) {
                merged[index] = sortedArray1[index1++];
            } else {
                merged[index] = sortedArray2[index2++];
            }
        }
        return merged;
    }

    static int[] mergeSort(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        if (end > start) {
            int middle = (start + end) / 2;
            int[] firstHalf = mergeSort(Arrays.copyOfRange(arr, start, middle + 1));
            int[] secondHalf = mergeSort(Arrays.copyOfRange(arr, middle + 1, end + 1));
            return merge(firstHalf, secondHalf);
        } else {
            return arr;
        }
    }


    static int binarySearch(int[] arr, int searched) {
        int midIndex = arr.length / 2;
        System.out.printf("Searching %s with middle index - %d%n", Arrays.toString(arr), midIndex);
        if (arr[midIndex] == searched) {
            System.out.printf("Found the number in %s at position %d%n", Arrays.toString(arr), midIndex);
            return midIndex;
        } else if (arr[midIndex] > searched) {
            System.out.printf("Searched number is not at the middle index %d%n", midIndex);
            System.out.printf("Searching in left half [%d; %d] of %s%n", 0, midIndex, Arrays.toString(arr));
            return binarySearch(Arrays.copyOfRange(arr, 0, midIndex), searched);
        }
        System.out.printf("Searched number is not at the middle index %d%n", midIndex);
        System.out.printf("Searching in left right [%d; %d] of %s%n", midIndex, arr.length - 1, Arrays.toString(arr));
        int positionInRightHalf = binarySearch(Arrays.copyOfRange(arr, midIndex, arr.length), searched);
        if (positionInRightHalf > -1) {
            System.out.printf("Found in right half [%d; %d] of %s%n", midIndex, arr.length - 1, Arrays.toString(arr));
            System.out.printf("Returning index in actual array - %d%n", positionInRightHalf + midIndex + 1);
            return positionInRightHalf + midIndex + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] sortedArray = {3, 4, 4, 5, 6, 23, 42, 65, 123, 231, 234, 235, 423, 543, 1234, 5667, 78978};
//        int[] unsortedArray = {3, 5, 1234, 543, 123, 235, 23, 42, 423, 4, 4, 234, 231, 6, 5667, 78978, 65};
//        int[] sortedArray2 = selectionSort(new int[]{3, 5, 1234, 543, 123, 235, 23, 42});
//        int[] sortedArray3 = selectionSort(new int[]{423, 4, 4, 234, 231, 6, 5667, 78978, 65});
//        LinkedList<Integer> linkedList1 = new LinkedList<>(Arrays.stream(sortedArray2).boxed().toList());
//        LinkedList<Integer> linkedList2 = new LinkedList<>(Arrays.stream(sortedArray3).boxed().toList());
        System.out.println(binarySearch(sortedArray, 1234));
    }
}
