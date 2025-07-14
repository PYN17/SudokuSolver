package com.PYN.sudokuV2.controller;

public class sudokuRequest { //Data Transfer Object class handles frontend JSON with Jackson
    private int[][] arrBoard;

    public sudokuRequest() {}

    public int[][] getArrBoard() { return arrBoard; }

    public void setArrBoard(int[][] arrBoard) {
        this.arrBoard = arrBoard;
        System.out.println("Received board:");
    }
}
