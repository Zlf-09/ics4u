/*
 * Oscar Zhu
 * October 26, 2026
 * Provides recursive methods for powers, string reversal and digit sums.
 */
public class Recursion {
    // Base case: If the exponent is 0, return 1.
    // Recursive case: Multiply the base by the result with the exponent reduced by 1.

    /**
     * Calculates a base raised to a non-negative integer exponent
     * Precondition: exp is non-negative
     * Base case: exp is 0
     *
     * @param base the base
     * @param exp  the non-negative exponent
     * @return the base raised to the exponent
     * @throws IllegalArgumentException if exp is negative
     */
    public static long power(long base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("exponent must not be negative");
        }
        if (exp == 0) {
            return 1;
        }
        return base * power(base, exp - 1);
    }
    // Base case: A string of length 0 or 1 is already reversed
    // Recursive case: Reverse the rest of the string, then add the first character.

    /**
     * Returns a string in reverse order
     * Precondition: s is not null
     * Base case: s has length 0 or 1
     *
     * @param s the string to reverse
     * @return the reversed string
     * @throws IllegalArgumentException if s is null
     */
    public static String reverseString(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s must not be null");
        }
        if (s.length() <= 1) {
            return s;
        }
        return reverseString(s.substring(1)) + s.charAt(0);
    }
    // Base case: A single-digit number is its own digit sum
    // Recursive case: Add the last digit to the digit sum of the remaining digits.

    /**
     * Returns the sum of the digits of a non-negative integer.
     * Precondition: n is non-negative
     * Base case: n is less than 10
     *
     * @param n the number whose digits are added
     * @return the sum of the digits
     * @throws IllegalArgumentException if n is negative
     */
    public static int sumDigits(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        if (n < 10) {
            return n;
        }
        return n % 10 + sumDigits(n / 10);
    }
}
