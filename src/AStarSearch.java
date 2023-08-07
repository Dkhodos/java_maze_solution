import java.util.*;

public class AStarSearch extends SearchAlgorithm {

    @Override
    public SearchResult solve(Maze maze, Node start, Node goal) {
        Map<Node, Integer> costMap = new HashMap<>();
        Set<Node> visitedNodes = new HashSet<>();
        Frontier frontier = new Frontier(Comparator.comparing(node -> cost(node, costMap) + node.getHeuristicDistance()));

        costMap.put(start, 0);
        maze.setHeuristicDistance(goal);  // Set the heuristic distance for all nodes
        frontier.add(start);

        Map<Node, Node> path = new HashMap<>();

        while (!frontier.isEmpty()) {
            Node current = frontier.dequeue();

            if (current.equals(goal)) {
                List<Node> finalPath = reconstructPath(path, goal);
                return new SearchResult(finalPath, visitedNodes);
            }

            for (Node neighbor : maze.getNeighbors(current)) {
                if (visitedNodes.contains(neighbor)) {
                    continue;
                }

                int tentativeCost = costMap.get(current) + 1;
                if (tentativeCost < cost(neighbor, costMap)) {
                    visitedNodes.add(current);
                    costMap.put(neighbor, tentativeCost);
                    path.put(neighbor, current);
                    frontier.remove(neighbor);
                    frontier.add(neighbor);
                }
            }
        }
        return new SearchResult(new ArrayList<>(), visitedNodes);
    }
}
