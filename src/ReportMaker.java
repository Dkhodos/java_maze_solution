import java.io.*;
import java.nio.file.*;
import java.util.List;

public class ReportMaker {
    final static String reportTemplateFile = System.getProperty("user.dir") + "/reports/report.template.html";

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

            System.out.println("Report written to " + reportFile);
        } catch(IOException e) {
            System.out.println("Error creating report: " + e.getMessage());
            e.printStackTrace();
        }
    }

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

    private String stringifyNode(Node node) {
        return String.format("{\"x\": %d, \"y\": %d, \"isObstacle\": %s}", node.getX(), node.getY(), node.isObstacle());
    }
}