import java.util.Iterator;

public class MHeap<T extends Comparable<T>> implements Iterable<T> {
    private T[] heap;
    private int size;
    private int maxSize;

    public MHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        heap = (T[]) new Comparable[this.maxSize + 1];
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    private void swap(int firstPos, int secondPos) {
        T tmp;
        tmp = heap[firstPos];
        heap[firstPos] = heap[secondPos];
        heap[secondPos] = tmp;
    }

    private void maxHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (heap[pos].compareTo(heap[leftChild(pos)]) < 0 || heap[pos].compareTo(heap[rightChild(pos)]) < 0) {
                if (heap[leftChild(pos)].compareTo(heap[rightChild(pos)]) > 0) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }

    public void insert(T element) {
        heap[++size] = element;
        int current = size;

        while (heap[current].compareTo(heap[parent(current)]) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void buildMaxHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            maxHeapify(pos);
        }
    }

    public T delete() {
        T popped = heap[1];
        heap[1] = heap[size--];
        maxHeapify(1);
        return popped;
    }

    public void heapSort() {
        buildMaxHeap();
        for (int i = size; i > 1; i--) {
            swap(1, i);
            size--;
            maxHeapify(1);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 1;

            @Override
            public boolean hasNext() {
                return currentIndex <= size;
            }

            @Override
            public T next() {
                return heap[currentIndex++];
            }
        };

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= size; i++) {
            sb.append(heap[i]);
            sb.append(", ");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<>(3); // Increase the size to 3
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
       System.out.println(heap);
    }
    
    }
