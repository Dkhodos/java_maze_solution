import java.util.*;


public class UniformCostSearch extends SearchAlgorithm {
    @Override
    public double getPriority(Node node) {
        // Implement UCS priority calculation logic
        return 0;
    }

    @Override
    public List<Node> solve(Maze maze, Node start, Node goal) {
        visitedNodes = new HashSet<>();
        frontier = new Frontier(Comparator.comparing(this::getPriority));
        // Implement UCS search logic
        return null;
    }
}