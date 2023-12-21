package queue;

public class LQueue<T extends Comparable<T>> implements Queueable<T> {
    CDlinkedList<T> list;

    public LQueue() {
        list = new CDlinkedList<T>();
    }

    @Override
    public void enqueue(T data) {
        list.addAtLast(data);
    }
    @Override
    public T dequeue() {
        return list.removeFirst();
    }

    @Override
    public T getFront() {
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list = new CDlinkedList<>();
    }

    
public static void main(String[] args) {
        LQueue<Integer> queue = new LQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }

    @Override
    public T pop() {
        return null;
    }
}
