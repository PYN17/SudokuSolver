package com.PYN.sudokuV2.Model;

import java.util.*;


public class board {//This class handles the array and converting the array to the appropriate form for the solver class
    private static Integer[][] arr = new Integer[9][9];

    public static Integer[][] getArr() {
        return arr;
    }

    public void setArr(Integer[][] arr) {
        this.arr = arr;
    }

    public String dtString() { //print for debugging
        return "Board array maybe " + Arrays.deepToString(arr); //Convert
    }

    public void nullToZ(Integer[][] b) {//converts empty fields from null to 0s for the solver
            for (int row = 0; row < 9; row++) {//increment through rows
                for (int col = 0; col < 9; col++) {//increment through columns
                    if (b[row][col] == null) {//only perform operation on empty cells
                        b[row][col] = 0;
                    }
                }
            }
    }

    public static void clear(Integer[][] arr){ //clear board/array for reuse
        for (int row = 0; row < 9; row++) { //increment through rows
            for (int col = 0; col < 9; col++) { //increment through columns
                arr[row][col] = null;
            }
        }
    }
}


