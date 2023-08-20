import java.util.*;

/**
 * Represents the Greedy Search algorithm.
 * This algorithm expands the node that is estimated to be closest to the goal.
 */
public class GreedySearch extends SearchAlgorithm {

    /**
     * Solves the maze using the Greedy Search algorithm.
     *
     * @param maze The maze to be solved.
     * @param start The starting node.
     * @param goal The goal node.
     * @return SearchResult containing the path from start to goal and the set of visited nodes.
     */
    @Override
    public SearchResult solve(Maze maze, Node start, Node goal) {
        // Initialize the set of visited nodes.
        Set<Node> visitedNodes = new HashSet<>();
        // Initialize the frontier with a comparator that prioritizes nodes based on their heuristic distance to the goal.
        Frontier frontier = new Frontier(Comparator.comparing((Node n) -> this.comparator(n, goal)));

        // Add the starting node to the frontier.
        frontier.add(start);

        // This map will be used to backtrack from the goal to the start to reconstruct the path.
        Map<Node, Node> path = new HashMap<>();

        // Continue searching as long as there are nodes to explore.
        while (!frontier.isEmpty()) {
            // Get the node with the lowest heuristic distance to the goal from the frontier.
            Node current = frontier.poll();

            // If the current node is the goal, we've found a solution.
            if (current.equals(goal)) {
                visitedNodes.add(current);
                List<Node> finalPath = reconstructPath(path, goal);
                return new SearchResult(finalPath, visitedNodes);
            }

            // Explore the neighbors of the current node.
            for (Node neighbor : maze.getNeighbors(current)) {
                // If the neighbor has already been visited, skip it.
                if(visitedNodes.contains(neighbor)){
                    continue;
                }

                // Update the path to include the current node as the predecessor of the neighbor.
                path.put(neighbor, current);
                visitedNodes.add(current);
                frontier.update(neighbor);
            }
        }

        // If we've exhausted all nodes and haven't found the goal, return an empty path.
        return new SearchResult(new ArrayList<>(), visitedNodes);
    }

    /**
     * Comparator function to prioritize nodes based on their heuristic distance to the goal.
     *
     * @param node The node to be compared.
     * @param goal The goal node.
     * @return The heuristic distance from the node to the goal.
     */
    private int comparator(Node node , Node goal){
        return this.getHeuristicDistance(node, goal);
    }
}
