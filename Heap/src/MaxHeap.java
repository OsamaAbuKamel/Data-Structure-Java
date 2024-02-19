import java.util.Iterator;

public class MaxHeap<T extends Comparable<T>> implements MaxHeapable<T> {
    T[] heap;
    int size = 0;

    public MaxHeap() {
        this(10);
    }

    public MaxHeap(int size) {
        heap = (T[]) new Comparable[size];
    }

    @Override
    public void insert(T data) {
        heap[++size] = data;
        swim(size);
    }

    @Override
    public T deleteMax() {
        T data = heap[1];
        exchange(size--, 1);
        sink(1);
        heap[size + 1] = null;
        return data;
    }

    @Override
    public T getMax() {
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
        while (i > 1 && less(i / 2, i)) {
            exchange(i / 2, i);
            i /= 2;
        }
    }

    private void exchange(int i, int j) {
        T d = heap[i];
        heap[i] = heap[j];
        heap[j] = d;
    }

    private boolean less(int i, int j) {
        if (heap[j] == null)
            return false;
        return heap[i].compareTo(heap[j]) < 0;
    }

    private void sink(int i) {
        while (i * 2 <= size) {
            int k = i * 2;
            if (less(k, k + 1))
                k++;
            if (!less(i, k))
                break;
            exchange(i, k);
            i = k;
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

    private static void sink(Comparable[] arr, int i, int n) {
        while (i * 2 + 1 <= n) {
            int k = i * 2 + 1;
            if (k + 1 < n && less(arr, k, k + 1))
                k++;
            if (less(arr, i, k))
                exchange(arr, i, k);
            i = k;
        }
    }

    private static void exchange(Comparable[] arr, int i, int k) {
        Comparable d = arr[i];
        arr[i] = arr[k];
        arr[k] = d;
    }

    private static boolean less(Comparable[] arr, int k, int i) {
        return arr[k].compareTo(arr[i]) < 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private void sink(int i, int n) {
        while (i * 2 <= n) {
            int k = i * 2;
            if (k + 1 <= n && less(k, k + 1)) {
                k++;
            }
            if (!less(i, k)) {
                break;
            }
            exchange(i, k);
            i = k;
        }
    }

    public void heapSort() {
        int n = size;
        for (int i = n/2; i >=1; i--) {
            sink(i, n);
        }
        while (n>1) {
            exchange(1, n);
            sink(1, --n);
        }
        // int n = size;
        // // Build max heap
        // for (int i = n / 2; i >= 1; i--) {
        //     sink(i, n);
        // }
        // // Extract elements from the heap in decreasing order
        // while (n > 1) {
        //     exchange(1, n);
        //     n--;
        //     sink(1, n);
        // }
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length - 1;
        for (int i=n / 2; i >= 1; i--)
            sink(arr, i, n);
            
        while (n > 1) {
            exchange(arr, 1, n);
            sink(arr, 1, --n);
        }
    }
}