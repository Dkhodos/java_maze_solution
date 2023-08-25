import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class SearchBaseTest {
    protected Maze maze;
    protected Node start;
    protected Node goal;

    protected void initMazeFromFile(String fileName){
        String filePath = System.getProperty("user.dir") + "/tests/test_data/" + fileName;
        maze = (new MazeParser()).parse(filePath);

        start = maze.getNode(0, 0); // Assuming start is at (0, 0)
        goal = maze.getNode(maze.size()-1, maze.size()-1); // Assuming goal is at (n, n)
    }

    protected void validateSearchResults(SearchResult result, int expectedPathSize,
                                         int expectedVisitedNodesSize){
        assertFalse(result.path().isEmpty(), "Path is Empty!");
        assertEquals(start, result.path().get(0), "Start isn't (0, 0)!");
        assertEquals(goal, result.path().get(result.path().size() - 1), "End isn't (n-1, n-1)!");
        assertEquals(expectedPathSize, result.path().size(), "Unexpected path size!");
        assertEquals(expectedVisitedNodesSize, result.visitedNodes().size(), "Unexpected visited size!");
    }

    protected void validateScenarioResults(SearchResult result, String expected, int expectedPathSize,
                                           int expectedVisitedNodesSize){
        assertFalse(result.path().isEmpty(), "Path is Empty!");
        assertEquals(start, result.path().get(0), "Start isn't (0, 0)!");
        assertEquals(goal, result.path().get(result.path().size() - 1), "End isn't (n-1, n-1)!");
        assertEquals(expectedPathSize, result.path().size(), "Unexpected path size!");
//        assertEquals(expectedVisitedNodesSize, result.visitedNodes().size(), "Unexpected visited size!");
        assertEquals(expected, result.toString(), "Unexpected result output!");
    }
}
