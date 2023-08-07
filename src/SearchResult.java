import java.util.List;
import java.util.Set;

public record SearchResult(List<Node> path, Set<Node> visitedNodes) {}
