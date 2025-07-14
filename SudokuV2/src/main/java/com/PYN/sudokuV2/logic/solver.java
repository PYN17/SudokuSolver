package com.PYN.sudokuV2.logic;

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
                        if (validateUtils.checkRnC(b, row, col, num) && validateUtils.checkB(b, row, col, num)) {//check if input is valid
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

    static int[][] copyBoard(int[][] b){ //Make a copy of the board at this point in the processes
        int[][] copy = new int[9][9];

        for(int i = 0; i < 9; i++){
            copy[i] = b[i].clone(); //save each row
        }
        return copy;
    }
}

