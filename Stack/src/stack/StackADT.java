package stack;
public interface StackADT<T extends Comparable<T>> extends Iterable<T> {
    void push(T data);
    T pop();
    T peek();
    int length();
    void clear();
    boolean isEmpty();
}
