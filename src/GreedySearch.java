import java.util.*;

/**
 * Represents the Greedy Search algorithm.
 * This algorithm expands the node that is estimated to be closest to the goal.
 */
public class GreedySearch extends SearchAlgorithm {
    @Override
    public String getName() {
        return "Greedy";
    }


    /**
     * Updates the path for the Greedy Search algorithm.
     * The path is updated to include the current node as the predecessor of the neighbor.
     * The cost is not considered in the Greedy Search update.
     *
     * @param neighbor The neighboring node.
     * @param current The current node.
     * @param costMap The map containing the costs associated with each node (not used in this update, but present due to method signature).
     * @param path The path being constructed.
     * @param frontier The nodes yet to be explored.
     */
    @Override
    public void update(Node neighbor,Node current, Map<Node, Integer> costMap, MazePath path, Frontier frontier) {
        // Update the path to include the current node as the predecessor of the neighbor.
        path.add(neighbor, current);
        frontier.update(neighbor);
    }

    /**
     * Compares two nodes based on their heuristic distance to the goal for the Greedy Search algorithm.
     * Only the heuristic distance to the goal is considered, without any actual cost to reach the node.
     *
     * @param node The node from which the heuristic distance is to be calculated.
     * @param goal The goal node to which the heuristic distance is to be calculated.
     * @param costMap The map containing the costs associated with each node (not used in this comparison, but present due to method signature).
     * @return An integer representing the heuristic distance between the given node and the goal node.
     */
    @Override
    protected int comparator(Node node, Node goal, Map<Node, Integer> costMap){
        return this.getHeuristicDistance(node, goal);
    }
}
