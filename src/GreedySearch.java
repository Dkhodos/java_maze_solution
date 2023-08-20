import java.util.*;

public class GreedySearch extends SearchAlgorithm {

    @Override
    public SearchResult solve(Maze maze, Node start, Node goal) {
        Set<Node> visitedNodes = new HashSet<>();
        Frontier frontier = new Frontier(Comparator.comparing((Node n) -> this.comparator(n, goal)));

        frontier.add(start);

        Map<Node, Node> path = new HashMap<>();

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.equals(goal)) {
                visitedNodes.add(current);
                List<Node> finalPath = reconstructPath(path, goal);
                return new SearchResult(finalPath, visitedNodes);
            }

            for (Node neighbor : maze.getNeighbors(current)) {
                if(visitedNodes.contains(neighbor)){
                    continue;
                }

                path.put(neighbor, current);
                visitedNodes.add(current);
                frontier.update(neighbor);
            }
        }
        return new SearchResult(new ArrayList<>(), visitedNodes);
    }

    private int comparator(Node node , Node goal){
        return this.getHeuristicDistance(node, goal);
    }
}
