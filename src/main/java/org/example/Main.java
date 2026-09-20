package org.example;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array1 = {
                15, 3, 8, 1, 12, 7, 20, 4,
                10, 6, 2, 18, 5, 14, 9, 11
        };
        int[] array2 = array1.clone();
        System.out.println("=== MERGE SORT ===");
        System.out.println("Before:");
        System.out.println(Arrays.toString(array1));
        MergeSorter mergeSorter = new MergeSorter();
        mergeSorter.sort(array1);
        System.out.println("After:");
        System.out.println(Arrays.toString(array1));
        System.out.println("Recursion depth: "
                + mergeSorter.getMaxRecursionDepth());
        System.out.println("Comparisons: "
                + mergeSorter.getComparisons());

        System.out.println();
        System.out.println("=== QUICK SORT ===");
        System.out.println("Before:");
        System.out.println(Arrays.toString(array2));
        QuickSorter quickSorter = new QuickSorter();
        quickSorter.sort(array2);
        System.out.println("After:");
        System.out.println(Arrays.toString(array2));
        System.out.println("Recursion depth: "
                + quickSorter.getMaxRecursionDepth());
        System.out.println("Comparisons: "
                + quickSorter.getComparisons());
        System.out.println("Swaps: "
                + quickSorter.getSwaps());

        System.out.println();
        System.out.println("=== DETERMINISTIC SELECT ===");
        int[] array3 = {
                15, 3, 8, 1, 12, 7, 20, 4,
                10, 6, 2, 18, 5, 14, 9, 11
        };
        int k = 5;
        System.out.println("Array:");
        System.out.println(Arrays.toString(array3));
        System.out.println("Looking for element at index: " + k);
        DeterministicSelector selector =
                new DeterministicSelector();
        int result = selector.select(array3, k);
        System.out.println("Result: " + result);
        System.out.println("Comparisons: "
                + selector.getComparisons());
    }
}