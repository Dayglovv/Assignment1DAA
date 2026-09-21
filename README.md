# Assignment 1 — Divide and Conquer Algorithms

## 1. Project Description

This project implements and compares four algorithms based on the Divide and Conquer approach:

1. Merge Sort
2. Quick Sort
3. Deterministic Select (Median of Medians)
4. Closest Pair of Points

The main goal of the assignment is to understand how Divide and Conquer algorithms work, analyze their time and space complexity, and compare their practical performance on different types and sizes of input data.

For the experiments, the program measures:

* execution time in nanoseconds;
* maximum recursion depth;
* number of comparisons;
* number of swaps where applicable.

The experimental results are saved into a CSV file and then visualized using Python.

---

## 2. Technologies Used

* Java
* IntelliJ IDEA
* Java standard library
* Python
* CSV
* Matplotlib

No Maven or external Java libraries are required.

---

## 3. Project Structure

```text
Assignment1DAA/
│
├── src/
│   └── org/
│       └── example/
│           ├── Main.java
│           ├── Experiment.java
│           ├── MergeSorter.java
│           ├── QuickSorter.java
│           ├── DeterministicSelector.java
│           ├── ClosestPairSolver.java
│           └── Point.java
│
├── plots/
│   └── plot_results.py
│
├── results/
│   └── results.csv
│
├── screenshots/
│   └── ...
│
├── TestMain.java
├── README.md
└── .gitignore
```

The exact location of some files can be slightly different depending on the IntelliJ IDEA project structure.

---

# 4. Algorithms

## 4.1 Merge Sort

Merge Sort recursively divides the array into two parts.

The algorithm:

1. Splits the array into two halves.
2. Recursively sorts the left half.
3. Recursively sorts the right half.
4. Merges the two sorted parts.

For small subarrays, the implementation uses insertion sort instead of continuing the recursion.

The limit is:

```java
private static final int INSERTION_SORT_LIMIT = 10;
```

### Time Complexity

The recurrence is:

```text
T(n) = 2T(n/2) + O(n)
```

Using the Master Theorem:

```text
a = 2
b = 2
f(n) = O(n)
```

Therefore:

```text
T(n) = O(n log n)
```

This is the complexity for best, average and worst cases.

### Space Complexity

Merge Sort uses an additional temporary array:

```text
O(n)
```

The recursion itself requires:

```text
O(log n)
```

So the main additional memory requirement is:

```text
O(n)
```

---

# 5. Quick Sort

The Quick Sort implementation uses a randomly selected pivot.

For every partition:

1. A random pivot is selected.
2. Elements smaller than the pivot are moved to the left.
3. The pivot is placed in its final position.
4. The two parts are processed recursively.

The implementation also recursively processes the smaller part first. The larger part is handled using the `while` loop. This helps reduce the recursion depth.

### Time Complexity

The general recurrence for a balanced partition is:

```text
T(n) = 2T(n/2) + O(n)
```

which gives:

```text
O(n log n)
```

Average expected complexity with a random pivot:

```text
O(n log n)
```

Worst case:

```text
O(n²)
```

The worst case can happen when the pivot repeatedly creates very unbalanced partitions.

### Space Complexity

Because the implementation processes the smaller partition recursively and handles the larger partition iteratively, the recursion depth is kept relatively small.

Average auxiliary stack space:

```text
O(log n)
```

The algorithm itself does not create another array for sorting.

---

# 6. Deterministic Select

Deterministic Select finds the k-th smallest element without fully sorting the array.

The implementation uses the **Median of Medians** method to choose the pivot.

The main steps are:

1. Divide the elements into groups of at most 5 elements.
2. Sort each small group using insertion sort.
3. Take the median from each group.
4. Recursively find the median of these medians.
5. Use this value as the pivot.
6. Partition the original array.
7. Continue only in the part that contains the required k-th element.

The method uses zero-based indexing for `k`.

For example:

```text
k = 0
```

means the smallest element, while:

```text
k = 2
```

means the third smallest element.

### Time Complexity

The recurrence for Median of Medians can be written approximately as:

```text
T(n) = T(n/5) + T(7n/10) + O(n)
```

The Median of Medians method guarantees:

```text
O(n)
```

worst-case time complexity.

This is one of the main differences between deterministic Select and a simple QuickSelect implementation.

### Space Complexity

The implementation creates a temporary array containing the medians of groups.

Its size is approximately:

