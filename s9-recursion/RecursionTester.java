/*
 * Oscar Zhu
 * October 27, 2026
 * Tests the recursive methods and Towers of Hanoi.
 */
public class RecursionTester {
    public static void main(String[] args) {
        System.out.println("1. power(2, 10) = " + Recursion.power(2, 10));
        System.out.println("2. power(3, 0) = " + Recursion.power(3, 0));
        System.out.println("3. power(-2, 5) = " + Recursion.power(-2, 5));
        System.out.println("4. power(10, 18) = " + Recursion.power(10, 18));
        try {
            Recursion.power(2, -1);
            System.out.println("5. FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("5. power(2, -1) rejected: " + e.getMessage());
        }
        System.out.println("6. reverseString(\"recursion\") = \""
                + Recursion.reverseString("recursion") + "\"");
        System.out.println("7. reverseString(\"racecar\") = \""
                + Recursion.reverseString("racecar") + "\"");
        System.out.println("8. reverseString(\"\") = \""
                + Recursion.reverseString("") + "\"");
        try {
            Recursion.reverseString(null);
            System.out.println("9. FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("9. reverseString(null) rejected: " + e.getMessage());
        }
        System.out.println("10. sumDigits(2026) = " + Recursion.sumDigits(2026));
        System.out.println("11. sumDigits(7) = " + Recursion.sumDigits(7));
        System.out.println("12. sumDigits(0) = " + Recursion.sumDigits(0));
        System.out.println("13. sumDigits(2147483647) = " + Recursion.sumDigits(2147483647));

        try {
            Recursion.sumDigits(-45);
            System.out.println("14. FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("14. sumDigits(-45) rejected: " + e.getMessage());
        }
        System.out.println("15. Hanoi, 3 disks, A to C:");
        Hanoi.resetMoves();
        Hanoi.solve(3, 'A', 'C', 'B', true);
        System.out.println("  total moves: " + Hanoi.getMoves());
        System.out.println("16. Hanoi move counts (disks: moves):");
        int[] diskCounts = {1, 2, 3, 4, 5, 10, 20};
        for (int n : diskCounts) {
            Hanoi.resetMoves();
            Hanoi.solve(n, 'A', 'C', 'B', false);
            System.out.println("  " + n + ": " + Hanoi.getMoves());
        }
        Hanoi.resetMoves();
        Hanoi.solve(0, 'A', 'C', 'B', true);
        System.out.println("17. Hanoi, 0 disks: " + Hanoi.getMoves() + " moves");
        try {
            Hanoi.solve(-1, 'A', 'C', 'B', true);
            System.out.println("18. FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("18. Hanoi, -1 disks rejected: " + e.getMessage());
        }
    }
}
