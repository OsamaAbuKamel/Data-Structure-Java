package stack;

public class CNode<T extends  Comparable<T>> {
    T data;
    // Reference to the next node in the list
    int next;
    
    // Constructor that takes data and next node as parameters
    public CNode(T data, int next) {
        this.data = data;
        this.next = next;
    }
}
