import { getSudoku } from 'https://cdn.jsdelivr.net/npm/sudoku-gen@1.0.2/+esm';

const SudokuApp = { //namespace pattern for organization, encapsulation
    stopAnimate: false, //object property now

    init() {
        document.getElementById("solve-btn").addEventListener("click", this.handleSolve.bind(this));
        document.getElementById("reset-btn").addEventListener("click", this.handleReset.bind(this));
        document.getElementById("gen-btn").addEventListener("click", this.handleGenerate.bind(this));
    },

    arrBuilder() {
        let arrBoard = Array.from({length: 9}, () => Array(9).fill(0));

        for (let row = 0; row < 9; row++) {
            for (let col = 0; col < 9; col++) {
                let temp = document.getElementById(`cell-${row}-${col}`).value;
                if (!temp == "") {
                    if (!/^[1-9]$/.test(temp)) {
                        alert("Invalid input: Only digits 1–9 are allowed.");
                        this.resetBoard(); //Use correct scoping
                        return null;
                    }
                }
                arrBoard[row][col] = temp || "0", 10; //convert string to base10 int
            }
        }
        return JSON.stringify({arrBoard});
    },

    boardAnimate(step) {
        for (let row = 0; row < 9; row++) {
            for (let col = 0; col < 9; col++) {
                const cell = document.getElementById(`cell-${row}-${col}`); //cell now references DOM element
                if (step[row][col] == 0) {
                    cell.value = null;
                } else {
                    cell.value = step[row][col];
                }
            }
        }
    },

    boardBuilder(steps, onFinish) { //Using callback to manage sequence of events with setInterval
        let i = 0;
        let delay = 0;
        let size = steps.length;
        SudokuApp.stopAnimate = false; //in case reset was previous clicked

        if(size < 3000) { //set animation delay dynamically to prevent long animations
            delay = 7;
        }
        else if(size < 6000) {
            delay = 5;
        }
        else {
            delay = 1;
        }

        const interval = setInterval(() => {
            if (i >= steps.length) {
                clearInterval(interval);
                onFinish();
                return;
            }
            if (SudokuApp.stopAnimate) {
                return;
            }
            this.boardAnimate(steps[i]);
            i++;
        }, delay) //delay between board states for animation
    },

    setStats(steps, diff, dura) {
        document.getElementById("steps-text").textContent = `Steps to Solve: ${steps}`;
        document.getElementById("diff-text").textContent = `Difficulty: ${diff}`;
        document.getElementById("dur-text").textContent = `Duration of Solve: ${dura}`;
    },

    resetBoard() {
        for (let row = 0; row < 9; row++) {
            for (let col = 0; col < 9; col++) {
                const cell = document.getElementById(`cell-${row}-${col}`);
                cell.value = "";
            }
        }

        document.getElementById("steps-text").textContent = "Steps to Solve: --";
        document.getElementById("diff-text").textContent = "Difficulty: --";
        document.getElementById("dur-text").textContent = "Duration of Solve: --";

        document.getElementById("solved-border").classList.remove("solved");
    },


    handleSolve(event) { //Listener callback for solve button
        event.preventDefault(); //Prevent unplanned behavior
        const requestBody = this.arrBuilder(); //Convert board to JSON for backend
        if (!requestBody) { //Ensure request body has contents
            return; //exit if contents is null, Builder signals bad input with null board.
        }

        fetch("/solve", { //Send POST request to backend
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: requestBody
        })

            .then(async response => { //Wait for response and parse
                if(!response.ok) { //Handle bad responses
                    const data = await response.text(); //parse error
                    alert(data.error || "Unknown error occurred.");
                    this.resetBoard();
                    return null; //Stop process after notification of error
                }
                return response.json(); //Pass parsed JSON to next then if valid
            })

            .then(data => { //Accept incoming data, populate frontend UI
                console.log("Solved Board:", data.SolvedBoard); //debugging
                this.boardBuilder(data.steps, () => {
                    document.getElementById("solved-border").classList.add("solved");
                });
                this.setStats(data.stepCnt, data.difficulty, data.timeStat);
            })

            .catch(error => {
                console.error("There was a problem with the solve request: ", error);
            });
    },

    handleReset(event) {
        event.preventDefault();
        SudokuApp.stopAnimate = true;
        this.resetBoard();
    },

    handleGenerate(event) {
        const difficulty = document.getElementById("diff-select").value;

        const {puzzle} = getSudoku(difficulty); //flat 81-char string

        for (let i = 0; i < 81; i++) {
            const row = Math.floor(i / 9);
            const col = i % 9;
            const cell = document.getElementById(`cell-${row}-${col}`);
            const val = puzzle[i];

            cell.value = val === '-' ? "" : parseInt(val);
        }
    }
};

SudokuApp.init(); //Entry point
