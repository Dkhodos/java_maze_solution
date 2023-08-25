/**
 * The Node class represents a cell in the maze.
 * Each node has an x and y coordinate and can either be a walkable path or an obstacle.
 */
public record Node(int x, int y, boolean isObstacle) {
    public Node(int x, int y){
        this(x, y, false);
    }

    /**
     * Returns a string representation of the node in the format "(x, y)".
     *
     * @return A string representing the node's coordinates.
     */

    @Override
    public String toString() {
        return String.format("(%d, %d)", x + 1, y + 1);
    }
}