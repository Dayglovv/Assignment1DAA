import org.example.DeterministicSelector;
import org.example.MergeSorter;
import org.example.QuickSorter;
import org.example.ClosestPairSolver;
import org.example.Point;
import java.util.Arrays;

public class TestMain {

    public static void main(String[] args) {

        testMergeSort();
        testQuickSort();
        testSelect();
        testClosestPair();

        System.out.println();
        System.out.println("All tests passed!");
    }

    private static void testMergeSort() {

        int[] array = {8, 3, 5, 1, 9, 2, 7, 4, 6};

        int[] expected = array.clone();
        Arrays.sort(expected);

        MergeSorter sorter = new MergeSorter();
        sorter.sort(array);

        if (!Arrays.equals(array, expected)) {
            throw new RuntimeException("MergeSort test failed");
        }

        System.out.println("MergeSort: OK");
    }

    private static void testQuickSort() {

        int[] array = {8, 3, 5, 1, 9, 2, 7, 4, 6};

        int[] expected = array.clone();
        Arrays.sort(expected);

        QuickSorter sorter = new QuickSorter();
        sorter.sort(array);

        if (!Arrays.equals(array, expected)) {
            throw new RuntimeException("QuickSort test failed");
        }

        System.out.println("QuickSort: OK");
    }

    private static void testSelect() {

        int[] array = {7, 2, 9, 1, 5};

        DeterministicSelector selector =
                new DeterministicSelector();

        int result = selector.select(array, 2);

        if (result != 5) {
            throw new RuntimeException("Select test failed");
        }

        System.out.println("Deterministic Select: OK");
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

        Point[] result = solver.findClosestPair(points);

        double distance =
                result[0].distance(result[1]);

        double expected = Math.sqrt(2);

        if (Math.abs(distance - expected) > 0.000001) {
            throw new RuntimeException(
                    "Closest Pair test failed"
            );
        }

        System.out.println("Closest Pair: OK");
    }
}