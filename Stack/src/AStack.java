import java.util.Iterator;

public class AStack<T extends Comparable<T>> implements StackADT<T> {
    private static final int DEFAULT_SIZE = 10;
    private T arr[];
    private int maxSize;
    private int top;

    public AStack() {
        this(DEFAULT_SIZE);
    }

    public AStack(int size) {
        maxSize = size;
        top = 0;
        arr = (T[]) new Comparable[size];
    }

    @Override
    public void clear() {
        top = 0;

    }

    @Override
    public void push(T data) {
        if (top >= maxSize)
            return;
        arr[top++] = data;
    }

    @Override
    public T pop() {
        if (top == 0)
            return null;
        return arr[--top];
    }

    @Override
    public T peek() {
        if (top == 0)
            return null;
        return arr[top - 1];
    }

    @Override
    public int length() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public Iterator<T> iterator() {
        int length = length();
        return new StackIterator<>(arr, length);
    }

    private static class StackIterator<T> implements Iterator<T> {
        private int index;
        private T[] arr;

        public StackIterator(T[] arr, int size) {
            this.arr = arr;
            index = size - 1;
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            return arr[index--];
        }
    }
}
