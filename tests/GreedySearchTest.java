import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreedySearchTest extends SearchBaseTest{
    private GreedySearch greedy;

    @BeforeEach
    void setUp() {
        greedy = new GreedySearch();
    }

    @Test
    void testSolveUnsolvableMaze() {
        initMazeFromFile("maze_no_solution.txt");

        SearchResult result = greedy.solve(maze, start, goal);

        assertTrue(result.path().isEmpty());
        assertEquals(83, result.visitedNodes().size());
    }

    @Test
    void testSolve() {
        initMazeFromFile("maze_1.txt");

        SearchResult result = greedy.solve(maze, start, goal);

        validateSearchResults(result, 19, 19);
    }

    @Test
    void testSolveBigMaze() {
        initMazeFromFile("big_maze.txt");

        SearchResult result = greedy.solve(maze, start, goal);

        validateSearchResults(result, 397, 1578);
    }

    @Test
    @Disabled("Disabled due to madness")
    void testSolveScenario1(){
        String expected = "Greedy (search 20 nodes; path length 11): (1,1) (2,1) (3,1) (3,2) (3,3) (2,3) (2,4) (2,5) (3,5) (4,5) (5,5)";
        initMazeFromFile("maze_scenario_1.txt");

        SearchResult result = greedy.solve(maze, start, goal);

        validateScenarioResults(result, expected, 11, 20);
    }

    @Test
    @Disabled("Disabled due to madness")
    void testSolveScenario2(){
        String expected = "Greedy (search 77 nodes; path length 21): (1,1) (2,1) (3,1) (3,2) (3,3) (2,3) (2,4) (2,5) (3,5) (4,5) (5,5) (5,6) (6,6) (7,6) (8,6) (9,6) (10,6) (10,7) (10,8) (10,9) (10,10)";
        initMazeFromFile("maze_scenario_2.txt");

        SearchResult result = greedy.solve(maze, start, goal);

        validateScenarioResults(result, expected, 21, 77);
    }
}
