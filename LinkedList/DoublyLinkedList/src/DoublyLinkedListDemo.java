public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        DLinkedList<Integer> list = new DLinkedList<>();
       list.insertAtHead(1);
       list.insertAtHead(3);
       list.insertAtHead(4);
        System.out.println("Length: "+list.length());
        System.out.println(list);
        System.out.println("-----------------------------");
        list.deleteAtLast();
        System.out.println(list);
    }
}