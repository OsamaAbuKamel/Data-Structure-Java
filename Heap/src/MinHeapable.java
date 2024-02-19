public interface MinHeapable<T extends Comparable<T>> extends Iterable<T> {
    void insert(T data);
    T delete();
    T getMin();
    void clear();
    boolean isEmpty();
    int getSize();
}
