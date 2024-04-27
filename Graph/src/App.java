public class App {
    public static void main(String[] args) throws Exception {
        Graph<Character> graph = new UnweightedGraph<>();
        graph.addVertex('U');
        graph.addVertex('V');
        int indexForU = graph.getIndex('U');
        int indexForV = graph.getIndex('V');
        System.out.println("indexForU is " + indexForU);
        System.out.println("indexForV is " + indexForV);
        graph.addEdge(indexForU, indexForV);
        System.out.println("Degree of U is " +
                graph.getDegree(indexForU));
        System.out.println("Degree of V is " +
                graph.getDegree(indexForV));
    }
}
