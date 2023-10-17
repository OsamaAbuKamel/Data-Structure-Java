public class Node<T extends Comparable<T>> {
    T data;
    Node<T> next;

    // Constructor to initialize the Node with data
    public Node(T data) {
        this.data = data;
    }

    // Method to set the data of the Node
    public void setData(T data) {
        this.data = data;
    }

    // Method to get the data of the Node
    public T getData() {
        return data;
    }

    // Method to get the next Node
    public Node<T> getNext() {
        return next;
    }

    // Method to set the next Node
    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return  "data= " + data;
    }
}
