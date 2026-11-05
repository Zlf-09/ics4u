import java.util.Arrays;
import java.util.Random;

public class SortExperiment {
    public static final int[] SIZES = {1000, 2000, 4000, 8000, 16000, 32000};
    public static final String[] INPUTS = {"random", "sorted", "reversed"};
    public static final String[] SORTS = {"selection", "insertion", "merge", "java"};
    public static final int REPEATS = 5;
    public static final long SEED = 42;

    /**
     * Returns a new array of n values: random (from seed), sorted, or reversed.
     */
    public static int[] makeArray(String input, int n, long seed) {
        int[] a = new int[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            if (input.equals("random")) {
                a[i] = rand.nextInt(1_000_000);
            } else if (input.equals("sorted")) {
                a[i] = i;
            } else {
                a[i] = n - i;
            }
        }
        return a;
    }

    private static void sortOnly(String sort, int[] a) {
        switch (sort) {
            case "selection" -> Sorts.selectionSort(a);
            case "insertion" -> Sorts.insertionSort(a);
            case "merge" -> Sorts.mergeSort(a);
            case "java" -> Arrays.sort(a);
            default -> throw new IllegalArgumentException("Unknown sort: " + sort);
        }
    }

    private static void runSort(String sort, int[] a) {
        sortOnly(sort, a);
        if (!Sorts.isSorted(a)) {
            throw new IllegalStateException(sort + " did not sort");
        }
    }

    private static long countComparisons(String sort, String input, int n) {
        if (sort.equals("java")) {
            return -1;
        }
        int[] a = makeArray(input, n, SEED);
        Sorts.comparisons = 0;
        runSort(sort, a);
        return Sorts.comparisons;
    }

    private static double medianMillis(String sort, String input, int n) {
        int[] original = makeArray(input, n, SEED);
        long[] times = new long[REPEATS];
        for (int trial = 0; trial < REPEATS; trial++) {
            int[] a = original.clone();
            Sorts.comparisons = 0;
            long start = System.nanoTime();
            sortOnly(sort, a);
            times[trial] = System.nanoTime() - start;
            if (!Sorts.isSorted(a)) {
                throw new IllegalStateException(sort + " did not sort");
            }
        }
        Arrays.sort(times);
        return times[times.length / 2] / 1_000_000.0;
    }

    public static void main(String[] args) {
        for (String sort : SORTS) {
            for (int trial = 0; trial < 3; trial++) {
                runSort(sort, makeArray("random", 5000, SEED + trial));
            }
        }
        System.out.println("sort,input,n,comparisons,ms");
        for (String sort : SORTS) {
            for (String input : INPUTS) {
                for (int n : SIZES) {
                    long comparisons = countComparisons(sort, input, n);
                    double millis = medianMillis(sort, input, n);
                    String count = comparisons < 0 ? "" : Long.toString(comparisons);
                    System.out.printf(java.util.Locale.US, "%s,%s,%d,%s,%.3f%n",
                            sort, input, n, count, millis);
                }
            }
        }
    }
}