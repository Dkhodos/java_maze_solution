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
            Node current = frontier.remove();

            // Mark the node as visited
            visitedNodes.add(current);

            // If the goal is reached, reconstruct the path and return it
            if (current.equals(goal)) {
                return reconstructPath(path, goal);
            }

            // Check all the non-obstacle neighbors of the current node
            for (Node neighbor : maze.getNeighbors(current)) {
                if (visitedNodes.contains(neighbor) || neighbor.isObstacle()) {
                    continue;
                }
                // Calculate tentative cost from start to the neighbor through current
                // Assuming the cost for each step is 1
                int tentativeCost = this.costMap.get(current) + 1;
                if (tentativeCost < this.costMap.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    this.costMap.put(neighbor, tentativeCost);
                    path.put(neighbor, current);
                    frontier.add(neighbor);
                }
            }
        }
        // If there is no solution, return an empty path
        return new ArrayList<>();
    }

    private List<Node> reconstructPath(Map<Node, Node> path, Node goal) {
        List<Node> result = new ArrayList<>();
        Node node = goal;
        while (node != null) {
            result.add(node);
            node = path.get(node);
        }
        Collections.reverse(result);
        return result;
    }
}
