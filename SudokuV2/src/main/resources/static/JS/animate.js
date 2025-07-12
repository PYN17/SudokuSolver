import { getSudoku } from 'https://cdn.jsdelivr.net/npm/sudoku-gen@1.0.2/+esm';
let stopAnimate = false;

export function arrBuilder() {
    let arrBoard = Array.from({length: 9}, () => Array(9).fill(0));

    for (let row = 0; row < 9; row++) {
        for (let col = 0; col < 9; col++) {
            let temp = document.getElementById(`cell-${row}-${col}`).value;
            if (!temp === "") {
                if (!/^[1-9]$/.test(temp)) {
                    alert("Invalid input: Only digits 1–9 are allowed.");
                    resetBoard();
                    return null;
                }
            }
            arrBoard[row][col] = temp || 0, 10;
        }
    }
    return JSON.stringify({arrBoard});
}

export function boardAnimate(step) {
    for (let row = 0; row < 9; row++) {
        for (let col = 0; col < 9; col++) {
            const cell = document.getElementById(`cell-${row}-${col}`);
            if (step[row][col] == 0) {
                cell.value = null;
            } else {
                cell.value = step[row][col];
            }
        }
    }
}

export function boardBuilder(steps) {
    let i = 0;
    stopAnimate = false;

    const interval = setInterval(() => {
        if (i >= steps.length) {
            clearInterval(interval);
            document.getElementById("solved-border").classList.add("solved");
            return;
        }
        if (stopAnimate) {
            return;
        }
        boardAnimate(steps[i]);
        i++;
    }, 10)
}

export function setStats(steps, diff, dura) {
    document.getElementById("steps-text").textContent = `Steps to Solve: ${steps}`;
    document.getElementById("diff-text").textContent = `Difficulty: ${diff}`;
    document.getElementById("dur-text").textContent = `Duration of Solve: ${dura}`;
}

export function resetBoard() {
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
}

document.getElementById("solve-btn").addEventListener("click", function (event) {
    event.preventDefault();

    const requestBody = arrBuilder();

    if (!requestBody) {
        return;
    }

    fetch("/solve", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: requestBody
    })
        .then(async (response) => {
            const data = await response.json();
            if (!response.ok) {
                alert(data.error || "Unknown error occurred.");
                resetBoard();
                return;
            }

            console.log("Solved Board:", data.SolvedBoard);
            boardBuilder(data.steps);
            setStats(data.stepCnt, data.difficulty, data.timeStat);
        })

        .catch(error => {
            console.error("There was a problem with the solve request: ", error);
        });
});

document.getElementById("reset-btn").addEventListener("click", function (event) {
    event.preventDefault();
    stopAnimate = true;
    resetBoard();
})

document.getElementById("gen-btn").addEventListener("click", async () => {
    const difficulty = document.getElementById("diff-select").value;

    const { puzzle } = getSudoku(difficulty); //flat 81-char string

    for (let i = 0; i < 81; i++) {
        const row = Math.floor(i / 9);
        const col = i % 9;
        const cell = document.getElementById(`cell-${row}-${col}`);
        const val = puzzle[i];

        cell.value = val === '-' ? "" : parseInt(val);
    }
});
