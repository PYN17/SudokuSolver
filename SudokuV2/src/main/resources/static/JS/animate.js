export function arrBuilder(){
    let arrBoard = Array.from({length: 9}, () => Array(9).fill(0));

    for(let row = 0; row < 9; row++){
        for(let col = 0; col < 9; col++){
            arrBoard[row][col] = parseInt(document.getElementById(`cell-${row}-${col}`).value || "0", 10)
        }
    }
    return JSON.stringify({ arrBoard });
}

export function boardAnimate(step){
    for(let row = 0; row < 9; row++){
        for(let col = 0; col < 9; col++){
            const cell = document.getElementById(`cell-${row}-${col}`);
            if(step[row][col] == 0) {
                cell.value = null
            } else {
                cell.value = step[row][col];
            }
        }
    }
}

export function boardBuilder(steps) {
    let i = 0;
    const interval = setInterval(() => {
        if(i >= steps.length) {
            clearInterval(interval);
            document.getElementById("solved-border").classList.add("solved");
            return;
        }
        boardAnimate(steps[i]);
        i++;
    }, 10)
}

export function setStats(steps, diff, dura){
    document.getElementById("steps-text").textContent = `Steps to Solve: ${steps}`;
    document.getElementById("diff-text").textContent = `Difficulty: ${diff}`;
    document.getElementById("dur-text").textContent = `Duration of Solve: ${dura}`;
}

export function resetBoard(){
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

    fetch("/solve", {
        method: "POST",
    headers: {
        "Content-Type": "application/json"
        },
        body:arrBuilder()
    })
        .then(response => {
            if(!response.ok) throw new Error("Network response was not ok");
            return response.json();
        })
        .then(data => {
            console.log("Solved Board:", data.solvedBoard);
            boardBuilder(data.steps);
            setStats(data.stepCnt, data.difficulty, data.timeStat);
        })
        .catch(error => {
            console.error("There was a problem with the solve request: ", error);
        });
});

document.getElementById("reset-btn").addEventListener("click", function (event) {
    event.preventDefault();
    resetBoard();
})