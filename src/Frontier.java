import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The Frontier class represents a priority queue-based frontier used in search algorithms.
 * It wraps around Java's PriorityQueue to provide specific functionalities needed for the search algorithms.
 */
public class Frontier {
    private final PriorityQueue<Node> queue;

    /**
     * Constructs a new Frontier with a given comparator to determine the priority of nodes.
     *
     * @param comparator The comparator used to prioritize nodes.
     */
    public Frontier(Comparator<Node> comparator) {
        this.queue = new PriorityQueue<>(comparator);
    }

    /**
     * Adds a node to the frontier.
     *
     * @param node The node to be added.
     */
    public void add(Node node) {
        queue.add(node);
    }

    /**
     * Retrieves and removes the head of this frontier, or returns null if this frontier is empty.
     *
     * @return The head of the frontier, or null if the frontier is empty.
     */
    public Node poll() {
        return queue.poll();
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
        return this.queue.remove();
    }

    /**
     * Removes a specific node from the frontier.
     *
     * @param node The node to be removed.
     */
    public void remove(Node node){
        this.queue.remove(node);
    }

    /**
     * Updates the position of a node in the frontier. This is typically used when the priority of a node changes.
     * The method removes the node and then adds it back to reposition it based on its new priority.
     *
     * @param node The node to be updated.
     */
    public void update(Node node){
        this.remove(node);
        this.add(node);
    }
}
