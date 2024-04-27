import java.util.List;

public interface Graph<V> {
    int getSize();
    List<V> getVertices();
    V getVertex(int index);
    int getIndex(V v);
    List<V> getNeighbors(int index);
    int getDegree(int v);
    void printEdges();
    void clear();
    boolean addVertex(V vertex);
    boolean addEdge(int u, int v);  
    boolean addEdge(Edge e);
    boolean remove(V v);
    boolean remove(int u, int v);
    UnweightedGraph<V>.SearchTree dfs(int v);
    UnweightedGraph<V>.SearchTree bfs(int v);



}
