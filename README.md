# N×N Sliding Puzzle Solver

This project provides two different implementations of a sliding puzzle solver, each with its own strengths and use cases. Both implementations use variations of the A* search algorithm to find optimal solutions for n×n sliding puzzles.

## Implementations

### 1. Twin-Board Implementation
Located in `Board.java` and `Solver.java`, this implementation uses a twin board technique for efficient solvability detection and provides comprehensive puzzle management features.

### 2. Pure A* Implementation
Located in `AStarSolver.java` and `PuzzleState.java`, this implementation focuses on memory efficiency and includes segment-based solving capabilities.

## Core Classes

### Twin-Board Implementation
- **Board.java**
  - Board state representation
  - Manhattan and Hamming distance calculations
  - Board operations and comparisons
  - Neighbor state generation

- **Solver.java**
  - A* search with twin board technique
  - Solution path management
  - Solvability detection

### Pure A* Implementation
- **PuzzleState.java**
  - State management and board operations
  - Manhattan distance calculation
  - Segment-based solving capability
  - State comparison utilities

- **AStarSolver.java**
  - Pure A* search implementation
  - Efficient state exploration
  - Solution path reconstruction

### Shared/Utility Classes
- **Main.java**
  - Example usage of both implementations
  - Performance timing
  - Sample puzzle configurations

- **PuzzleTest.java**
  - Random puzzle generation
  - User interaction for puzzle size
  - Testing utilities

## Features

### Common Features
- Support for any n×n puzzle size
- Optimal solution path finding
- Performance metrics
- Solution visualization

### Twin-Board Implementation Features
- Guaranteed solvability detection
- Multiple heuristic options (Manhattan and Hamming)
- Random puzzle generation
- Comprehensive testing framework

### Pure A* Implementation Features
- Memory-efficient state management
- Segment-based solving capability
- Streamlined state exploration
- Efficient duplicate detection

## Usage Examples

### Using Twin-Board Implementation
```java
// Create initial board
int[][] initialTiles = {
    {5, 1, 2, 3},
    {0, 6, 7, 4},
    {9, 10, 11, 8},
    {13, 14, 15, 12}
};

Board initial = new Board(initialTiles);
Solver solver = new Solver(initial);

if (solver.isSolvable()) {
    System.out.println("Minimum moves = " + solver.moves());
    for (Board board : solver.solution()) {
        System.out.println(board);
    }
}
```

### Using Pure A* Implementation
```java
int[][] initialBoard = {
    {5, 1, 2, 3},
    {0, 6, 7, 4},
    {9, 10, 11, 8},
    {13, 14, 15, 12}
};

PuzzleState startState = new PuzzleState(initialBoard, null, 0);
AStarSolver.solve(startState);
```

## Choosing an Implementation

### Use Twin-Board Implementation When:
- Solvability detection is crucial
- Multiple heuristic options are needed
- Random puzzle generation is required
- Comprehensive testing is needed

### Use Pure A* Implementation When:
- Memory efficiency is priority
- Segment-based solving is beneficial
- Simpler state management is preferred
- Quick solution finding is the main goal

## Performance Considerations

### Twin-Board Implementation
- Better for detecting unsolvable puzzles
- More memory usage due to twin board
- Additional computation for multiple heuristics

### Pure A* Implementation
- More efficient memory usage
- Faster for solvable puzzles
- Better for larger puzzle sizes

## Requirements

- Java 8 or higher
- Standard Java libraries (no external dependencies)

## Building and Running

1. Compile all Java files:
```bash
javac *.java
```

2. Run either implementation:
```bash
# For Twin-Board implementation with sample puzzle
java Main

# For random puzzle generation and testing
java PuzzleTest
```

## Implementation Details

### Twin-Board Implementation
- Uses twin board technique for solvability
- Maintains two parallel priority queues
- Implements both Manhattan and Hamming distances
- Immutable board states

### Pure A* Implementation
- Single priority queue with HashSet
- Segment-based solving capability
- Mutable state management
- Optimized state comparison

## Future Improvements for Consideration 

- Graphical user interface
- Parallel solving capabilities
- Advanced heuristic functions
- Pattern database support
- Solution animation
- Interactive puzzle input
- Hybrid solving approaches

## Performance Comparison

Both implementations have been tested with various puzzle sizes:
- 3×3 puzzles: Both solve instantly
- 4×4 puzzles: Typically solve within seconds
- 5×5 puzzles: Solving time varies significantly
- Larger puzzles: Consider memory and time constraints

Choose the implementation that best fits your specific needs based on the factors described above.
