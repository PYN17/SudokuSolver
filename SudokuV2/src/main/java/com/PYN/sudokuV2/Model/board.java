package com.PYN.sudokuV2.Model;

import java.util.*;


public class board {//This class handles the array and converting the array to the appropriate form for the solver class

    private static int[][] arr = new int[9][9]; //arithmetic operation

    public static int[][] getArr() { return arr; }

    public void setArr(int[][] a) { this.arr = a; }

    public String dtString() { //print for debugging
        return "Board array maybe " + Arrays.deepToString(arr); //Convert
    }

    public static String diffClass(){
        int cnt = 0;
        for (int row = 0; row < 9; row++) { //increment through rows
            for (int col = 0; col < 9; col++) { //increment through columns
                if(arr[row][col] != 0) {
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
}


