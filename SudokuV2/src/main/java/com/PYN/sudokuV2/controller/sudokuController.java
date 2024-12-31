package com.PYN.sudokuV2.controller;

import com.PYN.sudokuV2.logic.solver;
import com.PYN.sudokuV2.Model.board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class sudokuController {

    private final board board = new board();
    @GetMapping("/") //root of url structure
    public String index(Model model) {
        model.addAttribute("arr", board.getArr());
        return "index";
    }

    @PostMapping("/solve")//the result of clicking the solve button due to the solve method in html doc
    public String solution(@ModelAttribute("board") board board, Model model) {
        board.nullToZ(board.getArr()); //convert null spaces to zeros for solver
        solver.solve(board.getArr()); //Solve the board
        model.addAttribute("arr", board.getArr()); //Pass solved board array to the view
        System.out.println(board.dtString()); //print solution to terminal for debugging
        return "index"; //return page with updated board
    }

    @PostMapping("/reset")
    public String reset(@ModelAttribute("board") board board, Model model) {
        board.clear(board.getArr()); //set bard to null
        model.addAttribute("arr", board.getArr()); //pass board to model
        return "index"; //return page with updated board
    }
}