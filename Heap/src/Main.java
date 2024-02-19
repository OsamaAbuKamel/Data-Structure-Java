public class Main {
    public static void main(String[] args) {
        System.out.println("----------------MAX HEAP----------------");
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(30);
        maxHeap.insert(40);
        maxHeap.insert(50);
        System.out.println("Max heap elements: " + maxHeap.toString());
        System.out.println("----------------HEAPSORT----------------");
        MaxHeap.sort(maxHeap.heap);
        System.out.println("Sorted max heap elements: " + maxHeap.toString());
        System.out.println("----------------MIN HEAP----------------");
        MinHeap<Integer> minHeap = new MinHeap<>();
        minHeap.insert(50);
        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(30);
        minHeap.insert(40);
        System.out.println("Min heap elements: " + minHeap.toString());
        System.out.println("----------------HEAPSORT----------------");
        minHeap.heapSort();
        System.out.println("Sorted min heap elements: " + minHeap.toString());
    }
}