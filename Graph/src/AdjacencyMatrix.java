import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AdjacencyMatrix<V, E> implements Graph<V, E> {
    private Map<Integer, V> vertexValues;
    private E[][] adjacencyMatrix;
    private int vertexCount;
    private boolean undirected;

    public AdjacencyMatrix(boolean undirected, int numVertices) {
        this.vertexValues = new HashMap<>();
        this.adjacencyMatrix = (E[][]) new Object[numVertices][numVertices];
        this.vertexCount = 0;
        this.undirected = undirected;
    }

    @Override
    public int addVertex() {
        if (vertexCount >= adjacencyMatrix.length) {
            throw new IndexOutOfBoundsException("Cannot add more vertices");
        }
        int newVertexId = vertexCount++;
        vertexValues.put(newVertexId, null);
        return newVertexId;
    }

    @Override
    public void removeVertex(int id) {
        if (vertexValues.containsKey(id)) {
            vertexValues.remove(id);
            for (int i = 0; i < vertexCount; i++) {
                adjacencyMatrix[i][id] = null;
                adjacencyMatrix[id][i] = null;
            }
            vertexCount--;
        }

    }

    @Override
    public void addEdge(int source, int target) {
        addEdge(source, target, null);
    }

    @Override
    public void addEdge(int source, int target, E value) {
        if (vertexValues.containsKey(source) && vertexValues.containsKey(target)) {
            adjacencyMatrix[source][target] = value;
            if (undirected) {
                adjacencyMatrix[target][source] = value;
            }
        }
    }

    @Override
    public void removeEdge(int source, int target) {
        if (vertexValues.containsKey(source) && vertexValues.containsKey(target)) {
            adjacencyMatrix[source][target] = null;
            if (undirected) {
                adjacencyMatrix[target][source] = null;
            }
        }
    }

    @Override
    public boolean isAdjacent(int source, int target) {
        return adjacencyMatrix[source][target] != null;

    }

    @Override
    public LinkedList<Integer> getNeighbors(int source) {
        LinkedList<Integer> neighbors = new LinkedList<>();
        for (int i = 0; i < vertexCount; i++) {
            if (adjacencyMatrix[source][i] != null) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    @Override
    public void setVertexValue(int vertex, V value) {
        vertexValues.put(vertex, value);

    }

    @Override
    public V getVertexValue(int vertex) {
        return vertexValues.get(vertex);

    }

    @Override
    public void setEdgeValue(int source, int target, E value) {
        if (vertexValues.containsKey(source) && vertexValues.containsKey(target)) {
            adjacencyMatrix[source][target] = value;
            if (undirected) {
                adjacencyMatrix[target][source] = value;
            }
        }
    }

    @Override
    public E getEdgeValue(int source, int target) {
        if (vertexValues.containsKey(source) && vertexValues.containsKey(target)) {
            return adjacencyMatrix[source][target];
        }
        return null;
    }

    @Override
    public boolean isUndirected() {
        return undirected;

    }

    @Override
    public List<Integer> getAllVertices() {
        return new ArrayList<>(vertexValues.keySet());

    }

    @Override
    public int maxVertexID() {
        return vertexCount - 1;

    }

    @Override
    public List<Integer> bfs(int startVertex) {
        List<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(startVertex);
        queue.add(startVertex);
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            for (Integer neighbor : getNeighbors(currentVertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }

            }
        }
        return visited;
    }

    @Override
    public List<Integer> dfs(int startVertex) {
        List<Integer> visited = new ArrayList<>();
        dfsRecursive(startVertex, visited);
        return visited;
    }

    private void dfsRecursive(int startVertex, List<Integer> visited) {
        visited.add(startVertex);
        for (Integer neighbor : getNeighbors(startVertex)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        // Create an undirected graph with 5 vertices
        AdjacencyMatrix<String, Integer> graph = new AdjacencyMatrix<>(true, 5);

        // Test all methods
        int v1 = graph.addVertex();
        int v2 = graph.addVertex();
        int v3 = graph.addVertex();

        graph.addEdge(v1, v2, 5);
        graph.addEdge(v1, v3, 2);

        // add values for vertex
        graph.setVertexValue(v1, "Vertex 1");
        graph.setVertexValue(v2, "Vertex 2");
        graph.setVertexValue(v3, "Vertex 3");

        // print values for vertex
        System.out.println("Vertex 1 value: " + graph.getVertexValue(v1));
        System.out.println("Vertex 2 value: " + graph.getVertexValue(v2));
        System.out.println("Vertex 3 value: " + graph.getVertexValue(v3));

        System.out.println("Neighbors of v1: " + graph.getNeighbors(v1));
        System.out.println("Edge value between v1 and v2: " + graph.getEdgeValue(v1, v2));
        System.out.println("All vertices: " + graph.getAllVertices());
        System.out.println("Max vertex ID: " + graph.maxVertexID());
        List<Integer> bfs = graph.bfs(v1);
        System.out.println("BFS: " + bfs);
        // Add more tests as needed

    }

}
