import java.util.ArrayList;
import java.util.List;

public record Maze(int size, Node[][] nodes) {

    public List<Node> getNeighbors(Node node){
        int x = node.x();
        int y = node.y();

        List<Node> neighbors = new ArrayList<>();

        if(!isOutOfBound(x+ 1, y)){
            neighbors.add(nodes[x+ 1][y]);
        }

        if(!isOutOfBound(node.x(), y + 1)){
            neighbors.add(nodes[node.x()][y + 1]);
        }

        if(!isOutOfBound(x- 1, y)){
            neighbors.add(nodes[x- 1][y]);
        }

        if(!isOutOfBound(x, y - 1)){
            neighbors.add(nodes[x][y - 1]);
        }

        return neighbors;
    }

    private boolean isOutOfBound(int x, int y){
        if(x < 0 || y < 0){
            return true;
        }

        if(y > size -1 || x >= size -1){
            return true;
        }

        return nodes[x][y].isObstacle();
    }
}