import java.io.*;

/**
 * The MazeParser class is responsible for reading and parsing maze data from a file.
 * The maze file is expected to have the size of the maze as the first line, followed by rows of 0s and 1s,
 * where 0 represents a walkable path and 1 represents an obstacle.
 */
class MazeParser {
    private static final String OBSTACLE = "1";
    private static final Logger logger = new Logger("MazeParser");

    /**
     * Parses a maze from the given file path.
     *
     * @param filePath The path to the maze file.
     * @return A Maze object constructed from the file data, or null if an error occurs.
     */
    public Maze parse(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int size = readSize(reader);
            Node[][] nodes = readNodes(reader, size);
            return new Maze(size, nodes);
        } catch (IOException | NumberFormatException e) {
            logger.error("Error while trying to parse file in: " + filePath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads the size of the maze from the first line of the file.
     *
     * @param reader The BufferedReader to read from.
     * @return The size of the maze.
     * @throws IOException If there's an error reading from the file.
     * @throws NumberFormatException If the size is not a valid integer.
     */
    private int readSize(BufferedReader reader) throws IOException, NumberFormatException {
        String sizeLine = reader.readLine();
        if (sizeLine == null) {
            logger.error("File is empty or does not contain a size.");
            throw new IllegalArgumentException("File is empty or does not contain a size.");
        }

        try {
            return Integer.parseInt(sizeLine.trim());
        } catch (NumberFormatException e) {
            logger.error("File does not contain a valid size.");
            throw new IllegalArgumentException("File does not contain a valid size.");
        }
    }

    /**
     * Reads the nodes of the maze from the file.
     *
     * @param reader The BufferedReader to read from.
     * @param size The size of the maze.
     * @return A 2D array of Nodes representing the maze.
     * @throws IOException If there's an error reading from the file.
     */
    private Node[][] readNodes(BufferedReader reader, int size) throws IOException {
        Node[][] nodes = new Node[size][size];
        for (int x = 0; x < size; x++) {
            String[] line = reader.readLine().trim().split(" ");
            if (line.length != size) {
                logger.error("Incorrect number of columns in row " + x);
                throw new IllegalArgumentException("Incorrect number of columns in row " + x);
            }
            for (int y = 0; y < size; y++) {
                nodes[x][y] = new Node(x, y, OBSTACLE.equals(line[y].trim()));
            }
        }
        return nodes;
    }
}
