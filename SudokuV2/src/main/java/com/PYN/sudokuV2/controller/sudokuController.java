package com.PYN.sudokuV2.controller;

import com.PYN.sudokuV2.logic.solver;
import com.PYN.sudokuV2.Model.board;
import com.PYN.sudokuV2.Model.reset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class sudokuController {
    @GetMapping("/") //root of url structure
    public String index() {
        return "index";
    }

    @PostMapping("/solve")//the result of clicking the solve button due to the solve method in html doc
    public String solution(@ModelAttribute board board){
        board.nullToZ(board.getArr());
        solver.solve(board.getArr());
        System.out.println(board.deepToString());

        return "index";

    }

    @PostMapping("/reset")
    public String reset(@ModelAttribute board board) {
        reset.clear(board.getArr());
        return "index";
    }

}