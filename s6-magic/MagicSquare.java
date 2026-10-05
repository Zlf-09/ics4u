/*
 * Name: Oscar Zhu
 * Date: October 4, 2026
 * Description: Stores and checks a square grid of integers
 */

import java.util.Arrays;

public class MagicSquare {
    private final int[][] grid;
    private final int n;

    public MagicSquare(int[][] values) {
        if (values == null) {
            throw new IllegalArgumentException("grid must not be null");
        }
        if (values.length == 0) {
            throw new IllegalArgumentException("grid must have at least one row");
        }
        for (int r = 0; r < values.length; r++) {
            int rowLength = 0;
            if (values[r] != null) {
                rowLength = values[r].length;
            }
            if (rowLength != values.length) {
                throw new IllegalArgumentException("grid must be square: "
                        + values.length + " rows, but row " + r + " has " + rowLength + " values");
            }
        }
        n = values.length;
        grid = new int[n][];
        for (int r = 0; r < n; r++) {
            grid[r] = Arrays.copyOf(values[r], values[r].length);
        }
    }

    /**
     * Returns the size of the square.
     *
     * @return the number of rows and columns
     * Precondition: none
     */
    public int size() {
        return n;
    }

    /**
     * Returns the value at the specified row and column
     *
     * @param row the row index
     * @param col the column index
     * @return the value in that cell
     * Precondition: row and col are valid indices
     */
    public int get(int row, int col) {
        return grid[row][col];
    }

    /**
     * Returns the sum of the specified row
     *
     * @param row the row index
     * @return the sum of the row
     * Precondition: row is a valid index
     */
    public int rowSum(int row) {
        int sum = 0;
        for (int c = 0; c < n; c++) {
            sum += grid[row][c];
        }
        return sum;
    }

    /**
     * Returns the sum of the specified column
     *
     * @param col the column index
     * @return the sum of the column
     * Precondition: col is a valid index
     */
    public int colSum(int col) {
        int sum = 0;
        for (int r = 0; r < n; r++) {
            sum += grid[r][col];
        }
        return sum;
    }

    /**
     * Returns the sum of the main diagonal
     *
     * @return the sum from top-left to bottom-right
     * Precondition: the grid is square
     */
    public int mainDiagonalSum() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[i][i];
        }
        return sum;
    }

    /**
     * Returns the sum of the anti-diagonal
     *
     * @return the sum from top-right to bottom-left
     * Precondition: the grid is square
     */
    public int antiDiagonalSum() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[i][n - 1 - i];
        }
        return sum;
    }

    /**
     * Returns the magic constant for a normal n by n magic square
     *
     * @param n the size of the square
     * @return the magic constant
     * Precondition: n is positive
     */
    public static int magicConstant(int n) {
        return n * (n * n + 1) / 2;
    }

    /**
     * Returns the first problem found in the magic square
     *
     * @return a description of the first problem, or null if there is none
     * Precondition: the grid is square
     */
    public String firstProblem() {
        int target = rowSum(0);
        for (int r = 1; r < n; r++) {
            int sum = rowSum(r);
            if (sum != target) {
                return "row" + r + " sums to " + sum + ", expected " + target;
            }
        }
        for (int c = 0; c < n; c++) {
            int sum = colSum(c);
            if (sum != target) {
                return "column " + c + " sums to " + sum + ", expected " + target;
            }
        }
        int sum = mainDiagonalSum();
        if (sum != target) {
            return "main diagonal sums to " + sum + ", expected " + target;
        }
        sum = antiDiagonalSum();
        if (sum != target) {
            return "anti-diagonal sums to " + sum + ", expected " + target;
        }
        return null;
    }

    /**
     * Returns whether this grid is a magic square
     *
     * @return true if every row, column, and diagonal has the same sum
     * Precondition: the grid is square
     */
    public boolean isMagic() {
        return firstProblem() == null;
    }

    /**
     * Returns whether the grid contains each value from 1 to n squared exactly once
     *
     * @return true if the grid contains every value from 1 to n squared exactly once
     * Precondition: the grid is square
     */
    public boolean isNormal() {
        boolean[] seen = new boolean[n * n + 1];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int value = grid[r][c];
                if (value < 1 || value > n * n || seen[value]) {
                    return false;
                }
                seen[value] = true;
            }
        }
        return true;
    }

    /**
     * Finds the first occurrence of a value in the grid
     *
     * @param value the value to find
     * @return the row and column of the first match, or return null if not found
     * Precondition: none
     */
    public int[] find(int value) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[c][r] == value) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }

    /**
     * Returns the grid as a formatted string
     *
     * @return the formatted grid
     * Precondition: none
     */
    @Override
    public String toString() {
        String result = "";
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                result += String.format("%4d", grid[r][c]);
            }
            if (r < n - 1) {
                result += "\n";
            }
        }
        return result;
    }
}

