package com.PYN.sudokuV2.logic;

import java.util.ArrayList;
import java.util.List;

//Ver 0.2
//Improvements: GUI, smarter code, apply best practices
public class solver {
    //for num to be correct it must only appear in row or column once and 3X3 square once
    public static boolean solve(int[][] b, List<int[][]> steps) {
        for (int row = 0; row < 9; row++) {//increment through rows
            for (int col = 0; col < 9; col++) {//increment through columns
                if (b[row][col] == 0) {//only perform operation on empty cells
                    for (int num = 1; num < 10; num++) {//check for possible solutions
                        if (checkRnC(b, row, col, num) && checkB(b, row, col, num)) {//check if input is valid
                            b[row][col] = num;//if conditions are met change value in cell Recursion!
                            //copy board to record process at this point
                            steps.add(copyBoard(b));
                            if (solve(b, steps)) {//Time Travel! If the second move is correct then the first move is correct.
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

    static boolean checkRnC(int[][] b, int row, int col, int num) {//check for duplicates in rows and columns
        for (int i = 0; i < 9; i++) {//increment
            if (num == b[row][i] || num == b[i][col]) {//check if we have repeats in rows or columns
                return false;//check for false so loop finishes before returning true
            }
        }
        return true;
    }

    static boolean checkB(int[][] b, int row, int col, int num) {//check for repeats in block
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b[(row - row % 3) + i][(col - col % 3) + j] == num) {
                    return false;//check for false so loop finishes before returning true
                }
            }
        }
        return true;
    }

    public static boolean isValid(int[][] b){ //confirm user input is valid
        int[][] arr = new int[9][9]; //use empty array for validation so previous board check logic can be reused

        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(b[row][col] != 0) {
                    if (b[row][col] < 0 || b[row][col] > 9) {
                        System.out.println("Bad numbers");
                        return false;
                    } //reuse old logic
                    if (!(checkB(arr, row, col, b[row][col]) && checkRnC(arr, row, col, b[row][col]))) {
                        System.out.println("Bad number placement");
                        return false;
                    }
                    arr[row][col] = b[row][col]; //set value to new board and continue to verify
                }
            }
        }
        return true;
    }

    static int[][] copyBoard(int[][] b){ //Make a copy of the board at this point in the processes
        int[][] copy = new int[9][9];

        for(int i = 0; i < 9; i++){
            copy[i] = b[i].clone(); //save each row
        }
        return copy;
    }

}

