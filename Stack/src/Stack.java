public interface Stack<T extends Comparable<T>> extends Iterable<T> {
    void clear();
    boolean puh(T data);
    T pop();
    T peek();
    int length();
    boolean isEmpty();
}
