import org.example.DeterministicSelector;
import org.example.MergeSorter;
import org.example.QuickSorter;
import org.example.ClosestPairSolver;
import org.example.Point;
import java.util.Arrays;
import java.util.Random;

public class TestMain {

    public static void main(String[] args) {

        testMergeSort();
        testQuickSort();
        testSelect();
        testSelectRandom();
        testClosestPair();
        testRecursionDepth();
        testClosestPairBruteForce();
        testEdgeCases();

        System.out.println();
        System.out.println("All tests passed!");
    }

    private static void testMergeSort() {

        int[] array = {
                8, 3, 5, 1, 9, 2, 7, 4, 6
        };

        int[] expected = array.clone();
        Arrays.sort(expected);

        MergeSorter sorter = new MergeSorter();
        sorter.sort(array);

        if (!Arrays.equals(array, expected)) {
            throw new RuntimeException(
                    "MergeSort test failed"
            );
        }

        System.out.println("MergeSort: OK");
    }

    private static void testQuickSort() {

        int[] array = {
                8, 3, 5, 1, 9, 2, 7, 4, 6
        };

        int[] expected = array.clone();
        Arrays.sort(expected);

        QuickSorter sorter = new QuickSorter();
        sorter.sort(array);

        if (!Arrays.equals(array, expected)) {
            throw new RuntimeException(
                    "QuickSort test failed"
            );
        }

        System.out.println("QuickSort: OK");
    }

    private static void testSelect() {

        int[] array = {
                7, 2, 9, 1, 5
        };

        DeterministicSelector selector =
                new DeterministicSelector();

        int result = selector.select(array, 2);

        if (result != 5) {
            throw new RuntimeException(
                    "Select test failed"
            );
        }

        System.out.println("Deterministic Select: OK");
    }

    private static void testSelectRandom() {

        Random random = new Random(42);

        int numberOfTests = 100;

        for (int test = 1;
             test <= numberOfTests;
             test++) {

            int size =
                    10 + random.nextInt(191);

            int[] array =
                    new int[size];

            for (int i = 0; i < size; i++) {

                array[i] =
                        random.nextInt(1000);
            }

            int k =
                    random.nextInt(size);

            int[] sorted =
                    array.clone();

            Arrays.sort(sorted);

            int expected =
                    sorted[k];

            DeterministicSelector selector =
                    new DeterministicSelector();

            int actual =
                    selector.select(array, k);

            if (actual != expected) {

                throw new RuntimeException(
                        "Select random test failed. "
                                + "Test number: "
                                + test
                                + ", k: "
                                + k
                                + ", expected: "
                                + expected
                                + ", actual: "
                                + actual
                );
            }
        }

        System.out.println(
                "Deterministic Select random tests: "
                        + numberOfTests
                        + "/"
                        + numberOfTests
                        + " passed"
        );
    }

    private static void testClosestPair() {

        Point[] points = {
                new Point(0, 0),
                new Point(10, 10),
                new Point(1, 1),
                new Point(20, 20)
        };

        ClosestPairSolver solver =
                new ClosestPairSolver();

        Point[] result =
                solver.findClosestPair(points);

        double distance =
                result[0].distance(result[1]);

        double expected =
                Math.sqrt(2);

        if (Math.abs(
                distance - expected
        ) > 0.000001) {

            throw new RuntimeException(
                    "Closest Pair test failed"
            );
        }

        System.out.println(
                "Closest Pair: OK"
        );
    }

    private static void testRecursionDepth() {

        int[] array = {
                15, 3, 8, 1, 12,
                7, 20, 4, 10, 6,
                2, 18, 5, 14, 9, 11
        };

        DeterministicSelector selector =
                new DeterministicSelector();

        selector.select(array, 5);

        if (selector.getMaxRecursionDepth() <= 0) {

            throw new RuntimeException(
                    "Select recursion depth was not measured"
            );
        }

        Point[] points = {
                new Point(0, 0),
                new Point(2, 3),
                new Point(5, 4),
                new Point(7, 8),
                new Point(10, 10),
                new Point(12, 11),
                new Point(20, 20)
        };

        ClosestPairSolver solver =
                new ClosestPairSolver();

        solver.findClosestPair(points);

        if (solver.getMaxRecursionDepth() <= 0) {

            throw new RuntimeException(
                    "Closest Pair recursion depth was not measured"
            );
        }

        System.out.println(
                "Select recursion depth: "
                        + selector.getMaxRecursionDepth()
        );

        System.out.println(
                "Closest Pair recursion depth: "
                        + solver.getMaxRecursionDepth()
        );
    }

    private static void testClosestPairBruteForce() {

        Random random =
                new Random(42);

        int[] sizes = {
                10,
                50,
                100,
                500,
                1000,
                2000
        };

        for (int size : sizes) {

            Point[] points =
                    createRandomPoints(
                            size,
                            random
                    );

            ClosestPairSolver solver =
                    new ClosestPairSolver();

            Point[] fastResult =
                    solver.findClosestPair(points);

            double fastDistance =
                    fastResult[0]
                            .distance(fastResult[1]);

            double bruteForceDistance =
                    bruteForceClosestPair(points);

            if (Math.abs(
                    fastDistance
                            - bruteForceDistance
            ) > 0.000001) {

                throw new RuntimeException(
                        "Closest Pair brute force test failed for n="
                                + size
                );
            }

            System.out.println(
                    "Closest Pair brute force n="
                            + size
                            + ": OK"
            );
        }
    }

    private static Point[] createRandomPoints(
            int size,
            Random random) {

        Point[] points =
                new Point[size];

        for (int i = 0; i < size; i++) {

            double x =
                    random.nextDouble() * 10000;

            double y =
                    random.nextDouble() * 10000;

            points[i] =
                    new Point(x, y);
        }

        return points;
    }

    private static double bruteForceClosestPair(
            Point[] points) {

        double minDistance =
                Double.MAX_VALUE;

        for (int i = 0;
             i < points.length;
             i++) {

            for (int j = i + 1;
                 j < points.length;
                 j++) {

                double distance =
                        points[i]
                                .distance(points[j]);

                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }

        return minDistance;
    }

    private static void testEdgeCases() {

        // Empty array
        int[] empty = {};

        MergeSorter mergeSorter =
                new MergeSorter();

        QuickSorter quickSorter =
                new QuickSorter();

        mergeSorter.sort(empty);
        quickSorter.sort(empty);

        if (empty.length != 0) {

            throw new RuntimeException(
                    "Empty array test failed"
            );
        }

        // Single element
        int[] single = {5};

        mergeSorter.sort(single);
        quickSorter.sort(single);

        if (single.length != 1
                || single[0] != 5) {

            throw new RuntimeException(
                    "Single element test failed"
            );
        }

        // Duplicates
        int[] duplicates = {
                4, 2, 4, 1, 2, 4
        };

        int[] expected =
                duplicates.clone();

        Arrays.sort(expected);

        mergeSorter.sort(duplicates);

        if (!Arrays.equals(
                duplicates,
                expected
        )) {

            throw new RuntimeException(
                    "Duplicate values test failed"
            );
        }

        // QuickSort with duplicates
        int[] quickDuplicates = {
                4, 2, 4, 1, 2, 4
        };

        quickSorter.sort(quickDuplicates);

        if (!Arrays.equals(
                quickDuplicates,
                expected
        )) {

            throw new RuntimeException(
                    "QuickSort duplicate values test failed"
            );
        }

        System.out.println(
                "Edge cases: OK"
        );
    }
}