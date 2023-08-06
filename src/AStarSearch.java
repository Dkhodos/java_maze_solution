import java.util.*;

public class AStarSearch extends SearchAlgorithm {
    @Override
    public List<Node> solve(Maze maze, Node start, Node goal) {
        // Add the heuristic cost to the comparator
        frontier = new Frontier(Comparator.comparing(node -> this.cost(node) + node.getHeuristicDistance()));

        this.costMap.put(start, 0);
        maze.setHeuristicDistance(goal);  // Set the heuristic distance for all nodes
        frontier.add(start);

        Map<Node, Node> path = new HashMap<>();

        while (!frontier.isEmpty()) {
            Node current = frontier.dequeue();

            if (current.equals(goal)) {
                return reconstructPath(path, goal);
            }

            for (Node neighbor : maze.getNeighbors(current)) {
                if (visitedNodes.contains(neighbor)) {
                    continue;
                }

                int tentativeCost = this.costMap.get(current) + 1;
                if (tentativeCost < this.costMap.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    visitedNodes.add(current);

                    this.costMap.put(neighbor, tentativeCost);
                    path.put(neighbor, current);
                    frontier.remove(neighbor);
                    frontier.add(neighbor);
                }
            }
        }
        return new ArrayList<>();
    }
}
