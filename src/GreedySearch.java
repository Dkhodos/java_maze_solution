import java.util.*;

public class GreedySearch extends SearchAlgorithm {

    @Override
    public SearchResult solve(Maze maze, Node start, Node goal) {
        Map<Node, Integer> costMap = new HashMap<>();
        Set<Node> visitedNodes = new HashSet<>();
        Frontier frontier = new Frontier(Comparator.comparing(Node::getHeuristicDistance));

        frontier.add(start);
        costMap.put(start, 0);

        Map<Node, Node> path = new HashMap<>(); // This map will help us reconstruct the path.

        while (!frontier.isEmpty()) {
            Node current = frontier.removeHighestPriorityNode();

            if (current.equals(goal)) { // If the goal is reached, reconstruct the path and return it.
                visitedNodes.add(current);
                List<Node> finalPath = reconstructPath(path, goal);
                return new SearchResult(finalPath, visitedNodes);
            }

            for (Node neighbor : maze.getNeighbors(current)) {
                if (!visitedNodes.contains(neighbor)) {
                    frontier.add(neighbor);
                    costMap.put(neighbor, costMap.get(current) + 1);
                    path.put(neighbor, current); // Keep track of the parent node.
                    visitedNodes.add(current);
                }
            }
        }
        return new SearchResult(new ArrayList<>(), visitedNodes);
    }
}
