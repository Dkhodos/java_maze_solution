import java.util.List;
import java.util.Set;

/**
 * The SearchResult class represents the result of a search algorithm's execution on a maze.
 * It encapsulates the path found from the start node to the goal node (if any) and the set of nodes that were visited during the search.
 * @param path The path found by the search algorithm, represented as a list of nodes.
 *             An empty list indicates that no path was found.
 * @param visitedNodes The set of nodes that were visited during the search.
 */
public record SearchResult(List<Node> path, Set<Node> visitedNodes, String name) {
    private static final String RESULT_TEMPLATE = "%s (search %d nodes; path length %d): ";

    @Override
    public String toString() {
        String response = String.format(
                RESULT_TEMPLATE,
                this.name,
                this.visitedNodes.size(),
                this.path.size()
        );
        response += getPathString();
        return response;
    }

    public String getPathString(){
        StringBuilder response = new StringBuilder();
        for (Node node : path){
            response.append(node).append(" ");
        }
        return response.toString();
    }
}
