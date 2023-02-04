package com.PYN.sudokuV2.Model;

import java.util.*;
public class reset {
    public static void clear(Integer[][] arr){//clear board/array for reuse
        for (int row = 0; row < 9; row++) {//increment through rows
            for (int col = 0; col < 9; col++) {//increment through columns
                arr[row][col] = 0;

            }
        }
    }
}
