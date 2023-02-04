package com.PYN.sudokuV2.Model;

import java.util.*;

public class board {//This class handles the array and converting the array to the appropriate form for the solver class
    private Integer[][] arr;

    public Integer[][] getArr() {
        return arr;
    }

    public void setArr(Integer[][] arr) {
        this.arr = arr;
    }

    public String deepToString() {
        return "Board array maybe " + Arrays.deepToString(arr); //prints for debugging
    }

    public void nullToZ(Integer[][] b) {//converts empty fields from null to 0s for the solver
            for (int row = 0; row < 9; row++) {//increment through rows
                for (int col = 0; col < 9; col++) {//increment through columns
                    if (b[row][col] == null) {//only perform operation on empty cells
                        b[row][col] = 0;
                    }
                }
            }
        //return "Board array maybe " + Arrays.deepToString(arr);
    }
}


