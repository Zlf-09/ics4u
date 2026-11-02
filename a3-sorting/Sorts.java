/*
 * Oscar Zhu
 * November 1, 2026
 * Implements sorting algorithms and counts value comparisons
 */

import java.util.Arrays;

public class Sorts {
    /**
     * Swaps the values at indices i and j
     * Precondition: a is not null, and i and j are valid indices
     *
     * @param a the array to modify
     * @param i the first index
     * @param j the second index
     */
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Checks whether the array is in nondecreasing order
     * Precondition: a is not null
     *
     * @param a the array to check
     * @return true if every value is at most the next value
     */
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sorts the array into ascending order using selection sort
     * Precondition: a is not null
     * Postcondition: a is in nondecreasing order and contains
     * the same values as before
     *
     * @param a the array to sort
     */
    public static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    /**
     * Sorts the array into ascending order using insertion sort
     * Precondition: a is not null
     * Postcondition: a is in nondecreasing order and contains
     * the same values as before
     *
     * @param a the array to sort
     */

    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    /**
     * Sorts the strings into nondecreasing order using insertion sort
     * Precondition: a is not null and contains no null elements
     * Postcondition: a is in nondecreasing order according to compareTo and contains the same strings as before
     *
     * @param a the array to sort
     */

    public static void insertionSort(String[] a) {
        for (int i = 1; i < a.length; i++) {
            String key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j].compareTo(key) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    /**
     * Merges two sorted arrays into out in nondecreasing order
     * Precondition: all arrays are not null; left and right are sorted;
     * out has length left.length + right.length and is a separate array
     * Postcondition: out is sorted and contains all values from left and right. Neither input array is modified.
     *
     * @param left  the first sorted array
     * @param right the second sorted array
     * @param out   the array that receives the merged values
     */
    public static void merge(int[] left, int[] right, int[] out) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                out[k] = left[i];
                i++;
            } else {
                out[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            out[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            out[k] = right[j];
            j++;
            k++;
        }
    }

    /**
     * Sorts a into ascending order by splitting it in half, sorting each half, and merging
     *
     * @param a the array to sort
     *          Precondition: a is not null
     *          Postcondition: a[i - 1] <= a[i] for every i >= 1, and a holds the same values as before
     */
    public static void mergeSort(int[] a) {
        if (a.length < 2) {
            return;
        }
        int mid = a.length / 2;
        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);
        mergeSort(left);
        mergeSort(right);
        merge(left, right, a);
    }
}
