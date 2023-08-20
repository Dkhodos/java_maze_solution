import java.io.*;
import java.util.*;

/**
 * MazeGenerator is responsible for generating a maze based on the specified size and difficulty.
 * The generated maze is then saved to a file.
 */
public class MazeGenerator {

    /**
     * Enum representing the difficulty levels for maze generation.
     */
    enum Difficulty {
        Easy,
        Medium,
        Hard
    }

    private final int size;
    private final Random random;
    private final UniformCostSearch uch;
    private final Difficulty difficulty;

    /**
     * Constructs a MazeGenerator with the specified size and difficulty.
     *
     * @param size The size of the maze to be generated.
     * @param difficulty The difficulty level for maze generation.
     */
    public MazeGenerator(int size, Difficulty difficulty) {
        this.size = size;
        this.random = new Random();
        this.uch = new UniformCostSearch();
        this.difficulty = difficulty;
    }

    /**
     * Generates a maze based on the specified size and difficulty.
     * Continuously generates mazes until a solvable one is found.
     */
    public void generate() {
        while (true) {
            Node[][] nodes = new Node[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    boolean isObstacle = random.nextDouble() <= getWallProbability();
                    if (i == 0 && j == 0) isObstacle = false;
                    if (i == size - 1 && j == size - 1) isObstacle = false;

                    nodes[i][j] = new Node(i, j, isObstacle);
                }
            }

            Maze maze = new Maze(size, nodes);
            SearchResult result = uch.solve(maze, maze.nodes()[0][0], maze.nodes()[size - 1][size - 1]);
            if (!result.path().isEmpty()) {
                toFile(maze);
                return;
            }
        }
    }

    /**
     * Determines the probability of a cell being a wall based on the difficulty level.
     *
     * @return The probability of a cell being a wall.
     */
    private double getWallProbability() {
        return switch (difficulty) {
            case Medium -> 0.4;
            case Hard -> 0.5;
            case Easy -> 0.2;
        };
    }

    /**
     * Writes the generated maze to a file.
     *
     * @param maze The generated maze.
     */
    private void toFile(Maze maze) {
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

    /**
     * Main method to test the MazeGenerator.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator(30, Difficulty.Easy);
        generator.generate();
    }
}
