import java.util.*;

public class AStarSolver {

	public static void solve(PuzzleState startState) {
		// Priority queue with comparator based on f(n) = g(n) + h(n)
		PriorityQueue<PuzzleState> openSet = new PriorityQueue<>(
				Comparator.comparingInt(s -> s.g + s.calculateHeuristic()));
		Set<PuzzleState> explored = new HashSet<>();

		openSet.add(startState);

		while (!openSet.isEmpty()) {
			PuzzleState currentState = openSet.poll();

			// If the current state is the goal state, print the solution path
			if (currentState.isGoal()) {
				printSolutionPath(currentState);
				return;
			}

			explored.add(currentState);

			// Generate and explore all neighbor states
			for (PuzzleState neighbor : currentState.generateNeighbors()) {
				if (!explored.contains(neighbor) && !containsState(openSet, neighbor)) {
					openSet.add(neighbor);
				} else if (containsState(openSet, neighbor)) {
					updateStateInOpenSet(openSet, neighbor);
				}
			}
		}

		System.out.println("No solution found.");
	}

	private static boolean containsState(PriorityQueue<PuzzleState> openSet, PuzzleState state) {
		return openSet.stream().anyMatch(s -> Arrays.deepEquals(s.board, state.board));
	}

	private static void updateStateInOpenSet(PriorityQueue<PuzzleState> openSet, PuzzleState newState) {
		for (PuzzleState state : openSet) {
			if (Arrays.deepEquals(state.board, newState.board)
					&& state.g + state.calculateHeuristic() > newState.g + newState.calculateHeuristic()) {
				openSet.remove(state);
				openSet.add(newState);
				break;
			}
		}
	}

	// Print the solution path from the goal state to the initial state
	private static void printSolutionPath(PuzzleState goalState) {
		LinkedList<PuzzleState> path = new LinkedList<>();
		PuzzleState current = goalState;
		while (current != null) {
			path.addFirst(current);
			current = current.parent;
		}

		System.out.println("Solution found in " + (path.size() - 1) + " moves:");
		for (PuzzleState state : path) {
			state.printBoard();
			System.out.println();
		}
	}
}
