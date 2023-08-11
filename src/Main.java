import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger("Main Class");
        String file_path = System.getProperty("user.dir") + "/data/maze_1.txt";
//        if (args.length != 1) {
//            System.err.println("Usage: java Main <maze_file>");
//            System.exit(1);
//        }

        MazeParser parser = new MazeParser();
        Maze maze = parser.parse(file_path);
        if (maze == null) {
            logger.error("Error parsing maze file.");
            System.exit(1);
        }

        Node start = maze.nodes()[0][0]; // Assuming start is at (0, 0)
        Node goal = maze.nodes()[maze.size()-1][maze.size()-1]; // Assuming goal is at (n, n)

        List<SearchAlgorithm> algorithmsList = Arrays.asList(
                new AStarSearch(),
                new GreedySearch(),
                new UniformCostSearch()
        );

        SearchExecutor executor = new SearchExecutor(algorithmsList);
        executor.executeSearchAndGenerateReports(maze, start, goal);
    }
}
