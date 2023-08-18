import java.util.*;


public class UniformCostSearch extends SearchAlgorithm {

    @Override
    public SearchResult solve(Maze maze, Node start, Node goal) {
        Map<Node, Integer> costMap = new HashMap<>();
        Frontier frontier = new Frontier(Comparator.comparing(node -> this.comparator(node, costMap)));
        Set<Node> visitedNodes = new HashSet<>();

        costMap.put(start, 0);
        frontier.add(start);

        Map<Node, Node> path = new HashMap<>();

        while (!frontier.isEmpty()) {
            Node current = frontier.dequeue();

            if (current.equals(goal)) {
                visitedNodes.add(current);
                List<Node> finalPath = reconstructPath(path, goal);
                return new SearchResult(finalPath, visitedNodes);
            }

            for (Node neighbor : maze.getNeighbors(current)) {
                if (visitedNodes.contains(neighbor)) {
                    continue;
                }

                int tentativeCost = costMap.get(current) + 1;
                if (tentativeCost < getCost(neighbor, costMap)) {
                    visitedNodes.add(current);
                    costMap.put(neighbor, tentativeCost);
                    path.put(neighbor, current);
                    frontier.update(neighbor);
                }
            }
        }
        return new SearchResult(new ArrayList<>(), visitedNodes);
    }

    private int comparator(Node node , Map<Node, Integer> costMap){
        return this.getCost(node, costMap);
    }
}