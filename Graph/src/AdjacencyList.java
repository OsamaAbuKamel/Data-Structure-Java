import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class AdjacencyList<V, E> implements Graph<V, E> {
    private Map<Integer, V> vertexValues;
    private Map<Integer, List<Edge<V, E>>> adjacencyList;
    private int vertexCount;
    private boolean undirected;

    public AdjacencyList(boolean undirected) {
        this.vertexValues = new HashMap<>();
        this.adjacencyList = new HashMap<>();
        this.vertexCount = 0;
        this.undirected = undirected;
    }

    @Override
    public int addVertex() {
        int newVertexId = vertexCount++;
        vertexValues.put(newVertexId, null);
        adjacencyList.put(newVertexId, new LinkedList<>());
        return newVertexId;
    }

    @Override
    public void removeVertex(int id) {
        if (vertexValues.containsKey(id)) {
            vertexValues.remove(id);
            adjacencyList.remove(id); // Remove row
            // Remove column entries
            for (List<Edge<V, E>> neighbors : adjacencyList.values()) {
                neighbors.removeIf(edge -> edge.getTarget() == id);
            }
            vertexCount--;
        }
    }

    @Override
    public void addEdge(int source, int target) {
        addEdge(source, target, null); // No edge value by default
    }

    @Override
    public void addEdge(int source, int target, E value) {
        if (vertexValues.containsKey(source) && vertexValues.containsKey(target)) {
            adjacencyList.get(source).add(new Edge<>(source, target, value));
            if (undirected) {
                adjacencyList.get(target).add(new Edge<>(target, source, value)); // For undirected graphs
            }
        }
    }

    @Override
    public void removeEdge(int source, int target) {
        if (vertexValues.containsKey(source) && vertexValues.containsKey(target)) {
            adjacencyList.get(source).removeIf(edge -> edge.getTarget() == target);
            if (undirected) {
                adjacencyList.get(target).removeIf(edge -> edge.getTarget() == source);
            }
        }
    }

    @Override
    public boolean isAdjacent(int source, int target) {
        return adjacencyList.get(source).stream().anyMatch(edge -> edge.getTarget() == target);
    }

    @Override
    public LinkedList<Integer> getNeighbors(int source) {
        return adjacencyList.get(source).stream().map(Edge::getTarget)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public void setVertexValue(int vertex, V value) {
        if (vertexValues.containsKey(vertex)) {
            vertexValues.put(vertex, value);
        }
    }

    @Override
    public V getVertexValue(int vertex) {
        return vertexValues.get(vertex);
    }

    @Override
    public void setEdgeValue(int source, int target, E value) {
        if (vertexValues.containsKey(source) && vertexValues.containsKey(target)) {
            adjacencyList.get(source).stream().filter(edge -> edge.getTarget() == target).findFirst()
                    .ifPresent(edge -> edge.setValue(value));
            if (undirected) {
                adjacencyList.get(target).stream().filter(edge -> edge.getTarget() == source).findFirst()
                        .ifPresent(edge -> edge.setValue(value));
            }
        }
    }

    @Override
    public E getEdgeValue(int source, int target) {
        if (vertexValues.containsKey(source) && vertexValues.containsKey(target)) {
            // for (Edge<V, E> edge : adjacencyList.get(source)) {
            // if (edge.getTarget() == target) {
            // return edge.getValue();
            // }
            // }
            return adjacencyList.get(source).stream().filter(edge -> edge.getTarget() == target).findFirst()
                    .map(Edge::getValue).orElse(null);
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
        return vertexCount - 1; // Assuming contiguous IDs from 0
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
        AdjacencyList<String, Integer> graph = new AdjacencyList<>(true);
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
        System.out.println("Is v1 adjacent to v2? " + graph.isAdjacent(v1, v2));
        System.out.println("Neighbors of v1: " + graph.getNeighbors(v1));
        System.out.println("Edge value between v1 and v2: " + graph.getEdgeValue(v1, v2));
        System.out.println("All vertices: " + graph.getAllVertices());
        System.out.println("Max vertex ID: " + graph.maxVertexID());
        List<Integer> bfs = graph.bfs(v1);
        System.out.println("BFS: " + bfs);
        List<Integer> dfs = graph.dfs(v1);
        System.out.println("DFS: " + dfs);
    }
}
