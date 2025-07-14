package com.PYN.sudokuV2.Model;

import com.PYN.sudokuV2.logic.validateUtils;
import java.util.*;


public class board {//This class handles the array and converting the array to the appropriate form for the solver class

    private int[][] arrBoard = new int[9][9]; //arithmetic operation

    public int[][] getArr() { return arrBoard; }

    public void setArr(int[][] a) { this.arrBoard = a; }

    public String dtString() { //print for debugging
        return "Board array: " + Arrays.deepToString(arrBoard); //Convert
    }

    public String diffClass(){
        int cnt = 0;
        for (int row = 0; row < 9; row++) { //increment through rows
            for (int col = 0; col < 9; col++) { //increment through columns
                if(arrBoard[row][col] != 0) {
                    cnt++;
                }
            }
        }
        if(cnt <= 20){
            return "Hard";
        } else if (cnt <= 30){
            return "Medium";
        }
        return "Easy";
    }

    public boolean isValid(int[][] b){ //confirm user input is valid
        int[][] arr = new int[9][9]; //use empty array for validation so previous board check logic can be reused

        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(b[row][col] != 0) {
                    if (b[row][col] < 0 || b[row][col] > 9) {
                        System.out.println("Bad numbers");
                        return false;
                    } //reuse old logic
                    if (!(validateUtils.checkB(arr, row, col, b[row][col]) && validateUtils.checkRnC(arr, row, col, b[row][col]))) {
                        System.out.println("Bad number placement");
                        return false;
                    }
                    arr[row][col] = b[row][col]; //set value to new board and continue to verify
                }
            }
        }
        return true;
    }
}


