import java.util.*;
/**
 * The SearchAlgorithm class provides an abstract representation of a search algorithm.
 * It provides common utility methods that can be used by specific search algorithm implementations.
 * This is a stateless class, meaning it doesn't maintain any state information between method calls.
 */
public abstract class SearchAlgorithm {
    public abstract String getName();

    /**
     * Compares two nodes based on their cost and heuristic values.
     *
     * @param node The node to be compared.
     * @param goal The goal node.
     * @param costMap The map containing the costs associated with each node.
     * @return An integer representing the comparison result.
     */
    protected abstract double comparator(Node node, Node goal, Map<Node, Integer> costMap);

    /**
     * Updates the cost, path, and frontier based on the current node and its neighbor.
     *
     * @param neighbor The neighboring node.
     * @param current The current node.
     * @param costMap The map containing the costs associated with each node.
     * @param path The path being constructed.
     */
    protected abstract boolean update(Node neighbor,Node current, Map<Node, Integer> costMap, MazePath path);

    /**
     * Solves the maze using the implemented search algorithm.
     *
     * @param maze The maze to be solved.
     * @param start The starting node.
     * @param goal The goal node.
     * @return The result of the search, containing the path and visited nodes.
     */
    public SearchResult solve(Maze maze, Node start, Node goal) {
        // Initialize the cost map and the set of visited nodes.
        Map<Node, Integer> costMap = new HashMap<>();
        Set<Node> visitedNodes = new HashSet<>();

        // Initialize the frontier with a comparator that prioritizes nodes based on A* criteria.
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble((Node n) -> this.comparator(n ,goal, costMap)));

        // This map will be used to backtrack from the goal to the start to reconstruct the path.
        MazePath path = new MazePath(goal);

        // Set the starting node's cost to 0 and add it to the frontier.
        costMap.put(start, 0);
        frontier.add(start);

        // Continue searching as long as there are nodes to explore.
        while (!frontier.isEmpty()) {
            // Get the node with the highest priority (lowest cost + heuristic) from the frontier.
            Node current = frontier.poll();

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

                // execute update of  costMap, path and frontier
                // update will be according to the specific algorithm logic
                if(this.update(neighbor, current, costMap, path)){
                    path.add(neighbor, current);
                    frontier.add(neighbor);
                    visitedNodes.add(neighbor);
                }
            }
        }

        // If we've exhausted all nodes and haven't found the goal, return an empty path.
        return new SearchResult(new ArrayList<>(), visitedNodes, getName());
    }

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
    protected double getHeuristicDistance(Node node, Node goal){
        return (Math.abs(node.x() - goal.x()) + Math.abs(node.y() - goal.y()));
    }
}