```text
n / 5
```

Therefore the additional space is:

```text
O(n)
```

The recursive stack also requires additional memory.

---

# 7. Closest Pair of Points

The Closest Pair algorithm finds two points with the smallest Euclidean distance.

The implementation first sorts the points by their x-coordinate.

Then it recursively:

1. Splits the points into two halves.
2. Finds the closest pair in the left half.
3. Finds the closest pair in the right half.
4. Takes the smaller of the two distances.
5. Creates a strip around the dividing line.
6. Sorts the strip by y-coordinate.
7. Checks possible pairs inside the strip.

For small sets containing at most three points, brute force is used.

### Distance Formula

For two points:

```text
P1 = (x1, y1)
P2 = (x2, y2)
```

the Euclidean distance is:

```text
d = sqrt((x1 - x2)² + (y1 - y2)²)
```

### Time Complexity

The main divide-and-conquer recurrence is:

```text
T(n) = 2T(n/2) + O(n)
```

which gives:

```text
O(n log n)
```

after sorting.

### Space Complexity

The implementation creates arrays for the left and right parts and also creates the strip.

Therefore the additional memory usage is approximately:

```text
O(n)
```

---

# 8. Complexity Summary

| Algorithm            |       Best |    Average |      Worst |      Extra Space |
| -------------------- | ---------: | ---------: | ---------: | ---------------: |
| Merge Sort           | O(n log n) | O(n log n) | O(n log n) |             O(n) |
| Quick Sort           | O(n log n) | O(n log n) |      O(n²) | O(log n) average |
| Deterministic Select |       O(n) |       O(n) |       O(n) |             O(n) |
| Closest Pair         | O(n log n) | O(n log n) | O(n log n) |             O(n) |

The exact practical behaviour depends on the input and implementation details.

---

# 9. Recursion Depth

The program measures the maximum recursion depth for the recursive algorithms.

For this purpose, counters are used inside the algorithms.

For example:

```java
private int recursionDepth;
private int maxRecursionDepth;
```

When entering a recursive call:

```java
recursionDepth++;

if (recursionDepth > maxRecursionDepth) {
    maxRecursionDepth = recursionDepth;
}
```

When leaving the call:

```java
recursionDepth--;
```

This allows the experiment to store the maximum recursion depth for each input.

For Merge Sort, the recursion depth grows logarithmically with the input size.

For Quick Sort, the depth depends on the partitions. The implementation reduces unnecessary stack growth by recursively processing the smaller partition first.

For Deterministic Select and Closest Pair, the recursion depth also grows relatively slowly because the input is divided into smaller parts.

---

# 10. Experimental Setup

The experiment measures the algorithms using different input sizes and input types.

The input types include:

* random;
* sorted;
* reverse sorted;
* duplicates.

For every experiment, the following information is stored:

```text
algorithm
inputSize
inputType
timeNs
recursionDepth
comparisons
swaps
```

The data is saved into:

```text
results/results.csv
```

Example:

```text
algorithm,inputSize,inputType,timeNs,recursionDepth,comparisons,swaps
MergeSort,100,random,80000,5,489,0
QuickSort,100,random,171600,4,670,312
Select,100,random,102200,5,358,0
ClosestPair,100,random,6636300,7,138,0
```

---

# 11. Metrics

## Execution Time

Execution time is measured in nanoseconds.

The CSV column is:

```text
timeNs
```

This makes it possible to compare the practical running time of the algorithms.

Because Java execution time can be affected by JVM warm-up and other system processes, individual timing values should not be treated as exact theoretical measurements. The main purpose is to observe general trends.

---

## Recursion Depth

The maximum recursion depth is stored in:

```text
recursionDepth
```

This helps compare how much recursion each algorithm uses.

---

## Comparisons

The number of important element comparisons is stored in:

```text
comparisons
```

This metric is useful for comparing how much work the algorithms perform.

---

## Swaps

For sorting algorithms where swaps are used, the number of swaps is stored in:

```text
swaps
```

Merge Sort and Select do not use swaps in the same way as Quick Sort, so their value can be zero.

---

# 12. Experimental Results

The experiment showed the expected general behaviour of the algorithms.

For example, for input size 100:

```text
MergeSort:
random     -> depth 5
sorted     -> depth 5
reverse    -> depth 5
duplicates -> depth 5
```

This is expected because Merge Sort divides the array approximately in half regardless of the input order.

