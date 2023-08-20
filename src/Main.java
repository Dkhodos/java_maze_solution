import java.util.Arrays;
import java.util.List;

/**
 * The Main class serves as the entry point for the maze-solving application.
 * It reads a maze from a file, initializes various search algorithms, and then executes each algorithm on the maze.
 * The results of each algorithm's execution are then displayed and reports are generated.
 */
public class Main {
    public static void main(String[] args) {
        // Logger instance for logging messages.
        Logger logger = new Logger("Main Class");

        // Ensure that the correct number of command-line arguments are provided.
        if (args.length != 1) {
            System.err.println("Usage: java Main <absolute_path_to_maze_file | relative_path_to_maze_file>");
            System.exit(1);
        }

        String file_path = args[0];

        // Adjust the file path if it doesn't contain the expected directory structure.
        if(!file_path.contains("/java_maze_solution")){
            file_path = System.getProperty("user.dir") + "/" + file_path;
        }

        // Parse the maze from the provided file.
        MazeParser parser = new MazeParser();
        Maze maze = parser.parse(file_path);
        if (maze == null) {
            logger.error("Error parsing maze file.");
            System.exit(1);
        }

        // Define the start and goal nodes for the maze.
        Node start = maze.getNode(0, 0); // Assuming start is at (0, 0)
        Node goal = maze.getNode(maze.size()-1, maze.size()-1); // Assuming goal is at (n, n)

        // List of search algorithms to be executed on the maze.
        List<SearchAlgorithm> algorithmsList = Arrays.asList(
                new AStarSearch(),
                new GreedySearch(),
                new UniformCostSearch()
        );

        // Initialize the search executor and execute each algorithm on the maze.
        SearchExecutor executor = new SearchExecutor(algorithmsList);
        executor.executeSearchAndGenerateReports(maze, start, goal);
    }
}
