import java.util.*;

/**
 * The AStarSearch class implements the A* search algorithm.
 * A* is an informed search algorithm, which uses a combination of the cost to reach a node and a heuristic to estimate the cost from that node to the goal.
 * This combination helps A* to find the shortest path in an efficient manner.
 */
public class AStarSearch extends SearchAlgorithm {

    /**
     * Updates the cost, path, and frontier for the A* algorithm.
     * The cost to reach the neighbor through the current node is calculated.
     * If this tentative cost is less than the previously known cost for the neighbor,
     * the cost, path, and frontier are updated.
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
     * Compares two nodes based on their cost and heuristic values for the A* algorithm.
     * The comparison value is the sum of the cost to reach the node and the heuristic distance from the node to the goal.
     *
     * @param node The node to be compared.
     * @param goal The goal node.
     * @param costMap The map containing the costs associated with each node.
     * @return An integer representing the sum of the cost to reach the node and the heuristic distance to the goal.
     */
    protected int comparator(Node node , Node goal, Map<Node, Integer> costMap){
        return this.getHeuristicDistance(node, goal) + this.getCost(node, costMap);
    }
}