For Quick Sort, the recursion depth and number of comparisons vary more between input types because the partitions depend on the randomly selected pivot.

Deterministic Select generally performs a linear amount of selection work, although the measured number of comparisons changes depending on the input.

Closest Pair has higher absolute running time in the experiment because it works with objects, distance calculations, arrays and the strip processing.

The actual values used for the report are stored in:

```text
results/results.csv
```

---

# 13. Graphs

The CSV data was visualized using Python and Matplotlib.

The Python script is:

```text
plots/plot_results.py
```

The graphs show the relationship between input size and algorithm performance.

The main graphs are:

### Time vs Input Size

This graph shows how execution time changes when the input size increases.

The theoretical expectation is:

* Merge Sort → approximately O(n log n)
* Quick Sort → approximately O(n log n) on average
* Deterministic Select → approximately O(n)
* Closest Pair → approximately O(n log n)

### Recursion Depth vs Input Size

This graph shows how maximum recursion depth changes with the input size.

The expected general trend is logarithmic for the divide-and-conquer algorithms, although Quick Sort can behave differently depending on its partitions.

---

# 14. Correctness Testing

A separate `TestMain` class is used for correctness testing.

The tests compare the implemented algorithms with known correct results.

## Merge Sort

Merge Sort is compared with:

```java
Arrays.sort()
```

The test checks that the final arrays are equal.

---

## Quick Sort

Quick Sort is also compared with:

```java
Arrays.sort()
```

The sorted arrays must be identical.

---

## Deterministic Select

A basic test checks a known example:

```text
[7, 2, 9, 1, 5]
```

For:

```text
k = 2
```

the expected result is:

```text
5
```

There are also 100 random Select tests.

For each random test:

1. A random array is generated.
2. A random `k` is selected.
3. A copy of the array is sorted using `Arrays.sort()`.
4. The expected value is taken from the sorted array.
5. Deterministic Select is executed.
6. The two results are compared.

The random generator uses:

```java
new Random(42)
```

so the tests are reproducible.

The test output is:

```text
Deterministic Select random tests: 100/100 passed
```

---

# 15. Closest Pair Correctness Test

Closest Pair is tested using two methods.

First, a small manually created example is checked.

Then the divide-and-conquer algorithm is compared with a brute-force implementation.

The brute-force algorithm checks every possible pair of points.

The tested input sizes are:

```text
10
50
100
500
1000
2000
```

The random generator also uses a fixed seed:

```java
new Random(42)
```

This makes the test reproducible.

For every size, the distance returned by the fast algorithm is compared with the brute-force distance.

---

# 16. Edge Cases

The tests also include several edge cases.

### Empty array

```text
[]
```

The sorting algorithms should handle it without an error.

### Single element

```text
[5]
```

The array should remain unchanged.

### Duplicate values

Example:

```text
[4, 2, 4, 1, 2, 4]
```

Both Merge Sort and Quick Sort are checked against `Arrays.sort()`.

---

# 17. How to Run the Project

## Step 1 — Run the correctness tests

Run:

```text
TestMain
```

Expected final message:

```text
All tests passed!
```

The output should also contain:

```text
Deterministic Select random tests: 100/100 passed
```

and successful Closest Pair brute-force tests.

---

## Step 2 — Run the experiment

Run:

```text
Experiment
```

The program generates:

```text
results/results.csv
```

The CSV contains the measurements for the algorithms.

---

## Step 3 — Generate the graphs

Python is used for visualization.

Run:

```text
python plots/plot_results.py
```

The script reads:

```text
results/results.csv
```

and creates the graphs.

If running the script from IntelliJ IDEA, the working directory should be the project root so that the following path exists:

```text
results/results.csv
```

---

# 18. Divide and Conquer Analysis

All four algorithms use the main idea of Divide and Conquer, but in different ways.

### Merge Sort

The array is divided into two approximately equal parts and both parts are solved recursively.

```text
Divide → Solve left → Solve right → Merge
```

### Quick Sort

The array is divided around a pivot.

```text
Divide → Partition → Solve smaller parts
```

The size of the two parts depends on the pivot.

### Deterministic Select

The algorithm does not solve both partitions.

After partitioning, only the part containing the k-th element is processed.

```text
Choose pivot → Partition → Continue with one part
```

This is why its target is linear time.

### Closest Pair

The points are divided into two groups.

```text
Solve left → Solve right → Check the middle strip
```

