import java.util.List;
import java.util.Set;

/**
 * The SearchResult class represents the result of a search algorithm's execution on a maze.
 * It encapsulates the path found from the start node to the goal node (if any) and the set of nodes that were visited during the search.
 * @param path The path found by the search algorithm, represented as a list of nodes.
 *             An empty list indicates that no path was found.
 * @param visitedNodes The set of nodes that were visited during the search.
 */
public record SearchResult(List<Node> path, Set<Node> visitedNodes) {}
