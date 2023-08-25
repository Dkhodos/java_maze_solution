import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

        validateSearchResults(result, 19, 87);
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

        validateSearchResults(result, 301, 3901);
    }

    @Test
    @Disabled("Disabled due to madness")
    void testSolveScenario1(){
        String expected = "UCS (search 42 nodes; path length 9): (1,1) (1,2) (1,3) (2,3) (2,4) (2,5) (3,5) (4,5) (5,5)";
        initMazeFromFile("maze_scenario_1.txt");

        SearchResult result = ucs.solve(maze, start, goal);

        validateScenarioResults(result, expected, 9, 42);
    }

    @Test
    @Disabled("Disabled due to madness")
    void testSolveScenario2(){
        String expected = "UCS (search 130319 nodes; path length 19): (1,1) (1,2) (1,3) (2,3) (2,4) (2,5) (2,6) (3,6) (3,7) (3,8) (4,8) (5,8) (6,8) (7,8) (8,8) (9,8) (9,9) (9,10) (10,10)";
        initMazeFromFile("maze_scenario_2.txt");

        SearchResult result = ucs.solve(maze, start, goal);

        validateScenarioResults(result, expected, 19, 130319);
    }
}
