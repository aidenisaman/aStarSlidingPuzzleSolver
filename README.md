# Sliding Puzzle Solver

This is a Java implementation of a solver for the n×n sliding puzzle (also known as the n-puzzle or sliding tiles puzzle). The program uses the A* search algorithm with Manhattan distance heuristic to find the optimal solution path.

## Overview

The sliding puzzle consists of n² - 1 numbered tiles and one blank space arranged in an n×n grid. The goal is to rearrange the tiles from their initial configuration to reach the goal configuration where the tiles are in numerical order with the blank space at the end.

### Features

- Supports any n×n puzzle size (recommended for 2×2 up to 4×4 for reasonable solving times)
- Determines if a given puzzle configuration is solvable
- Finds the optimal (minimum moves) solution path
- Provides performance metrics (solution time and number of moves)
- Includes a random puzzle generator

## Classes

### Board.java
- Represents the puzzle board state
- Implements board operations and metrics:
  - Manhattan distance calculation
  - Hamming distance calculation
  - Neighbor state generation
  - Board comparison and equality checking

### Solver.java
- Implements the A* search algorithm
- Uses a priority queue to efficiently find the optimal solution
- Determines puzzle solvability using the twin board technique
- Generates the solution path

### Main.java
- Contains a sample implementation using a predefined 4×4 puzzle
- Demonstrates basic usage of the solver

### PuzzleTest.java
- Provides functionality to generate random solvable puzzles
- Allows user input for puzzle size
- Includes timing and performance measurements

## Usage

### Running the Sample Puzzle
```java
public static void main(String[] args) {
    int[][] initialTiles = {
        {5, 1, 2, 3},
        {0, 6, 7, 4},
        {9, 10, 11, 8},
        {13, 14, 15, 12}
    };
    
    Board initial = new Board(initialTiles);
    Solver solver = new Solver(initial);
    
    if (solver.isSolvable()) {
        System.out.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution()) {
            System.out.println(board);
        }
    } else {
        System.out.println("The puzzle is not solvable.");
    }
}
```

### Generating and Solving Random Puzzles
Run `PuzzleTest.java` and follow the prompts to:
1. Enter the desired puzzle size (n)
2. Get a randomly generated solvable puzzle
3. View the solution path and performance metrics

## Algorithm Details

The solver uses the A* search algorithm with the following components:

- **Priority Function**: f(n) = g(n) + h(n)
  - g(n): Number of moves made so far
  - h(n): Manhattan distance heuristic
- **Twin Board Technique**: Used to determine puzzle solvability
- **Manhattan Distance**: Sum of the horizontal and vertical distances of each tile from its goal position

## Performance Considerations

- Time complexity is exponential with respect to the board size
- Memory usage increases significantly with larger puzzles
- Recommended puzzle sizes:
  - 2×2 to 3×3: Solves instantly
  - 4×4: Typically solves within seconds
  - 5×5 and larger: May take considerable time and memory

## Requirements

- Java 8 or higher
- Standard Java libraries (no external dependencies)

## Building and Running

1. Compile all Java files:
```bash
javac *.java
```

2. Run either the sample puzzle or random puzzle generator:
```bash
java Main     # For sample puzzle
java PuzzleTest   # For random puzzle generator
```
