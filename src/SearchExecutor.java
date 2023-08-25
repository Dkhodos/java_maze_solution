import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * The SearchExecutor class is responsible for executing a list of search algorithms on a given maze.
 * It then displays the results and generates reports for each algorithm's execution.
 */
public class SearchExecutor {
    private final List<SearchAlgorithm> algorithms;
    private static final Logger logger = new Logger("SearchExecutor");
    private static final ReportMaker reportMaker = new ReportMaker();
    private final String fileName;

    /**
     * Initializes a new instance of the SearchExecutor with a list of search algorithms.
     *
     * @param algorithms The list of search algorithms to be executed.
     */
    public SearchExecutor(List<SearchAlgorithm> algorithms, String filePath) {
        this.algorithms = algorithms;
        this.fileName = getFileName(filePath);
    }

    /**
     * Executes each search algorithm on the provided maze and generates reports for each execution.
     *
     * @param maze The maze to be solved.
     * @param start The starting node in the maze.
     * @param goal The goal node in the maze.
     */
    public void executeSearchAndGenerateReports(Maze maze, Node start, Node goal) {
        StringBuilder outputs = new StringBuilder();

        for (SearchAlgorithm algorithm : algorithms) {
            SearchResult result = algorithm.solve(maze, start, goal);
            reportMaker.createReport(start, goal, maze, result);

            String output = result.toString();
            logger.info(output);
            System.out.println();

            outputs.append(output).append("\n");
        }

        printToFile(outputs);
    }

    /**
     * Displays the results of a search algorithm's execution.
     *
     * @param output The result of the algorithm's execution.
     */
    private void printToFile(StringBuilder output) {
        PrintWriter writer;
        try {
            writer = new PrintWriter("outputs/"+ fileName, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writer.print(output);
        writer.close();
    }


    private String getFileName(String filePath){
        String [] paths = filePath.split("/");

        return paths[paths.length - 1];
    }
}
