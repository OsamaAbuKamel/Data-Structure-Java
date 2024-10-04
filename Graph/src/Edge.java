public class Edge<V, E> {
    private int source;
    private int target;
    private E value;

    public Edge(int source, int target, E value) {
        this.source = source;
        this.target = target;
        this.value = value;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}