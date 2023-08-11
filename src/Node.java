public record Node(int x, int y, boolean isObstacle) {

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

}