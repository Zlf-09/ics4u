# Sorting and Efficiency Report

Oscar Zhu — ICS4U

## Question

How do array size and input order affect the comparison counts and running times of selection sort, insertion sort,
merge sort, and Java's Arrays.sort? I compared measured results with the expected growth of each algorithm.

## Method

I used a 2023 15-inch MacBook Air with an Apple M2 chip, 8 GB of RAM, macOS, and Temurin JDK 25. The array sizes were
1,000, 2,000, 4,000, 8,000, 16,000, and 32,000. Each size had random, sorted, and reversed integer inputs. Random arrays
used seed 42 and values below 1,000,000. Sorted arrays contained 0 through n - 1; reversed arrays contained n through 1.

Each combination was timed five times using fresh copies of the same original array. I recorded the median in
milliseconds using System.nanoTime (). Array generation and copying occurred before timing, and the sorted-order check
occurred afterward. The submitted experiment program also includes three warm-up runs per algorithm using 5,000-element
random arrays.

For comparison counts, I reset Sorts.comparisons before a separate sorting run. The counter measured comparisons between
integer values, including comparisons returning false, but excluded index checks. Arrays.sort comparisons were not
measured and are blank in the CSV. The handwritten algorithms also updated their counters during timing, adding
overhead to their measurements.

## Results

### Value comparisons on random input

|     n | Selection | Insertion |  Merge | Arrays.sort |
|------:|----------:|----------:|-------:|------------:|
|  1000 |    499500 |    253081 |   8688 |          NA |
|  2000 |   1999000 |    996913 |  19406 |          NA |
|  4000 |   7998000 |   3971946 |  42818 |          NA |
|  8000 |  31996000 |  16226374 |  93676 |          NA |
| 16000 | 127992000 |  64542397 | 203349 |          NA |
| 32000 | 511984000 | 256431056 | 438593 |          NA |

### Median times and doubling ratios on random input

Times are in milliseconds. Each ratio divides the current time by the time at half the array size. Times are displayed
to three decimal places; ratios use the three-decimal CSV values.

|     n | Selection ms | Ratio | Insertion ms | Ratio | Merge ms | Ratio | Arrays.sort ms | Ratio |
|------:|-------------:|------:|-------------:|------:|---------:|------:|---------------:|------:|
|  1000 |        1.285 |     — |        0.113 |     — |    0.066 |     — |          0.063 |     — |
|  2000 |        9.606 |  7.48 |        0.436 |  3.86 |    0.197 |  2.98 |          0.165 |  2.62 |
|  4000 |       26.531 |  2.76 |        1.700 |  3.90 |    0.389 |  1.97 |          0.346 |  2.10 |
|  8000 |      128.103 |  4.83 |        6.874 |  4.04 |    0.822 |  2.11 |          0.736 |  2.13 |
| 16000 |      326.501 |  2.55 |       27.177 |  3.95 |    1.918 |  2.33 |          1.717 |  2.33 |
| 32000 |      643.377 |  1.97 |      119.693 |  4.40 |    3.874 |  2.02 |          2.984 |  1.74 |

![Median sorting time on random input](chart.png)

Complete measurements for all input types are in [results.csv](results.csv).

## Analysis

### 1. Doubling ratios

Selection sort's time ratios ranged from 1.97 to 7.48 and did not settle near four. Its final three ratios were 4.83,
2.55, and 1.97. However, its comparison counts approximately quadrupled with each doubling of n, providing clearer
evidence of quadratic growth than these variable timings.

Insertion sort's ratios ranged from 3.86 to 4.40, close to four. Doubling n therefore approximately quadrupled its
running time. Its random-input comparison counts also grew approximately fourfold, supporting O (n²) average-case
growth.

Merge sort's final three ratios were 2.11, 2.33, and 2.02; Arrays.sort's were 2.13, 2.33, and 1.74. Merge sort's
larger-input timings roughly doubled, broadly consistent with n log n growth, which predicts slightly more than twice
the work when n doubles. Arrays.sort showed more variation, including a final ratio below two. Timing measurements alone
cannot establish a complexity class.

