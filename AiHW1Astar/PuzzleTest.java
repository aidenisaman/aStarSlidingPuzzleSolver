import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class PuzzleTest {

    // Method to generate a random solvable board
    private static Board generateRandomSolvableBoard(int n) {
        Random random = new Random();
        int[][] tiles = new int[n][n];
        // Start with a solved puzzle
        for (int i = 0, tile = 1; i < n; i++) {
            for (int j = 0; j < n; j++, tile++) {
                tiles[i][j] = tile % (n * n);
            }
        }

        Board board = new Board(tiles);
        // Make a series of random moves to shuffle the board
        for (int i = 0; i < 150; i++) { // 100-500 is an arbitrary number of shuffling moves
            Iterable<Board> neighbors = board.neighbors();
            int idx = random.nextInt(((List<Board>) neighbors).size());
            Board nextBoard = ((List<Board>) neighbors).get(idx);
            board = nextBoard;
        }
        return board;
    }

    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	System.out.println("Please enter the size of the puzzle you would like to generate and solve:");
    	int x = in.nextInt();
    	Board initial = generateRandomSolvableBoard(x); // Generate a random NxN board
    	long startTime = System.nanoTime();//timer
        Solver solver = new Solver(initial);
        long endTime = System.nanoTime();
        
        if (solver.isSolvable()) {
            System.out.println("The puzzle is solvable.");
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                System.out.println(board);
            }
            //long endTime = System.nanoTime();

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