The strip is needed because the closest pair can contain one point from each half.

---

# 19. Master Theorem

For Merge Sort and the main Closest Pair recurrence, the recurrence has the form:

```text
T(n) = 2T(n/2) + O(n)
```

Here:

```text
a = 2
b = 2
f(n) = O(n)
```

Since:

```text
n^(log_b(a)) = n
```

the Master Theorem gives:

```text
T(n) = O(n log n)
```

Quick Sort can also have a balanced recurrence:

```text
T(n) = 2T(n/2) + O(n)
```

but this is not guaranteed because the partition sizes depend on the selected pivot.

For Deterministic Select, the Median of Medians recurrence is approximately:

```text
T(n) = T(n/5) + T(7n/10) + O(n)
```

which results in:

```text
T(n) = O(n)
```

---

# 20. Discussion

## Which algorithm has the most stable running time?

Merge Sort has predictable asymptotic behaviour because it always divides the input into approximately equal halves.

Deterministic Select also has a guaranteed linear worst-case complexity for finding the k-th element.

Quick Sort has more variation because its partition sizes depend on the randomly selected pivot.

---

## Why does Quick Sort behave differently for different inputs?

The partition depends on the pivot.

If the pivot creates two reasonably balanced parts, the algorithm performs close to:

```text
O(n log n)
```

If partitions become very unbalanced repeatedly, the running time can approach:

```text
O(n²)
```

Using a random pivot reduces the likelihood of consistently bad partitions.

---

## Why does Merge Sort have similar recursion depth for different input types?

Merge Sort does not depend strongly on whether the input is sorted, reverse sorted or random.

The array is divided into halves in almost the same way every time.

Therefore the recursion depth mainly depends on `n`.

---

## Why is Deterministic Select linear?

The Median of Medians method provides a pivot that guarantees that a sufficiently large portion of the elements is discarded after partitioning.

Therefore the recursive problem becomes smaller by a constant fraction.

The resulting recurrence gives:

```text
O(n)
```

worst-case complexity.

---

## Why can Closest Pair take more time in practice?

Although Closest Pair has an asymptotic complexity of:

```text
O(n log n)
```

the implementation performs additional work:

* sorting points;
* creating arrays;
* creating the strip;
* sorting the strip by y-coordinate;
* calculating Euclidean distances;
* working with `Point` objects.

Therefore its measured time can be considerably larger than a simple integer sorting algorithm even when the asymptotic complexity is similar.

---

# 21. Reflection

This assignment helped me understand the difference between theoretical complexity and real program performance.

Before the implementation, it is easy to think that two algorithms with the same Big-O complexity should have similar running times. The experiment showed that this is not necessarily true.

For example, Merge Sort and Closest Pair can both have O(n log n) complexity, but their actual execution times can be very different because they perform different operations and use different data structures.

I also learned that recursion depth is an important practical property of recursive algorithms. Quick Sort in particular can have different recursion behaviour depending on how the array is partitioned.

The testing part was also useful. Comparing the results with `Arrays.sort()` and with a brute-force Closest Pair implementation helped verify that the algorithms were producing correct results.

The 100 random tests for Deterministic Select were especially useful because a single manually selected example is not enough to find implementation errors.

Finally, the experiments showed why both theoretical analysis and empirical testing are useful. Big-O notation describes how an algorithm scales, while actual measurements show what happens in a real implementation.

---

# 22. Conclusion

The project implements four Divide and Conquer algorithms:

* Merge Sort;
* Quick Sort;
* Deterministic Select;
* Closest Pair.

Their correctness was tested using manually created examples, random tests, comparisons with `Arrays.sort()`, and brute-force verification.

The experimental program records execution time, recursion depth, comparisons and swaps. The results are stored in CSV format and visualized using Python.

The results demonstrate the expected general complexity behaviour of the algorithms while also showing that implementation details can have a significant effect on practical performance.

---

# 23. Test Output

A successful test run ends with:

```text
All tests passed!
```

The Select tests should include:

```text
Deterministic Select random tests: 100/100 passed
```

Closest Pair should also pass all brute-force comparison tests up to:

```text
n = 2000
```

---

# 24. Files Used for the Report

The main files used for the experiment are:

```text
results/results.csv
```

and:

```text
plots/plot_results.py
```

The generated graphs are used to compare:

```text
Time vs Input Size
```

and:

```text
Recursion Depth vs Input Size
```

for the implemented algorithms.
