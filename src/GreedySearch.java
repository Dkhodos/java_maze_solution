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
     * Solves the maze using the Greedy Search algorithm.
     * <p>
     * Greedy Search is an informed search algorithm that selects the path which appears to be the closest
     * to the goal, as estimated by a heuristic. Unlike A* search, Greedy Search does not consider the cost
     * to reach a node, only the heuristic estimate to the goal. This means that while Greedy Search can be
     * faster, it does not guarantee the shortest path.
     * <p>
     * The algorithm starts with the start node and explores its neighbors. For each neighbor, it calculates
     * a heuristic estimate to the goal. The node with the lowest heuristic value is then chosen for further
     * exploration. This process continues until the goal node is found or there are no more nodes left to explore.
     * <p>
     * The algorithm uses a priority queue to keep track of nodes to explore, where the priority is determined
     * by the heuristic estimate to the goal. It also maintains a set of visited nodes to ensure that nodes
     * are not revisited.
     *
     * @param maze The maze to be solved. This provides information about the layout of the maze, including
     *             which cells are walkable and which are obstacles.
     * @param start The starting node. This is where the search begins.
     * @param goal The goal node. This is the destination the search is trying to reach.
     *
     * @return SearchResult object containing the path from the start node to the goal node (if found),
     *         and the set of all nodes that were visited during the search. If no path is found, the path
     *         in the SearchResult will be empty.
     */
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
