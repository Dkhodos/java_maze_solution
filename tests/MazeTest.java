import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    @Test
    public void testGetNeighbors() {
        Node[][] nodes = new Node[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                nodes[i][j] = new Node(i, j, false); // fill the maze with non-obstacle nodes
            }
        }

        // let's create an obstacle at (1,1)
        nodes[1][1] = new Node(1, 1, true);

        Maze maze = new Maze(3, nodes);

        // Test corner nodes
        // top left corner
        List<Node> neighbors = maze.getNeighbors(nodes[0][0]);
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains(nodes[0][1]));
        assertTrue(neighbors.contains(nodes[1][0]));

        // top right corner
        neighbors = maze.getNeighbors(nodes[0][2]);
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains(nodes[0][1]));
        assertTrue(neighbors.contains(nodes[1][2]));

        // bottom left corner
        neighbors = maze.getNeighbors(nodes[2][0]);
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains(nodes[2][1]));
        assertTrue(neighbors.contains(nodes[1][0]));

        // bottom right corner
        neighbors = maze.getNeighbors(nodes[2][2]);
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains(nodes[2][1]));
        assertTrue(neighbors.contains(nodes[1][2]));

        // Test node adjacent to an obstacle
        neighbors = maze.getNeighbors(nodes[0][1]);
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains(nodes[0][0]));
        assertTrue(neighbors.contains(nodes[0][2]));

        // Test obstacle node
        neighbors = maze.getNeighbors(nodes[1][1]);
        assertTrue(neighbors.isEmpty());
    }
}
