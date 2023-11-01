//This class is used to create a double linked list.
public class DNode <T extends Comparable<T>>{
    //The data stored in the node
    T data;
    //The next node in the list
    DNode<T> next;
    //The previous node in the list
    DNode<T> prev;
    //Constructor for the node, takes in the data to be stored in the node
    public DNode(T data) { this.data = data; }
    //Returns the data stored in the node
    public T getData() { return data; }
    //Returns the next node in the list
    public DNode<T> getNext () { return next; }
    //Returns the previous node in the list
    public DNode<T> getPrev () { return prev; }
    //Sets the next node in the list
    public void setNext(DNode<T> next) { this.next = next; }
    //Sets the previous node in the list
    public void setPrev(DNode<T> prev) { this.prev = prev; }
    //Returns the string representation of the data stored in the node
    public String toString() { return this.data.toString(); }
}