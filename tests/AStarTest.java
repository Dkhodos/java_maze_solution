import org.junit.jupiter.api.BeforeEach;
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

        validateSearchResults(result.path(), result.visitedNodes(), 19, 49);
    }

    @Test
    void testSolveUnsolvableMaze() {
        initMazeFromFile("maze_no_solution.txt");

        SearchResult result = aStar.solve(maze, start, goal);

        assertTrue(result.path().isEmpty());
        assertEquals(63, result.visitedNodes().size());
    }

    @Test
    void testSolveBigMaze() {
        initMazeFromFile("big_maze.txt");

        SearchResult result = aStar.solve(maze, start, goal);

        validateSearchResults(result.path(), result.visitedNodes(), 301, 1836);
    }
}
