import java.util.*;

/**
 * The MazePath class represents a path through a maze.
 * It uses a map to store the relationship between a node and its predecessor in the path.
 * The goal node is used to reconstruct the path from the start to the goal.
 */
public class MazePath {
    // A map where the key is a node and the value is its predecessor in the path.
    private final Map<Node, Node> path;
    // The goal node of the maze.
    private final Node goal;

    /**
     * Constructs a new MazePath with the specified goal node.
     *
     * @param goal The goal node of the maze.
     */
    public MazePath(Node goal){
        this.path = new HashMap<>();
        this.goal = goal;
    }

    /**
     * Adds a relationship between a neighbor node and its predecessor (current) in the path.
     *
     * @param neighbor The neighbor node.
     * @param current  The current node which is the predecessor of the neighbor in the path.
     */
    public void add(Node neighbor, Node current){
        path.put(neighbor, current);
    }

    /**
     * Returns the predecessor of the given node in the path.
     *
     * @param node The node whose predecessor is to be retrieved.
     * @return The predecessor node or null if not found.
     */
    public Node getPredecessor(Node node) {
        return path.get(node);
    }

    /**
     * Returns the goal node of the maze.
     *
     * @return The goal node.
     */
    public Node getGoal() {
        return goal;
    }

    /**
     * Reconstructs the path from the start node to the goal node using the path map.
     * The path map contains each node's parent in the path.
     *
     * @return A list of nodes representing the path from the start node to the goal node.
     */
    public List<Node> getReconstructPath(){
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
