import java.util.ArrayList;
import java.util.List;

public class Board {
	private int[][] tiles;
	private int n;

	// construct a board from an n-by-n array of tiles
	// where tiles[row][col] = tile at (row, col)
	public Board(int[][] tiles) {
		n = tiles.length;
		this.tiles = new int[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(tiles[i], 0, this.tiles[i], 0, n);
		}
	}

	// string representation of the board
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(n).append("\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", tiles[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	// board dimension n
	public int dimension() {
		return n;
	}

	// number of tiles out of place
	public int hamming() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (tiles[i][j] != 0 && tiles[i][j] != i * n + j + 1) {
					count++;
				}
			}
		}
		return count;
	}

	// sum of Manhattan distances between tiles and goal
	public int manhattan() {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int tile = tiles[i][j];
				if (tile != 0) {
					int targetX = (tile - 1) / n;
					int targetY = (tile - 1) % n;
					sum += Math.abs(i - targetX) + Math.abs(j - targetY);
				}
			}
		}
		return sum;
	}

	// is this board the goal board?
	public boolean isGoal() {
		return hamming() == 0;
	}

	// a board that is obtained by exchanging any pair of tiles
	public Board twin() {
		int[][] twinTiles = new int[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(tiles[i], 0, twinTiles[i], 0, n);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (twinTiles[i][j] != 0 && twinTiles[i][j + 1] != 0) {
					int temp = twinTiles[i][j];
					twinTiles[i][j] = twinTiles[i][j + 1];
					twinTiles[i][j + 1] = temp;
					return new Board(twinTiles);
				}
			}
		}
		return new Board(twinTiles);
	}

	// does this board equal y?
	public boolean equals(Object y) {
		if (y == this)
			return true;
		if (y == null || y.getClass() != this.getClass() || ((Board) y).tiles.length != n)
			return false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.tiles[i][j] != ((Board) y).tiles[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		List<Board> neighbors = new ArrayList<>();
		int blankRow = -1, blankCol = -1;
		// Find the blank tile
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (tiles[i][j] == 0) {
					blankRow = i;
					blankCol = j;
					break;
				}
			}
			if (blankRow != -1)
				break;
		}
		// Move the blank tile in all four directions if possible
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		for (int[] dir : dirs) {
			int newRow = blankRow + dir[0], newCol = blankCol + dir[1];
			if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
				int[][] newTiles = copyTiles();
				// Swap blank tile with its neighbor
				newTiles[blankRow][blankCol] = newTiles[newRow][newCol];
				newTiles[newRow][newCol] = 0;
				neighbors.add(new Board(newTiles));
			}
		}
		return neighbors;
	}

	private int[][] copyTiles() {
		int[][] newTiles = new int[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(tiles[i], 0, newTiles[i], 0, n);
		}
		return newTiles;
	}
}
