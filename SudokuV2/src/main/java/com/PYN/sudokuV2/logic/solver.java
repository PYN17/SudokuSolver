package com.PYN.sudokuV2.logic;

//Ver 0.1
//Improvements: hash mapping, GUI, smarter code, apply best practices
public class solver {

    public static void main(String[] args) {//main method
    }

    /*public static class board { //test board
        static int[][] b = { //sudoku board
                {0, 0, 0, 8, 0, 7, 9, 1, 0},
                {9, 0, 0, 3, 4, 0, 2, 0, 0},
                {0, 0, 5, 0, 0, 0, 0, 7, 0},
                {5, 9, 3, 7, 0, 2, 0, 6, 4},
                {0, 0, 1, 0, 0, 0, 0, 3, 8},
                {8, 7, 0, 6, 3, 1, 0, 9, 2},
                {7, 4, 0, 0, 0, 0, 0, 2, 0},
                {0, 0, 0, 0, 0, 4, 3, 0, 0},
                {0, 5, 2, 1, 7, 0, 0, 0, 0},};

        //prints out array board
        static void showBoard(int[][] f) {//methods must have parameters
            for (int row = 0; row < 9; row++) {//increments through rows
                if (row % 3 == 0 && row != 0) {//print bottom boarder
                    System.out.print("\n---------------------");
                }
                if (row != 0) {
                    System.out.print("\n");
                }
                for (int col = 0; col < 9; col++) {//increments through columns per row
                    if (col != 0 && col % 3 == 0) {
                        System.out.print("|" + " ");//prints side boarder
                    }
                    System.out.print(board.b[row][col] + " ");//improves readability
                }
            }
            System.out.print("\n\n");
        }
    }
*/

    //for num to be correct it must only appear in row or column once and 3X3 square once
    public static boolean solve(Integer[][] b) {
        for (int row = 0; row < 9; row++) {//increment through rows
            for (int col = 0; col < 9; col++) {//increment through columns
                if (b[row][col] == 0) {//only perform operation on empty cells
                    for (int num = 1; num < 10; num++) {//check for possible solutions
                        if (checkRnC(b, row, col, num) && checkB(b, row, col, num)) {//check if input is valid
                            b[row][col] = num;//if conditions are met change value in cell Recursion!
                            if (solve(b)) {//Time Travel! If the second move is correct then the first move is correct.
                                return true;
                            } else {
                                b[row][col] = 0;//if conditions are not met set cell to blank
                            }
                        }
                    }
                    return false;//if number is not valid backtrack
                }
            }
        }
        return true;//if all loops are completed conditions are true
    }

    static boolean checkRnC(Integer[][] b, int row, int col, int num) {//check for duplicates in rows and columns
        for (int i = 0; i < 9; i++) {//increment
            if (num == b[row][i] || num == b[i][col]) {//check if we have repeats in rows or columns
                return false;//check for false so loop finishes before returning true
            }
        }
        return true;
    }

    static boolean checkB(Integer[][] b, int row, int col, int num) {//check for repeats in block
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b[(row - row % 3) + i][(col - col % 3) + j] == num) {
                    return false;//check for false so loop finishes before returning true
                }
            }
        }
        return true;
    }

}

