import java.util.*;
/**
 * The SearchAlgorithm class provides an abstract representation of a search algorithm.
 * It provides common utility methods that can be used by specific search algorithm implementations.
 * This is a stateless class, meaning it doesn't maintain any state information between method calls.
 */
public abstract class SearchAlgorithm {

    /**
     * Solves the given maze using a specific search algorithm to find a path from the start node to the goal node.
     *
     * @param maze The maze to be solved.
     * @param start The starting node.
     * @param goal The goal node.
     * @return A SearchResult object containing the path found and the nodes visited during the search.
     */
    public abstract SearchResult solve(Maze maze, Node start, Node goal);

    /**
     * Retrieves the cost associated with a given node from the cost map.
     * If the node is not present in the map, it returns the maximum integer value.
     *
     * @param node The node whose cost is to be retrieved.
     * @param costMap The map containing the costs associated with each node.
     * @return The cost associated with the given node.
     */
    protected int getCost(Node node, Map<Node, Integer> costMap) {
        return costMap.getOrDefault(node, Integer.MAX_VALUE);
    }

    /**
     * Calculates the heuristic distance between the given node and the goal node.
     * The heuristic used is the Manhattan distance.
     *
     * @param node The node from which the distance is to be calculated.
     * @param goal The goal node to which the distance is to be calculated.
     * @return The heuristic distance between the given node and the goal node.
     */
    protected int getHeuristicDistance(Node node, Node goal){
        return (Math.abs(node.x() - goal.x()) + Math.abs(node.y() - goal.y()));
    }

    /**
     * Reconstructs the path from the start node to the goal node using the given path map.
     * The path map contains each node's parent in the path.
     *
     * @param path The map containing each node and its parent in the path.
     * @param goal The goal node.
     * @return A list of nodes representing the path from the start node to the goal node.
     */
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
