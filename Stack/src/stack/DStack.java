package stack;
import java.util.Iterator;

public class DStack<T extends Comparable<T>> implements StackADT<T> {
    // Array to store stack data
    private T[] data;
    // Number of elements in the stack
    private int size;
    // Actual capacity of the array
    private int capacity;

    public DStack(int initialCapacity) {
        // Validate initial capacity
        if (initialCapacity <= 1) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        // Set initial capacity
        this.capacity = initialCapacity;
        // Allocate array with initial capacity
        this.data = (T[]) new Comparable[capacity];
        // Initialize size to 0
        this.size = 0;
    }

    public DStack() {
        // Default initial capacity
        this(10);
    }

    @Override
    public void push(T t) {
        // Expand array if needed
        if (size >= capacity) {
            expandCapacity();
        }
        // Add element
        data[size++] = t;
    }

    // Double the capacity
    private void expandCapacity() {
        capacity *= 2;
        // Allocate larger array
        T[] newData = (T[]) new Comparable[capacity];
        // Copy data to new array
        System.arraycopy(data, 0, newData, 0, size);
        // Set data to new array
        data = newData;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T element = data[size - 1];
        size--;
        if (size < capacity / 4) {
            shrinkCapacity();
        }
        return element;
    }

    // private method to reduce the capacity of the array
    private void shrinkCapacity() {
        // divide the current capacity by 2
        capacity /= 2;
        // create a new array of the same type as the original array
        T[] newData = (T[]) new Comparable[capacity];
        // copy the contents of the original array into the new array
        System.arraycopy(data, 0, newData, 0, size);
        // set the original array to the new array
        data = newData;
    }

    @Override
    public T peek() {
        // Check if the stack is empty
        if (isEmpty()) {
            // Throw an exception if the stack is empty
            throw new IllegalStateException("Stack is empty");
        }
        // Return the element at the top of the stack
        return data[size - 1];
    }

    @Override
    public int length() {
        // return the size of the array
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        // Check if the size of the list is 0
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        // Create a new StackIterator object, passing in the data and size of this Stack
        return new StackIterator<>(data, size);
    }

    @Override
    public String toString() {
        String s = "";
        // Loop through the data array and add each element to the String
        for (int i = 0; i < size; i++) {
            s += data[i] + " ";
        }
        // Return the String
        return s;
    }
}