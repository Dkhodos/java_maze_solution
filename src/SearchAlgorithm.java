import java.util.*;
public abstract class SearchAlgorithm {
    protected Frontier frontier;
    protected Set<Node> visitedNodes = new HashSet<>();
    protected HashMap<Node, Integer> costMap = new HashMap<>();

    public abstract double cost(Node node);

    public abstract int heuristic(Node node);

    public abstract List<Node> solve(Maze maze, Node start, Node goal);

    public void printPath(List<Node> path) {
        path.forEach(node -> System.out.print(node + " "));
        System.out.println();
    }

    public Set<Node> getVisitedNodes() {
        return visitedNodes;
    }
}