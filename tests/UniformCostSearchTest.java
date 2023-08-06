import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UniformCostSearchTest extends SearchBaseTest{
    private UniformCostSearch ucs;

    @BeforeEach
    void setUp() {
        ucs = new UniformCostSearch();
    }

    @Test
    void testCost() {
        initMazeFromFile("maze_1.txt");

        // The cost of a node that is not visited yet should be Integer.MAX_VALUE
        assertEquals(Integer.MAX_VALUE, ucs.cost(new Node(1, 1, false, 0)));
    }

    @Test
    void testSolve() {
        initMazeFromFile("maze_1.txt");

        List<Node> path = ucs.solve(maze, start, goal);
        Set<Node> visitedNodes = ucs.getVisitedNodes();

        validateSearchResults(path, visitedNodes, 19, 60);
    }

    @Test
    void testSolveUnsolvableMaze() {
        initMazeFromFile("maze_no_solution.txt");

        List<Node> path = ucs.solve(maze, start, goal);
        Set<Node> visitedNodes = ucs.getVisitedNodes();

        assertTrue(path.isEmpty());
        assertEquals(58, visitedNodes.size());
    }

    @Test
    void testSolveBigMaze() {
        initMazeFromFile("big_maze.txt");

        List<Node> path = ucs.solve(maze, start, goal);
        Set<Node> visitedNodes = ucs.getVisitedNodes();

        validateSearchResults(path, visitedNodes, 301, 2792);
    }
}
