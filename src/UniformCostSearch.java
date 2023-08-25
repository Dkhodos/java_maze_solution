import java.util.*;

/**
 * Represents the Uniform Cost Search algorithm.
 * This algorithm expands the node with the lowest path cost.
 */
public class UniformCostSearch extends SearchAlgorithm {
    @Override
    public String getName() {
        return "UCS";
    }

    /**
     * Updates the cost, path, and frontier for the Uniform Cost Search algorithm.
     * The cost to reach the neighbor through the current node is calculated.
     * If this tentative cost is less than the previously known cost for the neighbor,
     * the cost and path are updated.
     *
     * @param neighbor The neighboring node.
     * @param current The current node.
     * @param costMap The map containing the costs associated with each node.
     * @param path The path being constructed.
     * @param frontier The nodes yet to be explored.
     */
    @Override
    public void update(Node neighbor,Node current, Map<Node, Integer> costMap, MazePath path, Frontier frontier) {
        // Calculate the tentative cost to reach the neighbor through the current node.
        int tentativeCost = costMap.get(current) + 1;

        // If this tentative cost is less than the previously known cost for the neighbor,
        // update the cost and path.
        if (tentativeCost < getCost(neighbor, costMap)) {
            costMap.put(neighbor, tentativeCost);
            path.add(neighbor, current);
            frontier.update(neighbor);
        }
    }

    /**
     * Compares two nodes based on their cost for the Uniform Cost Search algorithm.
     * Only the cost to reach the node is considered, without any heuristic.
     *
     * @param node The node to be compared.
     * @param goal The goal node (not used in this comparison, but present due to method signature).
     * @param costMap The map containing the costs associated with each node.
     * @return An integer representing the cost associated with the given node.
     */
    protected int comparator(Node node, Node goal, Map<Node, Integer> costMap){
        return this.getCost(node, costMap);
    }
}
