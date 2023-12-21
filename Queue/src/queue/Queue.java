package queue;

public class Queue<T> implements Queueable<T>{
    private T items[];
    private int front;
    private int rear;
    private int count;

    public Queue(int size) { 
        items = (T[]) new Object[size];
        front = 0;
        rear = -1;
        count = 0;
    }

    @Override
    public void enqueue(T data) {
      

    }

    @Override
    public T dequeue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeue'");
    }

    @Override
    public T getFront() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFront'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public T pop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }
    
}
