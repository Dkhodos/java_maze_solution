import java.util.*;

public abstract class SearchAlgorithm {
    protected Set<Node> visitedNodes;
    protected Frontier frontier;

    protected HashMap<Node, Integer> costMap = new HashMap<>();

    public abstract double getPriority(Node node);

    public abstract List<Node> solve(Maze maze, Node start, Node goal);

    public void printPath(List<Node> path) {
        path.forEach(node -> System.out.print(node + " "));
        System.out.println();
    }
}