/*
 * Oscar Zhu
 * October 27, 2026
 * Solves the Towers of Hanoi and counts the moves
 */
public class Hanoi {
    private static long moves = 0;

    /**
     * Returns the number of moves since the last reset
     * Precondition: none
     * Base case: not applicable; this method is not recursive
     *
     * @return the current move count
     */

    public static long getMoves() {
        return moves;
    }

    /**
     * Resets the move count to zero
     * Precondition: none
     * Base case: not applicable; this method is not recursive
     */
    public static void resetMoves() {
        moves = 0;
    }

    /**
     * Moves n disks from one peg to another using a spare peg.
     * Precondition: n is non-negative; the three pegs are distinct.
     * Base case: n is 0, so no moves are needed.
     *
     * @param n     the number of disks to move
     * @param from  the starting peg
     * @param to    the destination peg
     * @param spare the spare peg
     * @param print whether to print each move
     * @throws IllegalArgumentException if n is negative
     */
    public static void solve(int n, char from, char to, char spare, boolean print) {
        if (n < 0) {
            throw new IllegalArgumentException("disks must not be negative");
        }
        if (n == 0) {
            return;
        }
        solve(n - 1, from, spare, to, print);
        moves++;
        if (print) {
            System.out.println("  move disk " + n + " from " + from + " to " + to);
        }
        solve(n - 1, spare, to, from, print);
    }
}
