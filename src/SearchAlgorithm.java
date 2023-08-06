import java.util.*;
public abstract class SearchAlgorithm {
    protected Frontier frontier;
    protected Set<Node> visitedNodes = new HashSet<>();
    protected HashMap<Node, Integer> costMap = new HashMap<>();

    protected double cost(Node node) {
        return this.costMap.getOrDefault(node, Integer.MAX_VALUE);
    }

    public abstract List<Node> solve(Maze maze, Node start, Node goal);

    public void printPath(List<Node> path) {
        path.forEach(node -> System.out.print(node + " "));
        System.out.println();
    }

    public Set<Node> getVisitedNodes() {
        return visitedNodes;
    }

    protected List<Node> reconstructPath(Map<Node, Node> path, Node goal) {
        List<Node> result = new ArrayList<>();
        Node node = goal;
        while (node != null) {
            result.add(node);
            node = path.get(node);
        }
        Collections.reverse(result);
        return result;
    }
}