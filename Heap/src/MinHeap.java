import java.util.Iterator;
public class MinHeap<T extends Comparable<T>> implements MinHeapable<T> {
    T[] heap;
    int size = 0;
    
    public MinHeap() {
        this(10);
    }
    
    public MinHeap(int size) {
        heap = (T[]) new Comparable[size];
    }
    
    @Override
    public void insert(T data) {
        heap[++size] = data;
        swim(size);
    }
    
    @Override
    public T delete() {
        T data = heap[1];
        exchange(size--, 1);
        sink(1);
        heap[size + 1] = null;
        return data;
    }
    
    @Override
    public T getMin() {
        return heap[1];
    }
    
    @Override
    public void clear() {
        heap = (T[]) new Comparable[heap.length];
    }
    
    @Override
    public boolean isEmpty() {
        return heap[1] == null;
    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    private void swim(int i) {
        while (i > 1 && greater(i / 2, i)) {
            exchange(i / 2, i);
            i /= 2;
        }
    }
    
    private void exchange(int i, int j) {
        T d = heap[i];
        heap[i] = heap[j];
        heap[j] = d;
    }
    
    private boolean greater(int i, int j) {
        if (heap[j] == null)
            return false;
        
        return heap[i].compareTo(heap[j]) > 0;
    }
    
    private void sink(int i) {
        while (i * 2 <= size) {
            int k = i * 2;
            if (greater(k, k + 1))
                k++;
            
            if (!greater(i, k))
                break;
            
            exchange(i, k);
            i = k;
        }
    }
    public void heapSort() {
        int n = size;
        // Build the heap (rearrange array)
        for (int i = n / 2; i >= 1; i--) {
            sink(i);
        }
        // One by one extract an element from the heap
        for (int i = n; i > 1; i--) {
            // Move the current root (minimum element) to the end
            exchange(1, i);
            sink(1);
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i <= size; i++) {
            s += heap[i] + " ";
        }
        return s;
    }
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
