import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * The ReportMaker class is responsible for generating HTML reports based on the results of a search algorithm's execution on a maze.
 * It reads from a template HTML file and replaces placeholders with the appropriate data to visualize the maze, the solution path, and other relevant information.
 */
public class ReportMaker {
    // Path to the HTML template file used for generating reports.
    final static String reportTemplateFile = System.getProperty("user.dir") + "/reports/report.template.html";

    // Template for converting a Node object into a JSON-like string format.
    final static String JavaScriptNodeTemplate = "{\"x\": %d, \"y\": %d, \"isObstacle\": %s}";

    // Logger instance for logging messages.
    final static Logger logger = new Logger("ReportMaker");

    /**
     * Creates an HTML report based on the provided data.
     *
     * @param start The starting node of the maze.
     * @param end The goal node of the maze.
     * @param maze The maze object containing the nodes and their properties.
     * @param solution The list of nodes representing the solution path.
     * @param name The name of the search algorithm used.
     * @param visitedNodes The number of nodes visited during the search.
     */
    public void createReport(Node start,Node end, Maze maze, List<Node> solution, String name, int visitedNodes) {
        try {
            // Reading the template HTML
            String html = new String(Files.readAllBytes(Paths.get(reportTemplateFile)));

            // Replacing the placeholders with the respective data
            html = html.replace("{{maze}}", stringifyMaze(maze));
            html = html.replace("{{solution}}", stringifySolution(solution));
            html = html.replace("{{start}}", stringifyNode(start));
            html = html.replace("{{end}}", stringifyNode(end));
            html = html.replace("{{visitedNodes}}", String.valueOf(visitedNodes));
            html = html.replace("{{className}}", name);

            // Preparing the report directory
            String reportDirectory = System.getProperty("user.dir") + "/reports";

            // Create the new report file with the incremented count
            String reportFile = reportDirectory + "/report" + "." + name + ".html";
            Files.write(Paths.get(reportFile), html.getBytes());

            logger.info("Report written to " + reportFile);
        } catch(IOException e) {
            logger.error("Error creating report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Converts the maze into a JSON-like string format suitable for the report.
     *
     * @param maze The maze object.
     * @return A string representation of the maze in a JSON-like format.
     */
    private String stringifyMaze(Maze maze) {
        Node[][] mazeArray = maze.nodes(); // Assuming getNodes returns the 2D array of Nodes in the Maze
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node[] row : mazeArray) {
            sb.append("[");
            for (Node node : row) {
                sb.append(stringifyNode(node)).append(",");
            }
            // Remove trailing comma
            sb.setLength(sb.length() - 1);
            sb.append("],");
        }
        // Remove trailing comma
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Converts the solution path into a JSON-like string format suitable for the report.
     *
     * @param solution The list of nodes representing the solution path.
     * @return A string representation of the solution path in a JSON-like format.
     */
    private String stringifySolution(List<Node> solution) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node node : solution) {
            sb.append(stringifyNode(node)).append(",");
        }
        // Remove trailing comma
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Converts a node into a JSON-like string format suitable for the report.
     *
     * @param node The node to be converted.
     * @return A string representation of the node in a JSON-like format.
     */
    private String stringifyNode(Node node) {
        return String.format(JavaScriptNodeTemplate, node.x(), node.y(), node.isObstacle());
    }
}