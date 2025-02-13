public class Main {
	
	
	
    public static void main(String[] args) {
        // Compatible input for the other solver
        int[][] initialBoard = {
            {5, 1, 2, 3},
            {0, 6, 7, 4},
            {9, 10, 11, 8},
            {13, 14, 15, 12}
        };

        // Create the initial puzzle state
        PuzzleState startState = new PuzzleState(initialBoard, null, 0);

        // Print the initial puzzle configuration
        System.out.println("Initial Puzzle State:");
        startState.printBoard();
        long startTime = System.nanoTime();//timer
        // Solve the puzzle using A* algorithm with a divide and conquer strat in mind
        System.out.println("Solving...");
        AStarSolver.solve(startState);
        long endTime = System.nanoTime();

        //calc time
        long duration = endTime - startTime;

        // Convert the duration to seconds
        double durationInSeconds = duration / 1_000_000_000.0; // Use double for fractional seconds
        System.out.println("Puzzle solved  in " + durationInSeconds + " seconds");
    }
}
