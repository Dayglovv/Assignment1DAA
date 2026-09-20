package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Experiment {
    private static final Random random = new Random(42);
    public static void main(String[] args) {
        createResultsFolder();
        try {
            FileWriter writer =
                    new FileWriter("results/results.csv");
            writer.write(
                    "algorithm,inputSize,inputType,timeNs," +
                            "recursionDepth,comparisons,swaps\n");
            int[] sizes = {
                    100,
                    1000,
                    5000,
                    10000
            };
            String[] inputTypes = {
                    "random",
                    "sorted",
                    "reverse",
                    "duplicates"
            };
            for (int size : sizes) {
                for (String type : inputTypes) {
                    runSortingExperiment(
                            writer,
                            size,
                            type
                    );
                }
            }
            writer.close();
            System.out.println(
                    "Experiments finished!"
            );
            System.out.println(
                    "Results saved to results/results.csv"
            );
        } catch (IOException e) {
            System.out.println(
                    "Error writing results: "
                            + e.getMessage()
            );
        }
    }
    private static void runSortingExperiment(
            FileWriter writer,
            int size,
            String type) throws IOException {
        int[] originalArray =
                createArray(size, type);
        // MergeSort
        int[] mergeArray =
                originalArray.clone();
        MergeSorter mergeSorter =
                new MergeSorter();
        long start =
                System.nanoTime();

        mergeSorter.sort(mergeArray);
        long end =
                System.nanoTime();
        long time =
                end - start;
        writer.write(
                "MergeSort,"
                        + size + ","
                        + type + ","
                        + time + ","
                        + mergeSorter.getMaxRecursionDepth()
                        + ","
                        + mergeSorter.getComparisons()
                        + ",0\n"
        );
        // QuickSort
        int[] quickArray =
                originalArray.clone();
        QuickSorter quickSorter = new QuickSorter();
        start = System.nanoTime();
        quickSorter.sort(quickArray);
        end = System.nanoTime();
        time = end - start;

        writer.write(
                "QuickSort,"
                        + size + ","
                        + type + ","
                        + time + ","
                        + quickSorter.getMaxRecursionDepth()
                        + ","
                        + quickSorter.getComparisons()
                        + ","
                        + quickSorter.getSwaps()
                        + "\n"
        );

        // Deterministic Select
        int[] selectArray = originalArray.clone();
        DeterministicSelector selector = new DeterministicSelector();
        int k = size / 2;
        start = System.nanoTime();
        selector.select(selectArray, k);
        end = System.nanoTime();
        time = end - start;
        writer.write(
                "Select,"
                        + size + ","
                        + type + ","
                        + time + ",0,"
                        + selector.getComparisons()
                        + ",0\n"
        );
        writer.flush();
        System.out.println(
                "Finished: size=" + size
                        + ", type=" + type
        );
    }
    private static int[] createArray(
            int size,
            String type) {
        int[] array =
                new int[size];
        if (type.equals("random")) {
            for (int i = 0; i < size; i++) {
                array[i] =
                        random.nextInt(size * 10);
            }
        } else if (type.equals("sorted")) {
            for (int i = 0; i < size; i++) {
                array[i] = i;
            }
        } else if (type.equals("reverse")) {
            for (int i = 0; i < size; i++) {
                array[i] = size - i;
            }
        } else if (type.equals("duplicates")) {
            for (int i = 0; i < size; i++) {
                array[i] =
                        random.nextInt(10);
            }
        }
        return array;
    }
    private static void createResultsFolder() {

        File folder = new File("results");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}