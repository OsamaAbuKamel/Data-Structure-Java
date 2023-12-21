package queue;

public class CQueue<T> implements Queueable<T> {
    private T queueArr[];
    private int front;
    private int rear;
    private int size;

    public CQueue(int size) {
        this.size = size;
        front = -1;
        rear = -1;
        queueArr = (T[]) new Object[size];
    }

    private boolean isFull() {
        if (front == 0 && rear == size - 1) {
            return true;
        }
        if (front == rear + 1) {
            return true;
        }
        return false;
    }

    @Override
    public void enqueue(T data) {
        if (isFull()) {
            System.out.println("Queue is full!!!");
        } else {
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            queueArr[rear] = data;
        }
    }

    @Override
    public T dequeue() {
        T data;
        if (isEmpty()) {
            System.out.println("Queue is Empty!!!");
            return null;
        } else {
            data = queueArr[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % size;
            }
        }
        return data;
    }

    @Override
    public T getFront() {
        return queueArr[front];
    }

    @Override
    public boolean isEmpty() {
        return front == -1;
    }

    @Override
    public void clear() {
        front = -1;
        rear = -1;
    }

    

    @Override
    public T pop() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot pop element.");
            return null;
        } else {
            T item = queueArr[front];
            if (front == rear) {
                front = rear = -1;
            } else {
                rear = (rear) % size;
            }
            System.out.println("Popped " + item + " from index " + front);
            return queueArr[rear];
        }
    }

    public static void main(String[] args) {
        CQueue<Integer> q = new CQueue<Integer>(5);
        // Fails because front = -1
        // q.dequeue();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        System.out.println(q.pop());
        System.out.println(q.dequeue());
        // // Fails to enqueue because front == 0 && rear == SIZE - 1
        // q.enqueue(6);

        // q.display();

        // int elem = q.dequeue();

        // if (elem != -1) {
        // System.out.println("Deleted Element is " + elem);
        // }
        // q.display();

        // q.enqueue(7);

        // q.display();

        // // Fails to enqueue because front == rear + 1
        // q.enqueue(8);
    }

}
