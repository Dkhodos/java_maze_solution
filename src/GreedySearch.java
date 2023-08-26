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

    public SearchResult solve(Maze maze, Node start, Node goal) {
        // Initialize the cost map and the set of visited nodes.
        Set<Node> visitedNodes = new HashSet<>();

        // Initialize the frontier with a comparator that prioritizes nodes based on A* criteria.
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble((Node n) -> this.comparator(n, goal)));

        // This map will be used to backtrack from the goal to the start to reconstruct the path.
        MazePath path = new MazePath(goal);

        // Set the starting node's cost to 0 and add it to the frontier.
        frontier.add(start);

        // Continue searching as long as there are nodes to explore.
        while (!frontier.isEmpty()) {
            // Get the node with the highest priority (heuristic) from the frontier.
            Node current = frontier.poll();

            // check if current node was already visited
            if (visitedNodes.contains(current)) {
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
                if (visitedNodes.contains(neighbor) || frontier.contains(neighbor)) {
                    continue;
                }

                path.add(neighbor, current);
                frontier.add(neighbor);
            }
        }

        return new SearchResult(new ArrayList<>(), visitedNodes, getName());
    }

    /**
     * Compares two nodes based on their heuristic distance to the goal for the Greedy Search algorithm.
     * Only the heuristic distance to the goal is considered, without any actual cost to reach the node.
     *
     * @param node The node from which the heuristic distance is to be calculated.
     * @param goal The goal node to which the heuristic distance is to be calculated.
     * @return An integer representing the heuristic distance between the given node and the goal node.
     */
    protected double comparator(Node node, Node goal){
        return this.getHeuristicDistance(node, goal);
    }
}