### 2. Counts against the formulas

Selection sort matched `n(n - 1) / 2` exactly: 499,500 comparisons at n = 1,000 and 511,984,000 at n = 32,000. It scans
the remaining unsorted section regardless of input order.

For merge sort, `n log₂(n)` gives approximately 9,966 at n = 1,000 and 478,905 at n = 32,000. The measured counts, 8,688
and 438,593, were approximately 87.2% and 91.6% of those estimates. The expression describes the growth scale, not an
exact count. Once one side of a merge is exhausted, copying the remaining values requires no further value comparisons.

### 3. Insertion sort's best and worst cases

At n = 32,000, insertion sort made 31,999 comparisons on sorted input, 256,431,056 on random input, and 511,984,000 on
reversed input.

On sorted input, each value immediately fails the greater-than test, requiring no shifts. This produces n - 1
comparisons and O (n) best-case growth. On reversed input, each value moves past every preceding value, producing
`n(n - 1) / 2` comparisons and O (n²) growth. Random input required about half the reversed-input comparisons because
values generally moved past only part of the sorted section.

### 4. Same Big-O, different times

At n = 32,000 on random input, selection sort took 643.377 ms and insertion sort took 119.693 ms. Selection therefore
took about 5.38 times as long, although both have O (n²) average-case growth.

Selection always scans the remaining unsorted section. Insertion stops when it finds the correct position. Insertion
made 256,431,056 comparisons versus selection's 511,984,000. Different comparison counts, memory operations, and
implementation costs explain why equal Big-O classifications do not imply equal running times.

### 5. Arrays.sort compared with merge sort

In this run, Arrays.sort was faster at all six tested random-input sizes. At n = 32,000, Arrays.sort took 2.984 ms
versus merge sort's 3.874 ms, approximately 23.0% less time.

A library implementation can use specialized strategies to reduce overhead. My merge sort allocates and copies temporary
arrays and updates a comparison counter, while Arrays.sort does not update that counter. These factors may contribute to
the measured difference, although the experiment does not isolate their individual effects. These results compare the
tested implementations under this experiment's conditions.

## Limits

One irregularity was selection sort's random-input time increasing from 1.285 ms at n = 1,000 to 9.606 ms at n = 2,000,
a ratio of 7.48 rather than approximately four. Compilation, background activity, and processor conditions may affect
timings. More warm-up runs and repeated benchmark sessions would improve confidence.

Another irregularity was Arrays.sort's sorted-input time falling from 0.056 ms at n = 4,000 to 0.034 ms at n = 8,000.
This does not establish that larger arrays sort faster. Measuring batches of short sorts and reporting variation across
repeated sessions would make such small timings more reliable. Rounding to three decimal places also limits precision
for very short measurements.

Only seed 42 was used. Several seeds would provide broader evidence. Counter overhead also limits fairness when
comparing handwritten sorts with Arrays.sort. Separate timing versions without counters would improve that comparison.

## Recommendation

For large random arrays, I would choose Arrays.sort: at n = 32,000 it took 2.984 ms, compared with 3.874 ms for merge,
119.693 ms for insertion, and 643.377 ms for selection. Among my handwritten algorithms, merge was strongest for large
random inputs. For sorted input, insertion was particularly effective: 0.056 ms versus merge's 1.255 ms at n = 32,000.

My first prediction was correct: selection made 499,500 comparisons at n = 1,000 for every input order.

My second prediction was correct among the handwritten sorts. At n = 32,000 on sorted input, insertion was fastest at
0.056 ms and selection slowest at 421.072 ms.
My third prediction received mixed timing support. Selection's last ratio was 1.97 rather than four, while its
comparison counts approximately quadrupled. Merge's last ratio was 2.02, supporting slightly more than double the time
for that interval, although earlier ratios varied.
