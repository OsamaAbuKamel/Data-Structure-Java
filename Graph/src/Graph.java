import java.util.LinkedList;
import java.util.List;

public interface Graph<V, E> {
    int addVertex();

    void removeVertex(int id);

    void addEdge(int source, int target);

    public void addEdge(int source, int target, E value);

    void removeEdge(int source, int target);

    boolean isAdjacent(int source, int target);

    LinkedList<Integer> getNeighbors(int source);

    void setVertexValue(int vertex, V value);

    V getVertexValue(int vertex);

    void setEdgeValue(int source, int target, E value);

    E getEdgeValue(int source, int target);

    boolean isUndirected();

    List<Integer> getAllVertices();

    int maxVertexID();

    public List<Integer> bfs(int startVertex);

    public List<Integer> dfs(int startVertex);

}
