import java.util.*;

public class Frontier {
    private final PriorityQueue<Node> queue;
    private final Set<Node> nodeSet;

    public Frontier(Comparator<Node> comparator) {
        this.queue = new PriorityQueue<>(comparator);
        this.nodeSet = new HashSet<>();
    }

    public void add(Node node) {
        queue.add(node);
        nodeSet.add(node);
    }

    public Node removeHighestPriorityNode() {
        Node node = queue.poll();
        if (node != null) {
            nodeSet.remove(node);
        }
        return node;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean contains(Node node) {
        return nodeSet.contains(node);
    }

    public Node dequeue() {
        Node node = queue.remove();
        if (node != null) {
            nodeSet.remove(node);
        }
        return node;
    }

    public void remove(Node node) {
        nodeSet.remove(node);
        queue.remove(node);
    }
}
