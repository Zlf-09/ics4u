# Session 9 Homework: Answers

## 1. Call stack for reverseString("abc")

At the deepest point, the call stack is: 
```text
Top
reverseString("c")
reverseString("bc")
reverseString("abc")
Bottom
```
The calls return in this order:

1. `reverseString("c")` returns `"c"`.
2. `reverseString("bc")` returns `"c" + "b" , which is `"cb"`.
3. `reverseString("abc")` returns `"cb" + "a"`, which is `"cba"`.

## 2. Towers of Hanoi move count
Let `T(n)` be the number of moves needed for `n` disks.
The method first moves `n - 1` disks, then moves the largest disk 
once, and finally moves the `n - 1` disks again. Therefore:
```text
T(0) = 0
T(n) = 2T(n - 1) + 1
```
This gives `T(n) = 2^n - 1`.
For 20 disks:
```text
T(20) = 2^20 - 1 = 1048575 moves
```
At one move per second, this takes 1048575 seconds, or 12 days, 3 hours, 16 minutes and 15 seconds.

## 3. Negative exponent without a guard

When I temporarily removed the negative exponent check and called `power(2, -1)`, I got:

```text
Exception in thread "main" java.lang.StackOverflowError 
at Recursion.power(Recursion.java:24)
```
The exponent changed from -1 to -2, then -3, and so on.
It moves away from the base case `exp == 0`, so the recursive calls kept accumulating until the call stack ran out of space.
This is the case where a base case exists but the recursive calls do not reach it.

I restored the negative exponent check afterwards.

## 4. Recursive calls and complexity 
For `power(2, 10)`, the exponent goes from 10 down to 0.
Including the initial call and the base case, there are 11 calls.
In general, for a non-negative exponent `exp`, there are `exp + 1` calls, so the time complexity is 0(exp + 1).
For `sumDigits(n)`, each recursive call removes the last digit using integer division by 10. 
If `n` has `d` digits, there are `d` calls, including the base case, so the time complexity is 0(d).

For example, `sumDigits(2026)` calls the method with 2026, 202, 20, and 2: four calls in total.
