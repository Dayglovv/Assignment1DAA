package org.example;
import java.util.Random;

public class QuickSorter {

    private final Random random = new Random();

    private int recursionDepth;
    private int maxRecursionDepth;
    private long comparisons;
    private long swaps;

    public void sort(int[] array) {

        if (array == null || array.length <= 1) {
            return;
        }

        recursionDepth = 0;
        maxRecursionDepth = 0;
        comparisons = 0;
        swaps = 0;

        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {

        while (left < right) {

            recursionDepth++;

            if (recursionDepth > maxRecursionDepth) {
                maxRecursionDepth = recursionDepth;
            }

            int pivotIndex = left + random.nextInt(right - left + 1);

            int newPivotIndex = partition(array, left, right, pivotIndex);

            // Find the smaller part
            if (newPivotIndex - left < right - newPivotIndex) {

                quickSort(array, left, newPivotIndex - 1);

                left = newPivotIndex + 1;

            } else {

                quickSort(array, newPivotIndex + 1, right);

                right = newPivotIndex - 1;
            }

            recursionDepth--;
        }
    }

    private int partition(int[] array, int left, int right, int pivotIndex) {

        int pivot = array[pivotIndex];

        swap(array, pivotIndex, right);

        int smallerIndex = left;

        for (int i = left; i < right; i++) {

            comparisons++;

            if (array[i] < pivot) {
                swap(array, i, smallerIndex);
                smallerIndex++;
            }
        }

        swap(array, smallerIndex, right);

        return smallerIndex;
    }

    private void swap(int[] array, int i, int j) {

        if (i == j) {
            return;
        }

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        swaps++;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }
}