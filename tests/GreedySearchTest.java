import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GreedySearchTest extends SearchBaseTest{
    private GreedySearch greedy;

    @BeforeEach
    void setUp() {
        greedy = new GreedySearch();
    }

    @Test
    void testCost() {
        initMazeFromFile("maze_1.txt");

        // The cost of a node that is not visited yet should be Integer.MAX_VALUE
        assertEquals(Integer.MAX_VALUE, greedy.cost(new Node(1, 1, false, 0)));
    }

    @Test
    void testSolveUnsolvableMaze() {
        initMazeFromFile("maze_no_solution.txt");

        List<Node> path = greedy.solve(maze, start, goal);
        Set<Node> visitedNodes = greedy.getVisitedNodes();

        // The path should not be empty
        assertTrue(path.isEmpty());

        // The number of visited nodes should also be 9
        assertEquals(83, visitedNodes.size());
    }

    @Test
    void testSolve() {
        initMazeFromFile("maze_1.txt");

        List<Node> path = greedy.solve(maze, start, goal);
        Set<Node> visitedNodes = greedy.getVisitedNodes();

        validateSearchResults(path, visitedNodes, 19, 19);
    }

    @Test
    void testSolveBigMaze() {
        initMazeFromFile("big_maze.txt");

        List<Node> path = greedy.solve(maze, start, goal);
        Set<Node> visitedNodes = greedy.getVisitedNodes();

        validateSearchResults(path, visitedNodes, 367, 1557);
    }
}
