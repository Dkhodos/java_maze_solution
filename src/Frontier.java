import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;

/**
 * The Frontier class represents a priority queue-based frontier used in search algorithms.
 * It wraps around Java's PriorityQueue and uses a HashSet for efficient node removal.
 * The PriorityQueue determines the priority of nodes based on a provided comparator,
 * while the HashSet allows for quick membership checks and removals.
 */
public class Frontier {
    private final PriorityQueue<Node> queue;  // Priority queue to manage node priorities
    private final Set<Node> nodeSet;  // HashSet for quick membership checks and removals

    /**
     * Constructs a new Frontier with a given comparator to determine the priority of nodes.
     *
     * @param comparator The comparator used to prioritize nodes.
     */
    public Frontier(Comparator<Node> comparator) {
        this.queue = new PriorityQueue<>(comparator);
        this.nodeSet = new HashSet<>();
    }

    /**
     * Adds a node to the frontier.
     *
     * @param node The node to be added.
     */
    public void add(Node node) {
        queue.add(node);
        nodeSet.add(node);
    }

    /**
     * Checks if the frontier is empty.
     *
     * @return true if the frontier is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Retrieves and removes the head of this frontier.
     *
     * @return The head of the frontier.
     */
    public Node dequeue(){
        Node node = this.queue.remove();
        nodeSet.remove(node);
        return node;
    }

    /**
     * Removes a specific node from the frontier if it exists.
     *
     * @param node The node to be removed.
     */
    public void remove(Node node){
        if (nodeSet.contains(node)) {
            nodeSet.remove(node);
            queue.remove(node);
        }
    }

    public boolean contains(Node node){
        return nodeSet.contains(node);
    }
}
