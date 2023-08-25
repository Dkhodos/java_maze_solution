import java.util.ArrayList;
import java.util.List;

/**
 * The Maze class represents a 2D grid of nodes.
 * Each node in the maze can either be a walkable path or an obstacle.
 */
public record Maze(int size, Node[][] nodes) {

    private static final int [][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * Retrieves the neighboring nodes of a given node in the maze.
     * This method considers the four cardinal directions (North, East, South, West) as possible neighbors.
     *
     * @param node The node for which neighbors are to be retrieved.
     * @return A list of neighboring nodes. If the node is an obstacle, an empty list is returned.
     */
    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        if (node.isObstacle()) {
            return neighbors;
        }

        for (int[] direction : directions) {
            int newX = node.x() + direction[0];
            int newY = node.y() + direction[1];

            if (isInBound(newX, newY)) {
                neighbors.add(nodes[newX][newY]);
            }
        }

        return neighbors;
    }

    /**
     * Retrieves the node at the specified coordinates (x, y) from the maze.
     *
     * @param x The x-coordinate of the desired node.
     * @param y The y-coordinate of the desired node.
     * @return The node at the specified coordinates.
     */
    public Node getNode(int x, int y){
        return nodes[x][y];
    }

    /**
     * Checks if a given x and y coordinate is within the bounds of the maze and is not an obstacle.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @return true if the coordinates are within bounds and not an obstacle, false otherwise.
     */
    private boolean isInBound(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }

        if (x > size - 1 || y > size - 1) {
            return false;
        }

        return !(nodes[x][y].isObstacle());
    }
}
