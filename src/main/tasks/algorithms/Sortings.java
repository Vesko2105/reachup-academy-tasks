package tasks.algorithms;

import tasks.Utils;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.logging.Logger;

public class Sortings {
    private static final Logger logger = Utils.getConsoleLogger();
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

    private static Integer[] merge(Integer[] sortedArray1, Integer[] sortedArray2) {
        Integer[] merged = new Integer[sortedArray1.length + sortedArray2.length];
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

    public static Integer[] mergeSort(Integer[] array) {
        logger.info(() -> String.valueOf(array.length));
        if (array.length > 1) {
            int middle = (array.length) / 2;
            Integer[] firstHalf = mergeSort(Arrays.copyOfRange(array, 0, middle));
            Integer[] secondHalf = mergeSort(Arrays.copyOfRange(array, middle, array.length));
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
        if (array[midIndex] == searched) {
            return midIndex;
        } else if (array[midIndex] > searched) {
            return binarySearch(Arrays.copyOfRange(array, 0, midIndex), searched);
        }
        int positionInRightHalf = binarySearch(Arrays.copyOfRange(array, midIndex, array.length), searched);
        if (positionInRightHalf > -1) {
            return positionInRightHalf + midIndex + 1;
        }
        return -1;
    }
}
