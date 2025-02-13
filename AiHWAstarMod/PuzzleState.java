import java.util.*;

public class PuzzleState {
	int[][] board;
    PuzzleState parent;
    int g; // Cost from start to this state
    int zeroRow, zeroCol; // Position of the empty tile

    private static final int[][] GOAL = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        };
    // Constructor
    public PuzzleState(int[][] board, PuzzleState parent, int g) {
        this.board = cloneBoard(board);
        this.parent = parent;
        this.g = g;
        // Find the position of the empty tile (0)
        findZeroPosition();
    }

    // Method to clone the board array
    private int[][] cloneBoard(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    // Find the position of the empty tile
    private void findZeroPosition() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                    return;
                }
            }
        }
    }

    // Generate all possible next states from the current state
    public List<PuzzleState> generateNeighbors() {
        List<PuzzleState> neighbors = new ArrayList<>();
        final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        for (int[] dir : DIRECTIONS) {
            int newRow = zeroRow + dir[0], newCol = zeroCol + dir[1];
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                int[][] newBoard = cloneBoard(board);
                // Swap the empty tile with the adjacent tile
                newBoard[zeroRow][zeroCol] = newBoard[newRow][newCol];
                newBoard[newRow][newCol] = 0;
                neighbors.add(new PuzzleState(newBoard, this, g + 1));
            }
        }
        return neighbors;
    }


    // Calculate the Manhattan distance for a segment
    public int calculateHeuristic() {
    	int h = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int value = board[i][j];
                if (value != 0) {
                    int targetRow = (value - 1) / board.length;
                    int targetCol = (value - 1) % board.length;
                    h += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }
        return h;
    }

    // Check if the current segment of the puzzle is solved
    public boolean isSegmentSolved(int segment, boolean isRow) {
        if (isRow) {
            return isRowSolved(segment);
        } else {
            return isColumnSolved(segment);
        }
    }
    //checks the desired row
    private boolean isRowSolved(int row) {
        for (int col = 0; col < board.length; col++) {
            if (board[row][col] != GOAL[row][col]) {
                return false;
            }
        }
        return true;
    }
    //checks the desired column
    private boolean isColumnSolved(int col) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] != GOAL[row][col]) {
                return false;
            }
        }
        return true;
    }
    
    //prints out the board
    public void printBoard() {
        for (int[] row : board) {
            for (int tile : row) {
                System.out.printf("%2d ", tile);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    //checks to see if the board is solved
    public boolean isGoal() {
        int correctValue = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == board.length - 1 && j == board[i].length - 1) { // Check for the last tile
                    return board[i][j] == 0;
                } else if (board[i][j] != correctValue) {
                    return false;
                }
                correctValue++;
            }
        }
        return true;
    }
    
    //methods for the comparator methods as they are needed for checking the board states more effiecently
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleState that = (PuzzleState) o;
        return Arrays.deepEquals(this.board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.board);
    }


}
