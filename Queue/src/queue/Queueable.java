package queue;

public interface Queueable<T> {
    void enqueue(T data);

    T dequeue();

    T getFront();

    boolean isEmpty();
    public T pop();

    void clear();
}
