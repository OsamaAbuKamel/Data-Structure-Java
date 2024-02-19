public interface MaxHeapable<T extends Comparable<T>> extends Iterable<T> {
    void insert(T data);
    T deleteMax();
    T getMax();
    void clear();
    boolean isEmpty();
    int getSize();
}
