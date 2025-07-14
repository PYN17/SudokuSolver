package com.PYN.sudokuV2.logic;

public class validateUtils {
    public static boolean checkRnC(int[][] b, int row, int col, int num) {//check for duplicates in rows and columns
        for (int i = 0; i < 9; i++) {//increment
            if (num == b[row][i] || num == b[i][col]) {//check if we have repeats in rows or columns
                return false;//check for false so loop finishes before returning true
            }
        }
        return true;
    }

    public static boolean checkB(int[][] b, int row, int col, int num) {//check for repeats in block
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b[(row - row % 3) + i][(col - col % 3) + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
