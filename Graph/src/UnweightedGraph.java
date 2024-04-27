import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UnweightedGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected UnweightedGraph() {
    }

    protected UnweightedGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++) {
            addVertex(vertices[i]);
            createAdjacencyLists(edges, vertices.length);
        }
    }

    protected UnweightedGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
            createAdjacencyLists(edges, vertices.size());
        }
    }

    protected UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V) (Integer.valueOf(i))); // vertices is {0, 1, . . . }
        createAdjacencyLists(edges, numberOfVertices);
    }

    protected UnweightedGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V) (Integer.valueOf(i))); // vertices is {0, 1, . . . }
        createAdjacencyLists(edges, numberOfVertices);
    }

    private void createAdjacencyLists(int[][] edges, int length) {
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }

    }

    private void createAdjacencyLists(List<Edge> edges, int size) {
        for (Edge edge : edges) {
            addEdge(edge.u, edge.v);
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public List<V> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge edge : neighbors.get(index)) {
            result.add(edge.v);
        }
        return (List<V>) result;

    }

    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override
    public void printEdges() {
        for (int i = 0; i < neighbors.size(); i++) {
            System.out.println(getVertex(i) + "(" + i + "): ");
            for (Edge edge : neighbors.get(i)) {
                System.out.println("(" + getVertex(edge.u) + ", " + getVertex(edge.v) + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    @Override
    public boolean addEdge(Edge e) {
        if (e.u < 0 || e.u > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.u);
        }
        if (e.v < 0 || e.v > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.v);
        }
        if (!neighbors.get(e.u).contains(e)) {
            neighbors.get(e.u).add(e);
            return true;
        } else
            return false;
    }

    @Override
    public boolean remove(V v) {
        if (vertices.contains(v)) {
            int index = getIndex(v);
            vertices.remove(index);
            for (List<Edge> list : neighbors) {
                list.remove(new Edge(index, list.get(index).v));
            }
            neighbors.remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(int u, int v) {
        Edge e = new Edge(u, v);
        if (neighbors.get(u).contains(e)) {
            neighbors.get(u).remove(e);
            return true;
        } else {
            return false;
        }
    }

    public class SearchTree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        public SearchTree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int i) {
            return parent[i];
        }

        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }

        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();
            do {
                path.add(vertices.get(index));
                index = parent[index];
            } while (index != -1);
            return path;
        }

        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.print("A path from " + vertices.get(root) + " to " + vertices.get(index) + ": ");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.println(path.get(i) + " ");
            }
        }

        public void printTree() {
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    // Display an edge
                    System.out.print("(" + vertices.get(parent[i]) + ", " +
                            vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public UnweightedGraph<V>.SearchTree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; // Initialize parent[] as -1

        boolean [] isVisited = new boolean[vertices.size()];
        // Call the recursive helper function to store the DFS traversal
        dfs(v, parent, searchOrder, isVisited);

        // Return a search tree
        return new SearchTree(v, parent, searchOrder);
    }

    private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(v);
        isVisited[v] = true;
        
        for (Edge edge : neighbors.get(v)) {
            int w = edge.v;
            
            if (!isVisited[w]) {
                parent[w] = v;
                dfs(w, parent, searchOrder, isVisited);
            }
        }
    }

    @Override
    public UnweightedGraph<V>.SearchTree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) 
            parent[i] = -1;
        
            LinkedList<Integer> queue = new LinkedList<>();
            boolean[] isVisited = new boolean[vertices.size()];
            queue.offer(v);
            isVisited[v] = true;
            
            while (!queue.isEmpty()) {
                int u = queue.poll();
                searchOrder.add(u);
                
                for (Edge edge : neighbors.get(u)) {
                    int w = edge.v;
                    if (!isVisited[w]) {
                        queue.offer(w);
                            parent[w] = u;
                            isVisited[w] = true;
                        }
                }
            }
            
        return new SearchTree(v, parent, searchOrder);
    }
}
