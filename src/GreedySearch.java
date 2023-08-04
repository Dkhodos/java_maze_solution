import java.util.*;
import java.util.stream.Stream;


public class GreedySearch extends SearchAlgorithm {
    @Override
    public List<Node> solve(Maze maze, Node start, Node goal) {
        visitedNodes = new HashSet<>();
        frontier = new Frontier(this::compare);

        frontier.add(start);
        this.costMap.put(start, 0);

        while (!frontier.isEmpty()) {
            Node current = frontier.removeHighestPriorityNode();
            visitedNodes.add(current);

            for (Node neighbor : maze.getNeighbors(current)) {
                if (!visitedNodes.contains(neighbor)) {
                    frontier.add(neighbor);
                    this.costMap.put(neighbor, this.costMap.get(current) + 1);

                    if (neighbor == goal) {
                        visitedNodes.add(neighbor);
                        return reconstructPath(visitedNodes);
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    private List<Node> reconstructPath(Set<Node> path) {
        Node[] pathAsArray = new Node[path.size()];
        path.toArray(pathAsArray);

        return Arrays.stream(pathAsArray).sorted((Node n1, Node n2) -> -this.compare(n1, n2)).toList();
    }

    private int compare(Node n1, Node n2){
        return n1.getHeuristicDistance() - n2.getHeuristicDistance(); // returns -1 if p1 < p2, 0 if p1 == p2, and 1 if p1 > p2
    }
}