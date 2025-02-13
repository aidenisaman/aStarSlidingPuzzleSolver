public class Main {
    public static void main(String[] args) {
        // Compatible input for the other solver
    	int[][] initialTiles = {
    			{5, 1, 2, 3},
    			{0, 6, 7, 4},
    			{9, 10, 11, 8},
                {13, 14, 15, 12} // A solvable 4x4 puzzle
    	    };

    	// Create the initial board
        Board initial = new Board(initialTiles);
        long startTime = System.nanoTime();//timer
        // Solve the puzzle
        Solver solver = new Solver(initial);
        
       
        // Check if the puzzle is solvable and print the solution steps if it is
        if (solver.isSolvable()) {
            System.out.println("Minimum number of moves = " + solver.moves());
            System.out.println("Solution sequence:");
            for (Board board : solver.solution()) {
                System.out.println(board);
            }
            
            long endTime = System.nanoTime();//timer
            //calc time
            long duration = endTime - startTime;

            // Convert the duration to seconds
            double durationInSeconds = duration / 1_000_000_000.0; // Use double for fractional seconds
            System.out.println("Puzzle solved  in " + durationInSeconds + " seconds");
        } else {
            System.out.println("The puzzle is not solvable.");
        }
    	}
}
