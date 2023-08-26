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

    public SearchResult solve(Maze maze, Node start, Node goal) {
        // Initialize the cost map and the set of visited nodes.
        Map<Node, Integer> costMap = new HashMap<>();
        Set<Node> visitedNodes = new HashSet<>();

        // Initialize the frontier with a comparator that prioritizes nodes based on A* criteria.
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble((Node n) -> this.comparator(n, costMap)));

        // This map will be used to backtrack from the goal to the start to reconstruct the path.
        MazePath path = new MazePath(goal);

        // Set the starting node's cost to 0 and add it to the frontier.
        costMap.put(start, 0);
        frontier.add(start);

        // Continue searching as long as there are nodes to explore.
        while (!frontier.isEmpty()) {
            // Get the node with the highest priority (lowest cost) from the frontier.
            Node current = frontier.poll();

            // check if current node was already visited
            if(visitedNodes.contains(current)){
                continue;
            }

            // update visited nodes to avoid repetition
            visitedNodes.add(current);

            // If the current node is the goal, we've found a solution.
            if (current.equals(goal)) {
                List<Node> finalPath = path.getReconstructPath();
                return new SearchResult(finalPath, visitedNodes, getName());
            }

            // Explore the neighbors of the current node.
            for (Node neighbor : maze.getNeighbors(current)) {
                // If the neighbor has already been visited, skip it.
                if (visitedNodes.contains(neighbor)) {
                    continue;
                }

                // Calculate the tentative cost to reach the neighbor through the current node.
                int tentativeCost = getCost(current, costMap) + 1;

                // If this tentative cost is less than the previously known cost for the neighbor,
                // update the cost and path.
                if (tentativeCost < getCost(neighbor, costMap)) {
                    costMap.put(neighbor, tentativeCost);
                    path.add(neighbor, current);
                    frontier.add(neighbor);
                }
            }
        }

        // If we've exhausted all nodes and haven't found the goal, return an empty path.
        return new SearchResult(new ArrayList<>(), visitedNodes, getName());
    }

    /**
     * Compares two nodes based on their cost for the Uniform Cost Search algorithm.
     * Only the cost to reach the node is considered, without any heuristic.
     *
     * @param node The node to be compared.
     * @param costMap The map containing the costs associated with each node.
     * @return An integer representing the cost associated with the given node.
     */
    protected double comparator(Node node, Map<Node, Integer> costMap){
        return this.getCost(node, costMap);
    }
}
