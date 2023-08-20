import java.util.*;

/**
 * Represents the Uniform Cost Search algorithm.
 * This algorithm expands the node with the lowest path cost.
 */
public class UniformCostSearch extends SearchAlgorithm {

    /**
     * Solves the maze using the Uniform Cost Search algorithm.
     *
     * @param maze The maze to be solved.
     * @param start The starting node.
     * @param goal The goal node.
     * @return SearchResult containing the path from start to goal and the set of visited nodes.
     */
    @Override
    public SearchResult solve(Maze maze, Node start, Node goal) {
        // Initialize the cost map and the set of visited nodes.
        Map<Node, Integer> costMap = new HashMap<>();
        // Initialize the frontier with a comparator that prioritizes nodes based on their cost.
        Frontier frontier = new Frontier(Comparator.comparing(node -> this.comparator(node, costMap)));
        Set<Node> visitedNodes = new HashSet<>();

        // Set the starting node's cost to 0 and add it to the frontier.
        costMap.put(start, 0);
        frontier.add(start);

        // This map will be used to backtrack from the goal to the start to reconstruct the path.
        Map<Node, Node> path = new HashMap<>();

        // Continue searching as long as there are nodes to explore.
        while (!frontier.isEmpty()) {
            // Get the node with the lowest cost from the frontier.
            Node current = frontier.dequeue();

            // If the current node is the goal, we've found a solution.
            if (current.equals(goal)) {
                visitedNodes.add(current);
                List<Node> finalPath = reconstructPath(path, goal);
                return new SearchResult(finalPath, visitedNodes);
            }

            // Explore the neighbors of the current node.
            for (Node neighbor : maze.getNeighbors(current)) {
                // If the neighbor has already been visited, skip it.
                if (visitedNodes.contains(neighbor)) {
                    continue;
                }

                // Calculate the tentative cost to reach the neighbor through the current node.
                int tentativeCost = costMap.get(current) + 1;

                // If this tentative cost is less than the previously known cost for the neighbor,
                // update the cost and path.
                if (tentativeCost < getCost(neighbor, costMap)) {
                    visitedNodes.add(current);
                    costMap.put(neighbor, tentativeCost);
                    path.put(neighbor, current);
                    frontier.update(neighbor);
                }
            }
        }

        // If we've exhausted all nodes and haven't found the goal, return an empty path.
        return new SearchResult(new ArrayList<>(), visitedNodes);
    }

    /**
     * Comparator function to prioritize nodes based on their cost.
     *
     * @param node The node to be compared.
     * @param costMap The map containing the cost for each node.
     * @return The cost of the node.
     */
    private int comparator(Node node , Map<Node, Integer> costMap){
        return this.getCost(node, costMap);
    }
}
