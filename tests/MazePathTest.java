import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MazePathTest {
    MazePath mazePath;

    @BeforeEach
    public void setUp() {
        Node goal = new Node(2, 2);
        mazePath = new MazePath(goal);
    }

    @Test
    public void testAdd() {
        Node start = new Node(0, 0);
        Node next = new Node(1, 1);

        mazePath.add(next, start);

        assertEquals(start, mazePath.getPredecessor(next));
    }

    @Test
    public void testGetReconstructPathDataExists() {
        Node start = new Node(0, 0);
        Node next = new Node(1, 1);

        mazePath.add(next, start);
        mazePath.add(mazePath.getGoal(), next);

        List<Node> path = mazePath.getReconstructPath();

        assertEquals(3, path.size());
        assertEquals(start, path.get(0));
        assertEquals(next, path.get(1));
        assertEquals(mazePath.getGoal(), path.get(2));
    }

    @Test
    public void testGetReconstructPathEmpty() {
        List<Node> path = mazePath.getReconstructPath();

        assertEquals(1, path.size());
        assertEquals(mazePath.getGoal(), path.get(0));
    }

    @Test
    public void testGetReconstructPathSorted() {
        Node start = new Node(0, 0);
        Node n1 = new Node(1, 0);
        Node n2 = new Node(2, 0);
        Node n3 = new Node(3, 0);
        Node n4 = new Node(4, 0);

        // Create an array of nodes and shuffle it
        Node[] nodes = {n1, n2, n3, n4};
        List<Node> shuffledNodes = new ArrayList<>(Arrays.asList(nodes));
        Collections.shuffle(shuffledNodes);

        // Create a map to maintain the correct parent-child relationship
        Map<Node, Node> correctOrder = Map.of(
                n1, start,
                n2, n1,
                n3, n2,
                n4, n3
        );

        // Add nodes to the path using the correct parent-child relationship
        for (Node node : shuffledNodes) {
            mazePath.add(node, correctOrder.get(node));
        }
        mazePath.add(mazePath.getGoal(), n4);

        List<Node> path = mazePath.getReconstructPath();

        assertEquals(6, path.size()); // 5 nodes + goal node
        assertEquals(start, path.get(0));
        assertEquals(n1, path.get(1));
        assertEquals(n2, path.get(2));
        assertEquals(n3, path.get(3));
        assertEquals(n4, path.get(4));
        assertEquals(mazePath.getGoal(), path.get(5));
    }
}