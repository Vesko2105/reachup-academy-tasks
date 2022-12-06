package tasks.algorithms;

import java.util.Arrays;
import java.util.LinkedList;

public class Sortings {
    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static int[] bubbleSort(int[] array) {
        int[] arrayToSort = array.clone();
        for (int currentElement = 0; currentElement < arrayToSort.length; currentElement++) {
            for (int i = 0; i < arrayToSort.length - currentElement - 1; i++) {
                if(arrayToSort[i] > arrayToSort[i + 1]) {
                    swap(arrayToSort, i, i+1);
                }
            }
        }
        return arrayToSort;
    }

    public static int[] selectionSort(int[] array) {
        int[] arrayToSort = array.clone();
        int currentPutIndex = 0;
        for (int i = 0; i < arrayToSort.length; i++) {
            int minIndex = i;
            for (int j = minIndex; j < arrayToSort.length; j++) {
                if (arrayToSort[j] < arrayToSort[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arrayToSort, currentPutIndex++, minIndex);
        }
        return arrayToSort;
    }

    private static int[] merge(int[] sortedArray1, int[] sortedArray2) {
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

    public static int[] mergeSort(int[] array) {
        int start = 0;
        int end = array.length - 1;
        if (end > start) {
            int middle = (start + end) / 2;
            int[] firstHalf = mergeSort(Arrays.copyOfRange(array, start, middle + 1));
            int[] secondHalf = mergeSort(Arrays.copyOfRange(array, middle + 1, end + 1));
            return merge(firstHalf, secondHalf);
        } else {
            return array;
        }
    }

    public static int[] insertionSort(int[] array) {
        int[] sortedArray = array.clone();
        for (int currentNumberIndex = 1; currentNumberIndex < sortedArray.length; currentNumberIndex++) {
            int smallerNumberIndex = currentNumberIndex - 1;
            while (smallerNumberIndex >= 0 && sortedArray[smallerNumberIndex] > sortedArray[smallerNumberIndex + 1]) {
                swap(sortedArray, smallerNumberIndex, smallerNumberIndex + 1);
                smallerNumberIndex--;
            }
        }
        return sortedArray;
    }

    private static int partition(int[] array, int start, int end) {
        int pivotIndex = start;
        int pivot = array[end];
        for (int i = start; i <= end-1; i++) {
            if (array[i] < pivot) {
                swap(array, i, pivotIndex++);
            }
        }
        swap(array, end, pivotIndex);
        return pivotIndex;
    }

    public static int[] quickSort(int[] array, int start, int end) {
        if (end > start) {
            int pivot = partition(array, start, end);
            quickSort(array, start, pivot - 1);
            quickSort(array, pivot + 1, end);
        }
        return array;
    }

    public static int binarySearch(int[] array, int searched) {
        int midIndex = array.length / 2;
        System.out.printf("Searching %s with middle index - %d%n", Arrays.toString(array), midIndex);
        if (array[midIndex] == searched) {
            System.out.printf("Found the number in %s at position %d%n", Arrays.toString(array), midIndex);
            return midIndex;
        } else if (array[midIndex] > searched) {
            System.out.printf("Searched number is not at the middle index %d%n", midIndex);
            System.out.printf("Searching in left half [%d; %d] of %s%n", 0, midIndex, Arrays.toString(array));
            return binarySearch(Arrays.copyOfRange(array, 0, midIndex), searched);
        }
        System.out.printf("Searched number is not at the middle index %d%n", midIndex);
        System.out.printf("Searching in left right [%d; %d] of %s%n", midIndex, array.length - 1, Arrays.toString(array));
        int positionInRightHalf = binarySearch(Arrays.copyOfRange(array, midIndex, array.length), searched);
        if (positionInRightHalf > -1) {
            System.out.printf("Found in right half [%d; %d] of %s%n", midIndex, array.length - 1, Arrays.toString(array));
            System.out.printf("Returning index in actual array - %d%n", positionInRightHalf + midIndex + 1);
            return positionInRightHalf + midIndex + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] sortedArray = {3, 4, 4, 5, 6, 23, 42, 65, 123, 231, 234, 235, 423, 543, 1234, 5667, 78978};
        int[] unsortedArray = {3, 5, 1234, 543, 123, 235, 23, 42, 423, 4, 4, 234, 231, 6, 5667, 78978, 65};
//        int[] sortedArray2 = selectionSort(new int[]{3, 5, 1234, 543, 123, 235, 23, 42});
//        int[] sortedArray3 = selectionSort(new int[]{423, 4, 4, 234, 231, 6, 5667, 78978, 65});
//        LinkedList<Integer> linkedList1 = new LinkedList<>(Arrays.stream(sortedArray2).boxed().toList());
//        LinkedList<Integer> linkedList2 = new LinkedList<>(Arrays.stream(sortedArray3).boxed().toList());
//        partition(unsortedArray, 0, unsortedArray.length - 1);
        quickSort(unsortedArray, 0, unsortedArray.length - 1);
        for (int num : unsortedArray) {
            System.out.print(num    + " ");
        }
    }
}
