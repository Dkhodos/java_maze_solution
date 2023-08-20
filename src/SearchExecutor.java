import java.util.List;

/**
 * The SearchExecutor class is responsible for executing a list of search algorithms on a given maze.
 * It then displays the results and generates reports for each algorithm's execution.
 */
public class SearchExecutor {
    private final List<SearchAlgorithm> algorithms;
    private static final String RESULT_TEMPLATE = "Algorithm: %s, Visited Nodes: %d, Path Length: %d";

    private static final Logger logger = new Logger("SearchExecutor");
    private static final ReportMaker reportMaker = new ReportMaker();

    /**
     * Initializes a new instance of the SearchExecutor with a list of search algorithms.
     *
     * @param algorithms The list of search algorithms to be executed.
     */
    public SearchExecutor(List<SearchAlgorithm> algorithms) {
        this.algorithms = algorithms;
    }

    /**
     * Executes each search algorithm on the provided maze and generates reports for each execution.
     *
     * @param maze The maze to be solved.
     * @param start The starting node in the maze.
     * @param goal The goal node in the maze.
     */
    public void executeSearchAndGenerateReports(Maze maze, Node start, Node goal) {
        for (SearchAlgorithm algorithm : algorithms) {
            executeAlgorithm(algorithm, maze, start, goal);
            System.out.println();
        }
    }

    /**
     * Executes a specific search algorithm on the provided maze, logs the execution time,
     * displays the results, and generates a report.
     *
     * @param algorithm The search algorithm to be executed.
     * @param maze The maze to be solved.
     * @param start The starting node in the maze.
     * @param goal The goal node in the maze.
     */
    private void executeAlgorithm(SearchAlgorithm algorithm, Maze maze, Node start, Node goal) {
        long startTime = System.currentTimeMillis();

        SearchResult result = algorithm.solve(maze, start, goal);

        long endTime = System.currentTimeMillis();
        logger.info(String.format("%s executed in %dms", algorithm.getClass().getSimpleName(), endTime - startTime));

        displayResults(algorithm, result);
        reportMaker.createReport(start, goal, maze, result.path(), algorithm.getClass().getSimpleName(), result.visitedNodes().size());
    }

    /**
     * Displays the results of a search algorithm's execution.
     *
     * @param algorithm The executed search algorithm.
     * @param result The result of the algorithm's execution.
     */
    private void displayResults(SearchAlgorithm algorithm, SearchResult result) {
        String response = String.format(
                RESULT_TEMPLATE,
                algorithm.getClass().getSimpleName(),
                result.visitedNodes().size(),
                result.path().size()
        );
        System.out.print(response + " ");
        printPath(result.path());
    }

    /**
     * Prints the path found by a search algorithm.
     *
     * @param path The path to be printed.
     */
    private void printPath(List<Node> path) {
        path.forEach(node -> System.out.print(node + " "));
        System.out.println();
    }
}
