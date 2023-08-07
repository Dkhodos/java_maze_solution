import org.junit.jupiter.api.BeforeEach;
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
        assertEquals(76, result.visitedNodes().size());
    }

    @Test
    void testSolve() {
        initMazeFromFile("maze_1.txt");

        SearchResult result = greedy.solve(maze, start, goal);

        validateSearchResults(result.path(), result.visitedNodes(), 19, 19);
    }

    @Test
    void testSolveBigMaze() {
        initMazeFromFile("big_maze.txt");

        SearchResult result = greedy.solve(maze, start, goal);

        validateSearchResults(result.path(), result.visitedNodes(), 397, 1329);
    }
}
