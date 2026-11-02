import java.util.Arrays;
import java.util.Random;

/*
 * SortTester.java (given: copy it into a3-sorting/ unchanged)
 * Runs every sort in Sorts.java on the edge cases, then on 200 random arrays.
 */
public class SortTester {
    private static int passed = 0;
    private static int failed = 0;

    private static final int[][] CASES = {
            {},
            {7},
            {2, 1},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1},
            {3, 1, 3, 2, 1},
            {0, -4, 12, -4, 7},
            {29, 10, 14, 37, 13, 5}
    };

    private static void check(String name, int[] input) {
        int[] expected = input.clone();
        Arrays.sort(expected);
        int[] actual = input.clone();
        try {
            switch (name) {
                case "selection" -> Sorts.selectionSort(actual);
                case "insertion" -> Sorts.insertionSort(actual);
                case "merge" -> Sorts.mergeSort(actual);
            }
        } catch (RuntimeException e) {
            System.out.printf("  %-9s %-24s crashed: %s%n", name,
                    Arrays.toString(input), e);
            failed++;
            return;
        }
        boolean ok = Arrays.equals(actual, expected);
        System.out.printf("  %-9s %-24s -> %-24s %s%n", name,
                Arrays.toString(input),
                Arrays.toString(actual),
                ok ? "OK" : "FAIL, expected " + Arrays.toString(expected));
        if (ok) {
            passed++;
        } else {
            failed++;
        }
    }

    private static void checkMerge(int[] left, int[] right) {
        int[] out = new int[left.length + right.length];
        int[] expected = new int[out.length];
        System.arraycopy(left, 0, expected, 0, left.length);
        System.arraycopy(right, 0, expected, left.length, right.length);
        Arrays.sort(expected);
        Sorts.merge(left, right, out);
        boolean ok = Arrays.equals(out, expected);
        System.out.printf("  merge %s + %s -> %s %s%n",
                Arrays.toString(left), Arrays.toString(right),
                Arrays.toString(out),
                ok ? "OK" : "FAIL, expected " + Arrays.toString(expected));
        if (ok) {
            passed++;
        } else {
            failed++;
        }
    }

    private static void checkIsSorted(int[] a, boolean expected) {
        boolean actual = Sorts.isSorted(a);
        System.out.printf("  isSorted(%s) = %b %s%n", Arrays.toString(a),
                actual,
                actual == expected ? "OK" : "FAIL");
        if (actual == expected) {
            passed++;
        } else {
            failed++;
        }
    }

    private static int randomCheck(String name, int trials) {
        Random rand = new Random(2026);
        int good = 0;
        for (int t = 0; t < trials; t++) {
            int[] a = new int[rand.nextInt(51)];
            for (int i = 0; i < a.length; i++) {
                a[i] = rand.nextInt(201) - 100;
            }
            int[] expected = a.clone();
            Arrays.sort(expected);
            try {
                switch (name) {
                    case "selection" -> Sorts.selectionSort(a);
                    case "insertion" -> Sorts.insertionSort(a);
                    case "merge" -> Sorts.mergeSort(a);
                }
                if (Arrays.equals(a, expected)) {
                    good++;
                }
            } catch (RuntimeException e) {
                // counts as a failure
            }
        }
        return good;
    }

    public static void main(String[] args) {
        System.out.println("1. swap and isSorted");
        int[] s = {4, 8, 15};
        Sorts.swap(s, 0, 2);
        boolean swapOk = Arrays.equals(s, new int[]{15, 8, 4});
        System.out.println("  swap(a, 0, 2) on [4, 8, 15] -> "
                + Arrays.toString(s) + (swapOk ? " OK" : " FAIL"));
        if (swapOk) {
            passed++;
        } else {
            failed++;
        }
        checkIsSorted(new int[]{}, true);
        checkIsSorted(new int[]{1, 2, 2, 9}, true);
        checkIsSorted(new int[]{1, 3, 2}, false);
        checkIsSorted(new int[]{5, 1}, false);

        String[] names = {"selection", "insertion", "merge"};
        for (int n = 0; n < names.length; n++) {
            System.out.println((n + 2) + ". " + names[n] + " sort");
            for (int[] c : CASES) {
                check(names[n], c);
            }
        }

        System.out.println("5. merge");
        checkMerge(new int[]{1, 4, 9}, new int[]{2, 3, 10, 11});
        checkMerge(new int[]{}, new int[]{5, 6});
        checkMerge(new int[]{7}, new int[]{});
        checkMerge(new int[]{2, 2}, new int[]{2});

        System.out.println("6. insertion sort on Strings");
        String[] words = {"pear", "Apple", "fig", "apple", "Banana"};
        Sorts.insertionSort(words);
        String result = Arrays.toString(words);
        boolean wordsOk = result.equals("[Apple, Banana, apple, fig, pear]");
        System.out.println("  " + result + (wordsOk ? " OK" : " FAIL"));
        if (wordsOk) {
            passed++;
        } else {
            failed++;
        }

        System.out.println("7. Random check (200 arrays each)");
        for (String name : names) {
            int good = randomCheck(name, 200);
            System.out.printf("  %-9s %d/200%n", name, good);
            if (good == 200) {
                passed++;
            } else {
                failed++;
            }
        }

        System.out.println("Passed " + passed + ", failed " + failed);
    }
}