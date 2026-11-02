# A3 Sorting Traces and Predictions

## 1. Selection sort

Initial array: [33, 8, 61, 47, 12, 55, 20]
Indices are zero-based.

| Pass | i | Minimum value | Minimum index | Array after swap            |
|------|---|---------------|---------------|-----------------------------|
| 1    | 0 | 8             | 1             | [8，33，61，47，12，55，20] |
| 2    | 1 | 12            | 4             | [8, 12, 61, 47, 33, 55, 20] |
| 3    | 2 | 20            | 6             | [8, 12, 20, 47, 33, 55, 61] |
| 4    | 3 | 33            | 4             | [8, 12, 20, 33, 47, 55, 61] |
| 5    | 4 | 47            | 4             | [8, 12, 20, 33, 47, 55, 61] |
| 6    | 5 | 55            | 5             | [8, 12, 20, 33, 47, 55, 61] |

Self-swap passes: 5 and 6.
Total value comparisons: 6 + 5 + 4 + 3 + 2 + 1 = 21

## 2. Insertion sort

Initial array: [33, 8, 61, 47, 12, 55, 20]

| i | key | Shifts | Value comparisons | Array after insertion       |
|---|-----|--------|-------------------|-----------------------------|
| 1 | 8   | 1      | 1                 | [8, 33, 61, 47, 12, 55, 20] |
| 2 | 61  | 0      | 1                 | [8, 33, 61, 47, 12, 55, 20] |
| 3 | 47  | 1      | 2                 | [8, 33, 47, 61, 12, 55, 20] |
| 4 | 12  | 3      | 4                 | [8, 12, 33, 47, 61, 55, 20] |
| 5 | 55  | 1      | 2                 | [8, 12, 33, 47, 55, 61, 20] |
| 6 | 20  | 4      | 5                 | [8, 12, 20, 33, 47, 55, 61] |

Total shifts: 1 + 0 + 1 + 3 + 1 + 4 = 10
Total value comparisons: 1 + 1 + 2 + 4 + 2 + 5 = 15

## 3. Merge sort

Initial array: [15, 42, 8, 31, 27, 4, 19, 11]

### Splitting

| Level | Subarrays                                           |
|-------|-----------------------------------------------------|
| 0     | [15, 42, 8, 31, 27, 4, 19, 11]                      |
| 1     | [15, 42, 8, 31] / [27, 4, 19, 11]                   |
| 2     | [15, 42] / [8, 31] / [27, 4] / [19, 11]             |
| 3     | [15] / [42] / [8] / [31] / [27] / [4] / [19] / [11] |

### Merging in execution order

| Step | Left            | Right           | Merged result                  | Value comparisons |
|------|-----------------|-----------------|--------------------------------|-------------------|
| 1    | [15]            | [42]            | [15, 42]                       | 1                 |
| 2    | [8]             | [31]            | [8, 31]                        | 1                 |
| 3    | [15, 42]        | [8, 31]         | [8, 15, 31, 42 ]               | 3                 |
| 4    | [27]            | [4]             | [4, 27]                        | 1                 |
| 5    | [19]            | [11]            | [11, 19]                       | 1                 |
| 6    | [4, 27]         | [11, 19]        | [4, 11, 19, 27]                | 3                 |
| 7    | [8, 15, 31, 42] | [4, 11, 19, 27] | [4, 8, 11, 15, 19, 27, 31, 42] | 6                 | 

Total value comparisons: 1 + 1 + 3 + 1 + 1 + 3 + 6 = 16.

## 4. Incorrect while condition

Initial array: [5, 3, 1]

| Stage                | i | j  | key | Array     | What happens                                          | 
|----------------------|---|----|-----|-----------|-------------------------------------------------------|
| Start of first pass  | 1 | 0  | 3   | [5, 3, 1] | 5 > 3 and j >= 0 are both true                        |
| After shift and j--  | 1 | -1 | 3   | [5, 5, 1] | Move 5 right, then decrease j                         |
| Next condition check | 1 | -1 | 3   | [5, 5, 1] | Accessing a[-1] throws ArrayIndexOutOfBoundsException |

The incorrect condition evaluates a[j] > key before checking j >= 0. When j becomes -1, accessing a[-1] throws an
ArrayIndexOutOfBoundsException.
The correct condition is j >= 0 && a[j] > key. Java evaluates && from left to right and short-circuits when the left
side is false, so a[j] is not accessed when j is -1.

## 5. Predictions before running

### 1. Selection sort comparisons for n = 1000

I predict 1000 * 999 / 2 = 499500 value comparisons. Each pass checks every remaining element to find the minimum, so
the number of comparisons stays the same even when the array is already sorted.

### 2. Fastest and slowest sorts for sorted input at n = 32000

Among my three sorting algorithms, I predict insertion sort will be the fastest because each element after the first
needs only one value comparison and no shifts. Selection sort will be the slowest because it still makes n * (n - 1) / 2
comparisons. Merge
sort still splits and merges the array, taking O (n log n) time.

### 3. Effect of doubling n

I predict selection sort will take about four times as long when n doubles because its running time grows as O (n^2).
Merge sort should take slightly more than twice as long because its running time grows as O (n log n).

## 6. Corrections to my original traces

### Selection sort

- Pass 2: My original result was [8, 12, 20, 33, 47, 55, 61]. The correct result is [8, 12, 61, 47, 33, 55, 20]. I
  sorted the remaining elements instead of only swapping 33 and 12.
- Pass 3: My original result was [8, 12, 20, 61, 47, 33, 55]. The correct result is [8, 12, 20, 47, 33, 55, 61]. I
  shifted elements instead of swapping 61 and 20.
- Pass 4: My original result was [8, 12, 20, 33, 55, 61, 47]. The correct result is [8, 12, 20, 33, 47, 55, 61]. The
  displaced 47 should go to index 4, where 33 was.

### Insertion sort

- At i = 2, I originally recorded key = 33 and 0 comparisons. The correct key is a[2] = 61, with 1 comparison. The false
  comparison 33 > 61 still counts.
- At i = 4, I originally recorded 1 shift, 3 comparisons, and [8, 33, 47, 55, 20, 61, 12]. The correct result is 3
  shifts, 4 comparisons, and [8, 12, 33, 47, 61, 55, 20]. The key must be inserted into the sorted left portion; 55 and
  20 stay in place.

### Merge sort

- In the final merge, I originally recorded 7 comparisons. The correct count is 6 because copying the remaining 31 and
  42 does not require value comparisons.