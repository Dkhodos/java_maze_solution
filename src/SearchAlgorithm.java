import java.util.*;
public abstract class SearchAlgorithm {

    public abstract SearchResult solve(Maze maze, Node start, Node goal);

    protected int getCost(Node node, Map<Node, Integer> costMap) {
        return costMap.getOrDefault(node, Integer.MAX_VALUE);
    }

    protected int getHeuristicDistance(Node node, Node goal){
        return (Math.abs(node.x() - goal.x()) + Math.abs(node.y() - goal.y()));
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
