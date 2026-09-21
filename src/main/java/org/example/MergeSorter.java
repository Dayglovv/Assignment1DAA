package org.example;

public class MergeSorter {
    private int[] temp;
    private int recursionDepth;
    private int maxRecursionDepth;
    private long comparisons;

    private static final int INSERTION_SORT_LIMIT = 10;

    public void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        temp = new int[array.length];
        recursionDepth = 0;
        maxRecursionDepth = 0;
        comparisons = 0;

        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        recursionDepth++;

        if (recursionDepth > maxRecursionDepth) {
            maxRecursionDepth = recursionDepth;
        }

        if (right - left + 1 <= INSERTION_SORT_LIMIT) {
            insertionSort(array, left, right);
            recursionDepth--;
            return;
        }

        int middle = (left + right) / 2;

        mergeSort(array, left, middle);
        mergeSort(array, middle + 1, right);

        merge(array, left, middle, right);

        recursionDepth--;
    }

    private void merge(int[] array, int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            comparisons++;

            if (array[i] <= array[j]) {
                temp[k] = array[i];   // исправлено
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }

            k++;
        }

        while (i <= middle) {
            temp[k] = array[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = array[j];
            j++;
            k++;
        }

        for (int x = left; x <= right; x++) {
            array[x] = temp[x];
        }
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int current = array[i];
            int j = i - 1;

            while (j >= left && array[j] > current) {
                comparisons++;
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = current;
        }
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public long getComparisons() {
        return comparisons;
    }
}