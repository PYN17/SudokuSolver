package com.PYN.sudokuV2.controller;

public class sudokuRequest { //DTO class for handling frontend JSON
    private int[][] arrBoard;

    public sudokuRequest() {}

    public int[][] getArrBoard() { return arrBoard; }

    public void setArrBoard(int[][] arrBoard) {
        this.arrBoard = arrBoard;
        System.out.println("Received board:");
    }

}
