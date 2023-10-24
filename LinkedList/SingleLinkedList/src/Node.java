// Node class for the linked list
public class Node<T extends Comparable<T>> {
    T data; // Data held by the node
    Node next; // Reference to the next node in the list

    // Constructor: Initializes the node with the given data
    public Node(T data) {
        this.data = data;
    }

    // Returns the data of the node
    public T getData() {
        return this.data;
    }

    // Sets the data of the node
    public void setData(T data) {
        this.data = data;
    }

    // Returns the next node
    public Node getNext() {
        return this.next;
    }

    // Sets the next node
    public void setNext(Node next) {
        this.next = next;
    }

    // Returns a string representation of the node
    public String toString() {
        return " " + String.valueOf(this.data);
    }
}
