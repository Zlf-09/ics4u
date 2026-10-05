## Question 1:
If the constructor stored values directly, Test 9 would print magic false, top-left 99 because changing the original array would also change the MagicSquare's grid. Arrays.copyOf(values, values.length) is not enough because it only copies the outer array, while the rows still refer to the same original row arrays.
## Question 2:
Case 5 shows that checking only the rows and columns is not enough, because they all sum to 6 but the square is still not magic. "firstProblem()" must also check both diagonals.
## Question 3:
"isMagic()" checks whether every row, column, and diagonal has the same sum, while "magicConstant(3)" gives the expected sum for a normal 3 x 3 magic square using 1 to 9. Adding 10 to every cell keeps the square magic because every row, column, and diagonal has three cells, so each total increases by the same 30, from 15 to 45.