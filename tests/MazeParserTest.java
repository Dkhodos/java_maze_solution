import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

public class MazeParserTest {
    MazeParser parser;

    @BeforeEach
    public void setUp() {
        parser = new MazeParser();
    }

    @Test
    public void testParseMaze() {
        Maze maze = parser.parse(getTestFileAbsolutePath("test_data/maze_1.txt"));

        assertEquals(10, maze.size());

        assertFalse(maze.nodes()[0][0].isObstacle());
        assertFalse(maze.nodes()[0][2].isObstacle());
        assertTrue(maze.nodes()[1][3].isObstacle());
        assertTrue(maze.nodes()[8][2].isObstacle());
    }

    @Test
    public void testEmptyFile() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(getTestFileAbsolutePath("test_data/empty_file.txt"));
        });
    }

    @Test
    public void testSizeMissingFile() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(getTestFileAbsolutePath("test_data/maze_no_size.txt"));
        });
    }


    private String getTestFileAbsolutePath(String path){
        URL resource = getClass().getClassLoader().getResource(path);
        File file = new File(resource.getFile());
        return file.getAbsolutePath();
    }
}
