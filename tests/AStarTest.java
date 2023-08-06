import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AStarTest extends SearchBaseTest{
    private AStarSearch aStar;

    @BeforeEach
    void setUp() {
        aStar = new AStarSearch();
    }

    @Test
    void testCost() {
        initMazeFromFile("maze_1.txt");

        assertEquals(Integer.MAX_VALUE, aStar.cost(new Node(1, 1, false, 0)));
    }

    @Test
    void testSolve() {
        initMazeFromFile("maze_1.txt");

        List<Node> path = aStar.solve(maze, start, goal);
        Set<Node> visitedNodes = aStar.getVisitedNodes();

        validateSearchResults(path, visitedNodes, 19, 49);
    }

    @Test
    void testSolveUnsolvableMaze() {
        initMazeFromFile("maze_no_solution.txt");

        List<Node> path = aStar.solve(maze, start, goal);
        Set<Node> visitedNodes = aStar.getVisitedNodes();

        assertTrue(path.isEmpty());
        assertEquals(63, visitedNodes.size());
    }

    @Test
    void testSolveBigMaze() {
        initMazeFromFile("big_maze.txt");

        List<Node> path = aStar.solve(maze, start, goal);
        Set<Node> visitedNodes = aStar.getVisitedNodes();

        validateSearchResults(path, visitedNodes, 301, 1836);
    }
}
