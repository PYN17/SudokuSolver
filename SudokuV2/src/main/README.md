# 🧠 Sudoku Solver (Web App)

This is a fully functional Sudoku solver web application built with **Java**, **Spring Boot**, and a simple web frontend. It uses a **recursive backtracking algorithm** to solve any valid Sudoku puzzle, and includes options to reset or solve directly in the browser.

---

## 🚀 Features

- Solve any 9x9 Sudoku puzzle using recursive backtracking
- Accepts incomplete boards with blank cells
- Visual update of the board on solve
- Reset button to clear the board
- Responsive and clean front-end layout
- (Upcoming) Visual solving with animated updates
- (Upcoming) Difficulty rating per puzzle

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Thymeleaf**
- **HTML/CSS**
- **JUnit** for testing
- **Docker** (containerized for easy deployment)

---

## 🧩 How It Works

- The frontend accepts a 9x9 board, with blank cells left empty.
- On submit, nulls are replaced with 0s and passed to the backend.
- The `SudokuSolver` class uses backtracking to fill valid numbers.
- The result is rendered back on the same page.

---

## 🧪 Run Locally

### With Maven
```bash
git clone https://github.com/yourusername/sudoku-solver.git
cd sudoku-solver
./mvnw spring-boot:run
