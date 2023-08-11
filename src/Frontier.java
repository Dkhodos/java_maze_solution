import java.util.Comparator;
import java.util.PriorityQueue;

public class Frontier {
    private final PriorityQueue<Node> queue;

    public Frontier(Comparator<Node> comparator) {
        this.queue = new PriorityQueue<>(comparator);
    }

    public void add(Node node) {
        queue.add(node);
    }

    public Node poll() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Node dequeue(){
        return this.queue.remove();
    }

    public void remove(Node node){
        this.queue.remove(node);
    }

    public void update(Node node){
        this.remove(node);
        this.add(node);
    }
}
