package com.PYN.sudokuV2.controller;

import com.PYN.sudokuV2.logic.solver;
import com.PYN.sudokuV2.Model.board;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class sudokuController {
    @PostMapping(value = "/solve", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> solve(@RequestBody sudokuRequest request){
        board board = new board();
        board.setArr(request.getArrBoard());

        System.out.println(board.dtString()); //print starting board to terminal for debugging

        if(!board.isValid(board.getArr())){
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "All 3x3 subgrid, row, and column inputs must be unique.");
            return ResponseEntity
                    .badRequest()
                    .body(errorBody);
        }

        String diffLvl = board.diffClass();
        List<int[][]> steps = new ArrayList<>(); //store steps of solving process for animation
        long start = System.nanoTime(); //record time for solve
        solver.solve(board.getArr(), steps); //Solve the board
        long end = System.nanoTime();

        Map<String,Object> response = new HashMap<>();
        response.put("difficulty", diffLvl);
        response.put("steps", steps); // For animation
        response.put("stepCnt", steps.size()); // For stats
        response.put("timeStat", (end - start) / 1_000_000 + "ms"); //For time stat
        response.put("SolvedBoard", board.getArr());
        System.out.println("Solved Board:");
        System.out.println(board.dtString()); //print solution to terminal for debugging

        return ResponseEntity.ok(response);
    }
}