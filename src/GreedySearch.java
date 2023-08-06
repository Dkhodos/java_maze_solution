import java.util.*;

public class GreedySearch extends SearchAlgorithm {
    @Override
    public List<Node> solve(Maze maze, Node start, Node goal) {
        visitedNodes = new HashSet<>();
        frontier = new Frontier(Comparator.comparing(Node::getHeuristicDistance));

        frontier.add(start);
        this.costMap.put(start, 0);

        Map<Node, Node> path = new HashMap<>(); // This map will help us reconstruct the path.

        while (!frontier.isEmpty()) {
            Node current = frontier.removeHighestPriorityNode();

            if (current.equals(goal)) { // If the goal is reached, reconstruct the path and return it.
                visitedNodes.add(current);
                return reconstructPath(path, goal);
            }

            for (Node neighbor : maze.getNeighbors(current)) {
                if (!visitedNodes.contains(neighbor)) {
                    frontier.add(neighbor);
                    this.costMap.put(neighbor, this.costMap.get(current) + 1);
                    path.put(neighbor, current); // Keep track of the parent node.
                    visitedNodes.add(current);
                }
            }
        }
        return new ArrayList<>();
    }
}
