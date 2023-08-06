import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class SearchBaseTest {
    protected Maze maze;
    protected Node start;
    protected Node goal;

    protected void initMazeFromFile(String fileName){
        String filePath = System.getProperty("user.dir") + "/tests/test_data/" + fileName;
        maze = (new MazeParser()).parse(filePath);

        start = maze.nodes()[0][0]; // Assuming start is at (0, 0)
        goal = maze.nodes()[maze.size()-1][maze.size()-1]; // Assuming goal is at (n, n)

        maze.setHeuristicDistance(goal);
    }

    protected void validateSearchResults(List<Node> path, Set<Node> visitedNodes, int expectedPathSize,
                                         int expectedVisitedNodesSize){
        assertFalse(path.isEmpty());
        assertEquals(start, path.get(0));
        assertEquals(goal, path.get(path.size() - 1));
        assertEquals(expectedPathSize, path.size());
        assertEquals(expectedVisitedNodesSize, visitedNodes.size());
    }
}
