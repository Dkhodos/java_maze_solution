import java.util.*;

/**
 * The AStarSearch class implements the A* search algorithm.
 * A* is an informed search algorithm, which uses a combination of the cost to reach a node and a heuristic to estimate the cost from that node to the goal.
 * This combination helps A* to find the shortest path in an efficient manner.
 */
public class AStarSearch extends SearchAlgorithm {

    /**
     * Solves the maze using the A* search algorithm.
     *
     * @param maze  The maze to be solved.
     * @param start The starting node of the maze.
     * @param goal  The goal or destination node of the maze.
     * @return SearchResult containing the path from start to goal and the set of visited nodes.
     */
    @Override
    public SearchResult solve(Maze maze, Node start, Node goal) {
        // Initialize the cost map and the set of visited nodes.
        Map<Node, Integer> costMap = new HashMap<>();
        Set<Node> visitedNodes = new HashSet<>();

        // Initialize the frontier with a comparator that prioritizes nodes based on A* criteria.
        Frontier frontier = new Frontier(Comparator.comparing((Node n) -> this.comparator(n ,goal, costMap)));

        // Set the starting node's cost to 0 and add it to the frontier.
        costMap.put(start, 0);
        frontier.add(start);

        // This map will be used to backtrack from the goal to the start to reconstruct the path.
        Map<Node, Node> path = new HashMap<>();

        // Continue searching as long as there are nodes to explore.
        while (!frontier.isEmpty()) {
            // Get the node with the highest priority (lowest cost + heuristic) from the frontier.
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
     * Computes the value used for prioritizing nodes in the frontier.
     * It's a combination of the heuristic distance from the node to the goal and the actual cost to reach the node.
     *
     * @param node    The node for which the value is being computed.
     * @param goal    The goal node.
     * @param costMap A map containing the cost to reach each node.
     * @return The computed value for the node.
     */
    private int comparator(Node node , Node goal, Map<Node, Integer> costMap){
        return this.getHeuristicDistance(node, goal) + this.getCost(node, costMap);
    }
}
