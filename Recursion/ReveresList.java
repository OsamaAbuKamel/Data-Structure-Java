public class ReveresList {
public static void main(String[] args) {
    //build linked list use node
    Node head = new Node(1);
    head.setNext(new Node(2));
    head.getNext().setNext(new Node(3));
    head.getNext().getNext().setNext(new Node(4));
    head.getNext().getNext().getNext().setNext(new Node(5));
    head = reveresList(head);
    
    //print linked list
    Node current = head;
    while (current != null) {
        System.out.print(current.data + " ");
        current = current.next;
    }
    
}
public static Node reveresList(Node node){
    if (node == null || node.next == null) {
        return node;
    }
    Node p = reveresList(node.next);
    node.next.next = node;
    node.next = null;
    return p;
}
private static class Node {
    int data;
    Node next;
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
    //set and get
    public void setNext(Node next) {
        this.next = next;
    }
    public Node getNext() {
        return next;
    }


    
}
}
