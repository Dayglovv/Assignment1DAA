package org.example;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] array = {8, 3, 5, 1, 9, 2, 7, 4, 6};

        System.out.println("Before sorting:");
        System.out.println(Arrays.toString(array));

        MergeSorter sorter = new MergeSorter();

        sorter.sort(array);

        System.out.println("After sorting:");
        System.out.println(Arrays.toString(array));

        System.out.println("Max recursion depth: "
                + sorter.getMaxRecursionDepth());

        System.out.println("Comparisons: "
                + sorter.getComparisons());
    }
}