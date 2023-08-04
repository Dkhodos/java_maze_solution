import java.util.ArrayList;
import java.util.List;

public record Maze(int size, Node[][] nodes) {

    public List<Node> getNeighbors(Node node){
        int x = node.x();
        int y = node.y();

        List<Node> neighbors = new ArrayList<>();
        if(node.isObstacle()){
            return neighbors;
        }

        if(isInBound(x+ 1, y)){
            neighbors.add(nodes[x+ 1][y]);
        }

        if(isInBound(node.x(), y + 1)){
            neighbors.add(nodes[node.x()][y + 1]);
        }

        if(isInBound(x- 1, y)){
            neighbors.add(nodes[x- 1][y]);
        }

        if(isInBound(x, y - 1)){
            neighbors.add(nodes[x][y - 1]);
        }

        return neighbors;
    }

    private boolean isInBound(int x, int y){
        if(x < 0 || y < 0){
            return false;
        }

        if(x > size - 1 || y > size - 1){
            return false;
        }

        return !(nodes[x][y].isObstacle());
    }
}