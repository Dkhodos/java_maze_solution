import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class FrontierTest {
    private Frontier frontier;

    @BeforeEach
    public void setUp() {
        Comparator<Node> comparator = Comparator.comparingInt(Node::x);  // Simple comparator based on x-coordinate for testing
        frontier = new Frontier(comparator);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(frontier.isEmpty());

        Node node = new Node(1, 1);
        frontier.add(node);

        assertFalse(frontier.isEmpty());
    }

    @Test
    public void testDequeue() {
        Node node1 = new Node(1, 1);
        Node node2 = new Node(2, 2);

        frontier.add(node1);
        frontier.add(node2);

        assertEquals(node1, frontier.dequeue());
        assertEquals(node2, frontier.dequeue());
        assertTrue(frontier.isEmpty());
    }

    @Test
    public void testRemove() {
        Node node1 = new Node(1, 1);
        Node node2 = new Node(2, 2);

        frontier.add(node1);
        frontier.add(node2);

        frontier.remove(node1);

        assertFalse(frontier.isEmpty());
        assertEquals(node2, frontier.dequeue());
        assertTrue(frontier.isEmpty());
    }
}
