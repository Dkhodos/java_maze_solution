import java.util.*;

public abstract class SearchAlgorithm {
    protected record Solution(Set<Node> visitedNodes, List<Node> path, HashMap<Node, Integer> costMap){}

    protected Frontier frontier;
    protected Set<Node> visitedNodes;
    protected HashMap<Node, Integer> costMap;

    public abstract double getPriority(Node node);

    public abstract Solution solve(Maze maze, Node start, Node goal);

    public void printPath(List<Node> path) {
        path.forEach(node -> System.out.print(node + " "));
        System.out.println();
    }
}