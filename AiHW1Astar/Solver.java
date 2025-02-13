// Import necessary Java utility classes.
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// The Solver class to find a solution for the puzzle board.
public class Solver {
    private boolean isSolvable; // Flag to check if the puzzle is solvable.
    private List<Board> solutionSteps; // Stores the sequence of steps to solve the puzzle.

    // Inner class representing a search node in the A* search algorithm.
    private class SearchNode implements Comparable<SearchNode> {
        private Board board; // Current board state.
        private int moves; // Number of moves made to reach this board state.
        private SearchNode previous; // Reference to the previous search node.
        private int priority; // Priority of this node, calculated as the sum of the Manhattan distance and the number of moves.

        // Constructor for creating a new search node.
        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.priority = board.manhattan() + moves; // Calculate the priority.
        }

        // CompareTo method to enable sorting of search nodes based on their priority.
        @Override
        public int compareTo(SearchNode that) {
            return Integer.compare(this.priority, that.priority);
        }
    }

    // Constructor for the Solver. Initiates the A* search algorithm.
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Board is null"); // Check for null input.

        PriorityQueue<SearchNode> pq = new PriorityQueue<>(); // Priority queue for the original board.
        pq.add(new SearchNode(initial, 0, null)); // Add the initial board to the queue.
        PriorityQueue<SearchNode> twinPQ = new PriorityQueue<>(); // Priority queue for the twin board.
        twinPQ.add(new SearchNode(initial.twin(), 0, null)); // Add the twin of the initial board.

        // Main loop of the A* search algorithm.
        while (true) {
            SearchNode current = pq.poll(); // Get and remove the node with the lowest priority.
            SearchNode twinCurrent = twinPQ.poll(); // Same for the twin board.

            // Check if the current board is the goal.
            if (current.board.isGoal()) {
                isSolvable = true; // Set solvable flag to true.
                solutionSteps = new ArrayList<>(); // Initialize the solution steps list.
                // Backtrack to form the solution path.
                for (SearchNode node = current; node != null; node = node.previous) {
                    solutionSteps.add(node.board);
                }
                Collections.reverse(solutionSteps); // Reverse the list to get the correct order.
                break; // Break the loop since the solution is found.
            } else if (twinCurrent.board.isGoal()) {
                isSolvable = false; // Puzzle is unsolvable if the twin board reaches the goal.
                solutionSteps = null; // No solution steps.
                break; // Break the loop.
            }

            // Add all valid neighbors of the current board to the queue.
            for (Board neighbor : current.board.neighbors()) {
                if (current.previous == null || !neighbor.equals(current.previous.board)) {
                    pq.add(new SearchNode(neighbor, current.moves + 1, current));
                }
            }

            // Same for the twin board's neighbors.
            for (Board twinNeighbor : twinCurrent.board.neighbors()) {
                if (twinCurrent.previous == null || !twinNeighbor.equals(twinCurrent.previous.board)) {
                    twinPQ.add(new SearchNode(twinNeighbor, twinCurrent.moves + 1, twinCurrent));
                }
            }
        }
    }

    // Method to check if the puzzle is solvable.
    public boolean isSolvable() {
        return isSolvable;
    }

    // Method to get the number of moves in the solution, if solvable.
    public int moves() {
        return isSolvable() ? solutionSteps.size() - 1 : -1;
    }

    // Method to get an iterable of the solution steps, if solvable.
    public Iterable<Board> solution() {
        return isSolvable() ? solutionSteps : null;
    }
}
