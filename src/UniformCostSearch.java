import java.util.*;


public class UniformCostSearch extends SearchAlgorithm {

    @Override
    public List<Node> solve(Maze maze, Node start, Node goal) {
        frontier = new Frontier(Comparator.comparing(this::cost));

        // Initialize the start node cost to 0 and add it to the costMap and the frontier
        this.costMap.put(start, 0);
        frontier.add(start);

        // Map to keep track of the path
        Map<Node, Node> path = new HashMap<>();

        while (!frontier.isEmpty()) {
            // Get the node with the least priority (i.e., cost)
            Node current = frontier.dequeue();

            // If the goal is reached, reconstruct the path and return it
            if (current.equals(goal)) {
                return reconstructPath(path, goal);
            }

            // Check all the non-obstacle neighbors of the current node
            for (Node neighbor : maze.getNeighbors(current)) {
                if (visitedNodes.contains(neighbor)) {
                    continue;
                }

                // Calculate tentative cost from start to the neighbor through current
                // Assuming the cost for each step is 1
                int tentativeCost = this.costMap.get(current) + 1;
                if (tentativeCost < this.costMap.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    // Mark the node as visited
                    visitedNodes.add(current);

                    this.costMap.put(neighbor, tentativeCost);
                    path.put(neighbor, current);
                    frontier.remove(neighbor);  // Remove the old instance of the node if present
                    frontier.add(neighbor);  // Add the new instance with updated cost
                }
            }
        }
        // If there is no solution, return an empty path
        return new ArrayList<>();
    }
}
