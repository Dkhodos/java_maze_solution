import java.io.*;
import java.util.*;

public class MazeGenerator {
    enum Difficulty {
        Easy,
        Medium,
        Hard
    }

    private final int size;
    private final Random random;
    private final UniformCostSearch uch;

    private final Difficulty difficulty;

    public MazeGenerator(int size, Difficulty difficulty) {
        this.size = size;
        this.random = new Random();
        this.uch = new UniformCostSearch();
        this.difficulty = difficulty;
    }

    public void generate(){
        while (true){
            Node [][] nodes = new Node[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    boolean isObstacle = random.nextDouble() <= getWallProbability();
                    if(i == 0 && j == 0) isObstacle = false;
                    if(i == size-1 && j == size-1) isObstacle = false;

                    nodes[i][j] = new Node(i, j, isObstacle, 0);
                }
            }

            Maze maze = new Maze(size, nodes);
            maze.setHeuristicDistance(nodes[size-1][size-1]);

            SearchResult result = uch.solve(maze, maze.nodes()[0][0], maze.nodes()[size-1][size-1]);
            if(!result.path().isEmpty()) {
                toFile(maze);
                return;
            }
        }
    }

    private double getWallProbability(){
        return switch (difficulty) {
            case Medium -> 0.4;
            case Hard -> 0.5;
            case Easy -> 0.2;
        };
    }

    private void toFile(Maze maze){
        String rootDir = System.getProperty("user.dir");
        String fullPath = rootDir + "/data/maze.generated.txt";
        File file = new File(fullPath);

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(size); // Writes the size at the beginning
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    String value = maze.nodes()[i][j].isObstacle() ? "1" : "0";
                    writer.print(value + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator(30, Difficulty.Easy);
        generator.generate();
    }
}
