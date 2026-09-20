package org.example;
public class DeterministicSelector {
    private long comparisons;
    private int recursionDepth;
    private int maxRecursionDepth;
    public int select(int[] array, int k) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException("Invalid k");
        }
        comparisons = 0;
        recursionDepth = 0;
        maxRecursionDepth = 0;
        return select(array, 0, array.length - 1, k);
    }

    private int select(int[] array, int left, int right, int k){
        recursionDepth++;
        if (recursionDepth > maxRecursionDepth) {
            maxRecursionDepth = recursionDepth;
        }
        if (left == right) {
            recursionDepth--;
            return array[left];
        }
        int pivot = medianOfMedians(array, left, right);
        int pivotIndex =
                partition(array, left, right, pivot);
        int result;
        if (k == pivotIndex) {
            result = array[pivotIndex];
        } else if (k < pivotIndex) {
            result = select(array, left, pivotIndex - 1, k);
        } else {
            result = select(array, pivotIndex + 1, right, k);
        }
        recursionDepth--;
        return result;
    }

    private int medianOfMedians(int[] array, int left, int right) {
        int size = right - left + 1;
        if (size <= 5) {
            insertionSort(array, left, right);
            return array[left + size / 2];
        }
        int medianCount = 0;
        for (int start = left; start <= right; start += 5) {
            int groupRight = Math.min(start + 4, right);
            insertionSort(array, start, groupRight);
            int median = array[start + (groupRight - start) / 2];
            array[left + medianCount] = median;
            medianCount++;
        }
        int middle = medianCount / 2;
        return select(array, left, left + medianCount - 1, left + middle);
    }

    private int partition(int[] array, int left, int right, int pivot){
        int pivotIndex = left;
        while (pivotIndex <= right
                && array[pivotIndex] != pivot) {
            pivotIndex++;
        }
        if (pivotIndex > right) {
            throw new IllegalStateException(
                    "Pivot not found"
            );
        }
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

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= left) {
                comparisons++;
                if (array[j] <= current) {
                    break;
                }
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
}