# Sorting and Efficiency Report

Oscar Zhu — ICS4U

## Question

How do array size and input order affect the comparison counts and running times of selection sort, insertion sort,
merge sort, and Java's Arrays.sort? I investigated whether measured growth agreed with the expected complexity of each
algorithm.

## Method

I used a 2023 15-inch MacBook Air with an Apple M2 chip, 8 GB of RAM, macOS, and Temurin JDK 25. The array sizes were
1,000, 2,000, 4,000, 8,000, 16,000, and 32,000. Each size was tested with random, sorted, and reversed integer arrays.
Random values came from Java's Random using seed 42 and a bound of 1,000,000. Sorted arrays contained values from 0 to
n - 1; reversed arrays contained n down to 1.

Before measuring, I warmed up each algorithm using three random arrays of 5,000 elements. Each timed combination ran
five times using fresh copies of the same original array. I recorded the median in milliseconds using System.nanoTime
(). Array generation and copying occurred before timing; the sorted-order check occurred afterward.

For comparison counts, I reset Sorts.comparisons before a separate sorting run. The counter recorded integer-value
comparisons, including comparisons returning false, but excluded index checks. Arrays.sort's comparison field was blank
because I did not measure its internal comparisons. The handwritten sorts retained their counters during timing.

## Results

### Value comparisons on random input

|     n | Selection | Insertion |  Merge | Arrays.sort |
|------:|----------:|----------:|-------:|------------:|
|  1000 |    499500 |    253081 |   8688 |           — |
|  2000 |   1999000 |    996913 |  19406 |           — |
|  4000 |   7998000 |   3971946 |  42818 |           — |
|  8000 |  31996000 |  16226374 |  93676 |           — |
| 16000 | 127992000 |  64542397 | 203349 |           — |
| 32000 | 511984000 | 256431056 | 438593 |           — |

Arrays.sort comparisons were not measured.

### Median times and doubling ratios on random input

Times are in milliseconds. Each ratio divides the current time by the time at half the array size, using the rounded CSV
values.

|     n | Selection ms | Ratio | Insertion ms | Ratio | Merge ms | Ratio | Arrays.sort ms | Ratio |
|------:|-------------:|------:|-------------:|------:|---------:|------:|---------------:|------:|
|  1000 |        0.896 |     — |        0.103 |     — |    0.054 |     — |          0.043 |     — |
|  2000 |        6.852 |  7.65 |        0.403 |  3.91 |    0.143 |  2.65 |          0.126 |  2.93 |
|  4000 |       25.760 |  3.76 |        1.578 |  3.92 |    0.354 |  2.48 |          0.273 |  2.17 |
|  8000 |       93.433 |  3.63 |        6.455 |  4.09 |    0.703 |  1.99 |          0.577 |  2.11 |
| 16000 |      213.328 |  2.28 |       25.510 |  3.95 |    1.588 |  2.26 |          1.318 |  2.28 |
| 32000 |      605.202 |  2.84 |      105.711 |  4.14 |    3.651 |  2.30 |          3.337 |  2.53 |

![Time against n, random input](chart.png)

Complete measurements for all input types are in [results.csv](results.csv).

## Analysis

### 1. Doubling ratios

Insertion sort's time ratios stayed between 3.91 and 4.14, close to four. This supports O (n²) average-case growth:
doubling n approximately quadruples the work.

Selection sort's ratios ranged from 2.28 to 7.65 and did not settle near four. However, its comparisons increased from
127,992,000 at n = 16,000 to 511,984,000 at n = 32,000, almost exactly fourfold. Its counts provide stronger evidence of
quadratic growth than these variable timings.

Merge sort's final two ratios were 2.26 and 2.30; Arrays.sort's were 2.28 and 2.53. These are broadly consistent with O
(n log n), which predicts slightly more than twice the work when n doubles. The measurements support this pattern
without proving complexity from timing alone.

### 2. Counts against the formulas

Selection sort matched `n(n - 1) / 2` exactly: 499,500 comparisons at n = 1,000 and 511,984,000 at n = 32,000. It scans
the entire remaining section regardless of input order.

For merge sort, `n log₂(n)` gives approximately 9,966 at n = 1,000 and 478,905 at n = 32,000. The measured counts, 8,688
and 438,593, were approximately 87.2% and 91.6% of those estimates. The expression describes growth rather than an exact
count. When one side of a merge is exhausted, copying the remaining values requires no further value comparisons.

### 3. Insertion sort's best and worst cases

At n = 32,000, insertion sort made 31,999 comparisons on sorted input, 256,431,056 on random input, and 511,984,000 on
reversed input.

On sorted input, each value after the first immediately fails the greater-than test, requiring no shifts. This produces
n - 1 comparisons and O (n) best-case growth. On reversed input, each new value moves past every preceding value,
producing `n(n - 1) / 2` comparisons and O (n²) growth. Random input required about half the reversed-input comparisons
because values generally moved past only part of the sorted section.

### 4. Same Big-O, different times

On random input at n = 32,000, insertion sort took 105.711 ms versus selection sort's 605.202 ms. Selection therefore
took about 5.72 times as long, despite both having O (n²) average-case growth.

Selection always scans the remaining section, while insertion stops at the correct position. Insertion made 256,431,056
comparisons versus selection's 511,984,000. Their different comparison counts, memory operations, and implementation
costs explain why equal Big-O classifications do not imply equal times.

### 5. Arrays.sort compared with merge sort

Arrays.sort was faster at every tested random-input size. At n = 32,000, its 3.337 ms was approximately 8.6% below merge
sort's 3.651 ms.

Algorithms with the same growth class can have different constant costs. A library implementation can use specialized
strategies to reduce overhead. My merge sort creates and copies temporary arrays and updates a comparison counter, which
Arrays.sort does not update. These measurements therefore compare implementations, not just abstract algorithms.

## Limits

Selection sort's random-input time increased from 0.896 ms at n = 1,000 to 6.852 ms at n = 2,000, a ratio of 7.65 rather
than approximately four. Background activity, processor conditions, and Java compilation may affect timings. Warm-up
runs and the median of five trials reduced variation but could not eliminate it.

A second irregularity appeared in Arrays.sort's sorted-input times: doubling n from 4,000 to 8,000 reduced time from
0.049 ms to 0.046 ms. This small difference is not convincing evidence that larger arrays sort faster. More repetitions
would improve confidence in such short measurements.

Only seed 42 was used, so several seeds would provide broader evidence. Counter overhead also limits fairness when
comparing handwritten algorithms with Arrays.sort.

## Recommendation

For large random arrays, I would use Arrays.sort: at n = 32,000 it took 3.337 ms, versus 3.651 ms for merge, 105.711 ms
for insertion, and 605.202 ms for selection. Among my handwritten sorts, I would choose merge for large random inputs.
Insertion was particularly effective on sorted input: 0.048 ms at n = 32,000 versus merge's 1.681 ms.

My first prediction was correct: selection made 499,500 comparisons at n = 1,000 for every input order.

My second prediction was correct among the handwritten sorts. At n = 32,000 on sorted input, insertion was fastest at
0.048 ms and selection slowest at 429.196 ms.

My third prediction was only partly supported by timing. Selection's last two random-input ratios were 2.28 and 2.84
rather than four, although its comparison counts approximately quadrupled. Merge's ratios of 2.26 and 2.30 supported my
prediction of slightly more than double the time.