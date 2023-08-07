import java.util.List;

public class SearchExecutor {
    private final List<SearchAlgorithm> algorithms;
    private static final String RESULT_TEMPLATE = "Algorithm: %s, Visited Nodes: %d, Path Length: %d";

    private static final Logger logger = new Logger("SearchExecutor");
    private static final ReportMaker reportMaker = new ReportMaker();

    public SearchExecutor(List<SearchAlgorithm> algorithms) {
        this.algorithms = algorithms;
    }

    public void executeSearchAndGenerateReports(Maze maze, Node start, Node goal) {
        for (SearchAlgorithm algorithm : algorithms) {
            executeAlgorithm(algorithm, maze, start, goal);
            System.out.println();
        }
    }

    private void executeAlgorithm(SearchAlgorithm algorithm, Maze maze, Node start, Node goal) {
        long startTime = System.currentTimeMillis();

        SearchResult result = algorithm.solve(maze, start, goal);

        long endTime = System.currentTimeMillis();
        logger.info(String.format("%s executed in %dms", algorithm.getClass().getSimpleName(), endTime - startTime));

        displayResults(algorithm, result);
        reportMaker.createReport(start, goal, maze, result.path(), algorithm.getClass().getSimpleName(), result.visitedNodes().size());
    }

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

    private void printPath(List<Node> path) {
        path.forEach(node -> System.out.print(node + " "));
        System.out.println();
    }

}