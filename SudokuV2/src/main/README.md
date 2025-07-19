# 🧠 Sudoku Solver (Web App)

This is a fully functional Sudoku solver web application built with **Java**, **Spring Boot**, and a simple web frontend. It uses a **recursive backtracking algorithm** to solve any valid Sudoku puzzle, and includes options to reset or solve directly in the browser.

---

## 🚀 Features

- Solve any 9x9 Sudoku puzzle using recursive backtracking
- Accepts incomplete boards with blank cells
- Animation update of the board on solve
- Reset button to clear the board
- Responsive and clean front-end layout
- API call to Sudoku-Gen to populate board for easy usage
- Stats dashboard
  - Difficulty rating per puzzle
  - Time to solve
  - Algorithmic steps required to solve
  
- (Upcoming): Unit test and further error handling
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
- Alternatively, user can generate their own problem with difficulty selector.
- User input is verified on the frontend.
- On submit, nulls are replaced with 0s and passed to the backend.
- User input is further validate for correct starting problem on the backend.
- The `Solver` class uses backtracking to fill valid numbers.
- The result is rendered back on the same page.

---

## 🧪 Run Locally

### With Maven
```bash
git clone https://github.com/yourusername/sudoku-solver.git
cd sudoku-solver
./mvnw spring-boot:run
