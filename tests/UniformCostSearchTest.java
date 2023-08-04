import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UniformCostSearchTest {

    private Maze maze;
    private Node start;
    private Node goal;
    private UniformCostSearch ucs;

    @BeforeEach
    void setUp() {
        start = new Node(0, 0, false);
        goal = new Node(9, 9, false);
        ucs = new UniformCostSearch();

        String filePath = System.getProperty("user.dir") + "/tests/test_data/maze_1.txt";
        maze = (new MazeParser()).parse(filePath);
    }

    @Test
    void testCost() {
        // The cost of a node that is not visited yet should be Integer.MAX_VALUE
        assertEquals(Integer.MAX_VALUE, ucs.cost(new Node(1, 1, false)));
    }

    @Test
    void testSolve() {
        List<Node> path = ucs.solve(maze, start, goal);
        Set<Node> visitedNodes = ucs.getVisitedNodes();

        // The path should not be empty
        assertFalse(path.isEmpty());

        // The first node in the path should be the start node
        assertEquals(start, path.get(0));

        // The last node in the path should be the goal node
        assertEquals(goal, path.get(path.size() - 1));

        // The path length should be 9 (including start and goal)
        assertEquals(19, path.size());

        // The number of visited nodes should also be 9
        assertEquals(87, visitedNodes.size());
    }
}
