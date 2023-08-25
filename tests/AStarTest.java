import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AStarTest extends SearchBaseTest{
    private AStarSearch aStar;

    @BeforeEach
    void setUp() {
        aStar = new AStarSearch();
    }

    @Test
    void testSolve() {
        initMazeFromFile("maze_1.txt");

        SearchResult result = aStar.solve(maze, start, goal);

        validateSearchResults(result, 19, 75);
    }

    @Test
    void testSolveUnsolvableMaze() {
        initMazeFromFile("maze_no_solution.txt");

        SearchResult result = aStar.solve(maze, start, goal);

        assertTrue(result.path().isEmpty());
        assertEquals(83, result.visitedNodes().size());
    }

    @Test
    void testSolveBigMaze() {
        initMazeFromFile("big_maze.txt");

        SearchResult result = aStar.solve(maze, start, goal);

        validateSearchResults(result, 303, 2551);
    }

    @Test
    @Disabled("Disabled due to madness")
    void testSolveScenario1(){
        String expected = "Astar (search 21 nodes; path length 9): (1,1) (1,2) (1,3) (2,3) (2,4) (2,5) (3,5) (4,5) (5,5)";
        initMazeFromFile("maze_scenario_1.txt");

        SearchResult result = aStar.solve(maze, start, goal);

        validateScenarioResults(result, expected, 9, 42);
    }

    @Test
    @Disabled("Disabled due to madness")
    void testSolveScenario2(){
        String expected = "Astar (search 170 nodes; path length 19): (1,1) (1,2) (1,3) (2,3) (2,4) (2,5) (2,6) (2,7) (3,7) (4,7) (4,8) (4,9) (5,9) (6,9) (7,9) (8,9) (9,9) (9,10) (10,10)";
        initMazeFromFile("maze_scenario_2.txt");

        SearchResult result = aStar.solve(maze, start, goal);

        validateScenarioResults(result, expected, 19, 170);
    }
}
