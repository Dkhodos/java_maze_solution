
public class Node {

    private final int x;
    private final int y;
    private final boolean isObstacle;
    private int heuristicDistance;

    public Node(int x, int y, boolean isObstacle, int heuristicDistance) {
        this.x = x;
        this.y = y;
        this.isObstacle = isObstacle;
        this.heuristicDistance = heuristicDistance;
    }

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public void setHeuristicDistance(Node goal){
        heuristicDistance = (Math.abs(x - goal.x) + Math.abs(y - goal.y));
    }

    public int getHeuristicDistance(){
        return heuristicDistance;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isObstacle(){
        return isObstacle;
    }

}