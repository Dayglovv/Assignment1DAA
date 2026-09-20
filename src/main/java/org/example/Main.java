package org.example;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = {
                15, 3, 8, 1, 12, 7, 20, 4,
                10, 6, 2, 18, 5, 14, 9, 11
        };
        System.out.println("Before sorting:");
        System.out.println(Arrays.toString(array));
        QuickSorter sorter = new QuickSorter();
        sorter.sort(array);
        System.out.println("After sorting:");
        System.out.println(Arrays.toString(array));
        System.out.println("Max recursion depth: "
                + sorter.getMaxRecursionDepth());
        System.out.println("Comparisons: "
                + sorter.getComparisons());
        System.out.println("Swaps: "
                + sorter.getSwaps());
    }
}