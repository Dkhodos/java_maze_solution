import java.util.List;

/**
 * The SearchExecutor class is responsible for executing a list of search algorithms on a given maze.
 * It then displays the results and generates reports for each algorithm's execution.
 */
public class SearchExecutor {
    private final List<SearchAlgorithm> algorithms;
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

        displayResults(result);
        reportMaker.createReport(start, goal, maze, result);
    }

    /**
     * Displays the results of a search algorithm's execution.
     *
     * @param result The result of the algorithm's execution.
     */
    private void displayResults(SearchResult result) {
        System.out.println(result.toString());
    }
}
