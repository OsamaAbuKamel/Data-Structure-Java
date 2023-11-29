package src;


public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        CDlinkedList<Integer> list = new CDlinkedList<>();
        list.addAtHead(4);
        list.addAtHead(70);
        list.addAtHead(3);
        list.addAtHead(2);
        list.addAtHead(5);
        list.addAtHead(80);
        list.traverse();
       
    }
}