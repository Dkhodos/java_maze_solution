import java.util.*;


public class GreedySearch extends SearchAlgorithm {
    @Override
    public double cost(Node node) {
        return 0;
    }

    @Override
    public int heuristic(Node node) {
        return 0;
    }

    @Override
    public List<Node> solve(Maze maze, Node start, Node goal) {
        visitedNodes = new HashSet<>();
        frontier = new Frontier(Comparator.comparing(this::cost));
        // Implement Greedy search logic
        return null;
    }
}