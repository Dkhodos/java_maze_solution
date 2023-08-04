import java.util.*;


public class GreedySearch extends SearchAlgorithm {
    @Override
    public double getPriority(Node node) {
        // Implement Greedy search priority calculation logic
        return 0;
    }

    @Override
    public SearchAlgorithm.Solution solve(Maze maze, Node start, Node goal) {
        visitedNodes = new HashSet<>();
        frontier = new Frontier(Comparator.comparing(this::getPriority));
        // Implement Greedy search logic
        return null;
    }
}