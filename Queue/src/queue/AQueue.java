package queue;

public class AQueue<T> implements Queueable<T> {
    private T[] items;
    private int front = -1;
    private int back = -1;
    private static int MAX_QUEUE = 11;
    private int count = 0;

    public AQueue(int size) {
        items = (T[]) new Object[MAX_QUEUE];
        MAX_QUEUE = size;
    }

    @Override
    public void enqueue(T data) {
        if (count < MAX_QUEUE) {
            back = ++back % MAX_QUEUE;
            items[back] = data;
            ++count;
            if (count == 1) {
                front = back;
            }
        } else {
            throw new IllegalStateException("Queue is full");
        }
    }

    @Override
    public T dequeue() {
        if (count > 0) {
            T data = items[front];
            front = ++front % MAX_QUEUE;
            --count;
            if (count == 0) {
                front = back = -1;
            }
            return data;
        }
        return null;
    }

    @Override
    public T getFront() {
        if (!isEmpty()) {
            return items[front];
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public T pop() {
        if (count > 0) {
            T data = items[back];
            back = ++back % MAX_QUEUE;
            --count;
            if (count == 0) {
                front = back = -1;
            }
            return data;
        }
        return null;
    }

    @Override
    public void clear() {
        front = back = -1;
        count = 0;
    }
}
