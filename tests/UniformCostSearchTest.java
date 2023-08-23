import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniformCostSearchTest extends SearchBaseTest{
    private UniformCostSearch ucs;

    @BeforeEach
    void setUp() {
        ucs = new UniformCostSearch();
    }

    @Test
    void testSolve() {
        initMazeFromFile("maze_1.txt");

        SearchResult result = ucs.solve(maze, start, goal);

        validateSearchResults(result.path(), result.visitedNodes(), 19, 87);
    }

    @Test
    void testSolveUnsolvableMaze() {
        initMazeFromFile("maze_no_solution.txt");

        SearchResult result = ucs.solve(maze, start, goal);

        assertTrue(result.path().isEmpty());
        assertEquals(83, result.visitedNodes().size());
    }

    @Test
    void testSolveBigMaze() {
        initMazeFromFile("big_maze.txt");

        SearchResult result = ucs.solve(maze, start, goal);

        validateSearchResults(result.path(), result.visitedNodes(), 301, 3901);
    }

    @Test
    void testSolveScenario1(){
        initMazeFromFile("maze_scenario_1.txt");

        SearchResult result = ucs.solve(maze, start, goal);

        validateSearchResults(result.path(), result.visitedNodes(), 15, 64);
    }
}
