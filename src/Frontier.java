import java.util.Comparator;
import java.util.PriorityQueue;

public class Frontier {
    private PriorityQueue<Node> queue;

    public Frontier(Comparator<Node> comparator) {
        this.queue = new PriorityQueue<>(comparator);
    }

    public void add(Node node) {
        queue.add(node);
    }

    public Node removeHighestPriorityNode() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Node remove(){
        return this.queue.remove();
    }
}
