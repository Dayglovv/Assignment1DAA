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

        System.out.println();
        System.out.println("=== CLOSEST PAIR ===");
        Point[] points = {
                new Point(1, 1),
                new Point(2, 5),
                new Point(4, 4),
                new Point(7, 8),
                new Point(3, 3),
                new Point(10, 10)
        };
        ClosestPairSolver solver = new ClosestPairSolver();
        Point[] closestPair = solver.findClosestPair(points);
        System.out.println("Closest points:");
        System.out.println(closestPair[0]);
        System.out.println(closestPair[1]);
        System.out.println("Distance: "
                + closestPair[0].distance(closestPair[1]));
        System.out.println("Comparisons: "
                + solver.getComparisons());
    }
}