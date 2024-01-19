package Hash.LinkedList;

public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> next;
    
    public Node(T data) {
        this.data = data;
    }
    
    // Getter method to return the data stored in the node
    public T getData() {
        return this.data;
    }
    
    // Setter method to set the data stored in the node
    public void setData(T data) {
        this.data = data;
    }
    
    // Getter method to return the next node
    public Node<T> getNext() {
        return this.next;
    }
    
    // Setter method to set the next node
    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    // Overriding the toString method to return the data and the next node
    @Override
    public String toString() {
        return getData() + " , " + getNext();
    }
}