# Sudoku Solver Web App

A full-stack Java web application that solves Sudoku puzzles using recursive backtracking. Built with Spring Boot and MVC architecture, this app allows users to input puzzles via a web interface and displays the computed solution in real time.

Live Demo: https://sudokusolver-dj8h.onrender.com
---

## 🧠 Features

- Accepts user-input 9x9 Sudoku puzzles via GUI
- Validates inputs with basic Sudoku rules
- Solves using recursive backtracking algorithm
- Built with clean Model-View-Controller (MVC) separation
- Handles puzzles with multiple solutions (in progress)
- Displays step-by-step solution progress (in progress)
- Generates Sudoku problems (in progress)

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot (MVC pattern)
- **Frontend:** HTML, CSS, Thymeleaf
- **Logic:** Recursive backtracking
- **Build Tool:** Maven

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven

### Run Locally
```bash
git clone https://github.com/yourusername/sudoku-solver.git
cd sudoku-solver
mvn spring-boot:run
Then navigate to http://localhost:8080 in your browser.
