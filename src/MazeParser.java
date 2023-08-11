import java.io.*;

class MazeParser {
    private static final String OBSTACLE = "1";
    private static final Logger logger = new Logger("MazeParser");

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

    private int readSize(BufferedReader reader) throws IOException, NumberFormatException {
        String sizeLine = reader.readLine();
        if (sizeLine == null) {
            logger.error("File is empty or does not contain a size.");
            throw new IllegalArgumentException("File is empty or does not contain a size.");
        }

        try {
            return Integer.parseInt(sizeLine.trim());
        } catch (NumberFormatException e) {
            logger.error("File  does not contain a size.");
            throw new IllegalArgumentException("File  does not contain a size.");
        }
    }

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
