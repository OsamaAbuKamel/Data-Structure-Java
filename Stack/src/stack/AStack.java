package stack;
import java.util.Iterator;

public class AStack<T extends Comparable<T>> implements StackADT<T> {
    private static final int DEFAULT_SIZE = 10;
    // Array to store the data
    private T data[];
    // Max size of the array
    private int maxSize;
    // Index of the top element
    private int top;

    public AStack() {
        // Create a new AStack with the default size
        this(DEFAULT_SIZE);
    }

    public AStack(int size) {
        // Set the maximum size of the stack
        maxSize = size;
        // Set the top of the stack to 0
        top = 0;
        // Create an array of Comparable objects with the given size
        data = (T[]) new Comparable[size];
    }

    @Override
    public void clear() {
        // Set the top of the stack to 0
        top = 0;
    }

    @Override
    public void push(T t) {
        // Check if the stack is full
        if (top >= maxSize)
            // Return if it is
            return;
        // Push the element onto the top of the stack
        data[top++] = t;
    }

    @Override
    public T pop() {
        // Check if the stack is empty
        if (top == 0)
            // Return null if it is
            return null;
        // Return the element at the top of the stack
        return data[--top];
    }

    @Override
    public T peek() {
        // Check if the top of the stack is 0
        if (top == 0)
            // Return null if the top is 0
            return null;
        // Return the data at the top of the stack minus 1
        return data[top - 1];
    }

    @Override
    public int length() {
        // Returns the length of the top stack element
        return top;
    }

    @Override
    public boolean isEmpty() {
        // Check if the top of the stack is 0
        return top == 0;
    }

    @Override
    public Iterator<T> iterator() {
        // Get the length of the data
        int length = length();
        // Return a new StackIterator with the data and the length
        return new StackIterator<>(data, length);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < top; i++) {
            s += data[i] + " ";
        }
        return s;
    }
}
