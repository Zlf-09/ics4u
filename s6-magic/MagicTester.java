/*
 * Name: Oscar Zhu
 * Date: October 5, 2026
 * Description: Tests the MagicSquare class with different grids
 */
public class MagicTester {
    private static void report(String label, int[][] values) {
        MagicSquare square = new MagicSquare(values);
        System.out.println(label + " (" + square.size() + " x " + square.size() + ")");
        System.out.println(square);
        if (square.isMagic()) {
            System.out.println("magic, every line sums to " + square.rowSum(0));
        } else {
            System.out.println("not magic: " + square.firstProblem());
        }
        System.out.println("uses 1 to " + (square.size() * square.size()) + " once each: " + square.isNormal());
    }

    /**
     * Runs all test cases for the MagicSquare class
     *
     * @param args command-line arguments
     *             Precondition: none
     */
    public static void main(String[] args) {
        report("1. Lo Shu", new int[][]{
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 8}
        });
        report("2. Durer", new int[][]{
                {16, 3, 2, 13},
                {5, 10, 11, 8},
                {9, 6, 7, 12},
                {4, 15, 14, 1}
        });
        report("3. Lo Shu plus 10", new int[][]{
                {12, 17, 16},
                {19, 15, 11},
                {14, 13, 18}
        });
        report("4. Swapped cells", new int[][]{
                {7, 2, 6},
                {9, 5, 1},
                {4, 3, 8}
        });
        report("5. Latin square", new int[][]{
                {1, 2, 3},
                {2, 3, 1},
                {3, 1, 2}
        });
        report("6. One cell", new int[][]{{1}});
        System.out.println("7. magic constants: n=3 " + MagicSquare.magicConstant(3)
                + ", n=4 " + MagicSquare.magicConstant(4) + ", n=5 " + MagicSquare.magicConstant(5));
        MagicSquare loShu = new MagicSquare(new int[][]{
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 8}
        });
        int[] position = loShu.find(5);
        System.out.println("8. find(5): row " + position[0] + ", column " + position[1]);
        position = loShu.find(10);
        if (position == null) {
            System.out.println("find(10): not found");
        }
        int[][] original = {
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 8}
        };
        MagicSquare copy = new MagicSquare(original);
        original[0][0] = 99;
        System.out.println("9. after changing the original array: magic "
                + copy.isMagic() + ", top-left " + copy.get(0, 0));
        try {
            new MagicSquare(null);
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("10. null rejected: " + e.getMessage());
        }
        try {
            new MagicSquare(new int[0][0]);
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("11. no rows rejected: " + e.getMessage());
        }
        try {
            new MagicSquare(new int[][]{
                    {1, 2, 3},
                    {4, 5, 6}
            });
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("12. 2 x 3 rejected: " + e.getMessage());
        }
        try {
            new MagicSquare(new int[][]{
                    {1, 2},
                    {3, 4, 5}
            });
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("13. jagged rejected: " + e.getMessage());
        }
    }
}
