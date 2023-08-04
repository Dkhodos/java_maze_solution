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

        // The path should not be empty
        assertFalse(path.isEmpty());

        // The first node in the path should be the start node
        assertEquals(start, path.get(0));

        // The last node in the path should be the goal node
        assertEquals(goal, path.get(path.size() - 1));

        // The path length should be 9 (including start and goal)
        assertEquals(19, path.size());

        // The number of visited nodes should also be 9
        assertEquals(60, visitedNodes.size());
    }

    @Test
    void testSolveUnsolvableMaze() {
        initMazeFromFile("maze_no_solution.txt");

        List<Node> path = ucs.solve(maze, start, goal);
        Set<Node> visitedNodes = ucs.getVisitedNodes();

        // The path should not be empty
        assertTrue(path.isEmpty());

        // The number of visited nodes should also be 9
        assertEquals(58, visitedNodes.size());
    }

    @Test
    void testSolveBigMaze() {
        initMazeFromFile("big_maze.txt");

        List<Node> path = ucs.solve(maze, start, goal);
        Set<Node> visitedNodes = ucs.getVisitedNodes();

        // The path should not be empty
        assertFalse(path.isEmpty());

        // The first node in the path should be the start node
        assertEquals(start, path.get(0));

        // The last node in the path should be the goal node
        assertEquals(goal, path.get(path.size() - 1));

        // The path length should be 9 (including start and goal)
        assertEquals(301, path.size());

        // The number of visited nodes should also be 9
        assertEquals(2792, visitedNodes.size());
    }

    private void initMazeFromFile(String fileName){
        String filePath = System.getProperty("user.dir") + "/tests/test_data/" + fileName;
        maze = (new MazeParser()).parse(filePath);

        start = maze.nodes()[0][0]; // Assuming start is at (0, 0)
        goal = maze.nodes()[maze.size()-1][maze.size()-1]; // Assuming goal is at (n, n)

        maze.setHeuristicDistance(goal);
    }
}
