import java.util.*;
public abstract class SearchAlgorithm {

    public abstract SearchResult solve(Maze maze, Node start, Node goal);

    protected int cost(Node node, Map<Node, Integer> costMap) {
        return costMap.getOrDefault(node, Integer.MAX_VALUE);
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
