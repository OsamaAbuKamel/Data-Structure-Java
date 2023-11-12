
public class Node<T extends Comparable<T>> {
    // Data stored in the node
    T data;
    // Reference to the next node in the list
    int next;

    // Constructor that takes data and next node as parameters
    public Node(T data, int next) {
        this.data = data;
        this.next = next;
    }

    // Getter for the data stored in the node
    public T getData() {
        return this.data;
    }

    // Setter for the data stored in the node
    public void setData(T data) {
        this.data = data;
    }

    // Getter for the next node reference
    public int getNext() {
        return this.next;
    }

    // Setter for the next node reference
    public void setNext(int next) {
        this.next = next;
    }

    // Overridden toString method to display the node data and next node reference
    @Override
    public String toString() {
        return "[" + data + " , " + next + "]";
    }

}