import java.util.List;

public class Main {
    private static final String RESULT_TEMPLATE = "%s (search %d nodes; path length %d): ";

    public static void main(String[] args) {
        String file_path = System.getProperty("user.dir") + "/data/maze_1.txt";

        ReportMaker reportMaker = new ReportMaker();

//        if (args.length != 1) {
//            System.err.println("Usage: java Main <maze_file>");
//            System.exit(1);
//        }

        MazeParser parser = new MazeParser();
        Maze maze = parser.parse(file_path);
        if (maze == null) {
            System.err.println("Error parsing maze file.");
            System.exit(1);
        }

        Node start = maze.nodes()[0][0]; // Assuming start is at (0, 0)
        Node goal = maze.nodes()[maze.size()-1][maze.size()-1]; // Assuming goal is at (n, n)

        SearchAlgorithm[] algorithms = new SearchAlgorithm[] {
//                new AStarSearch(),
//                new GreedySearch(),
                new UniformCostSearch()
        };

        for (SearchAlgorithm algorithm : algorithms) {
            List<Node> path = algorithm.solve(maze, start, goal);
            System.out.printf(
                    RESULT_TEMPLATE,
                    algorithm.getClass().getSimpleName(),
                    algorithm.getVisitedNodes().size(),
                    path.size()
            );
            algorithm.printPath(path);
            reportMaker.createReport(start, goal, maze, path, algorithm.getClass().getSimpleName(), algorithm.getVisitedNodes().size());
        }
    }
}
