public class Main {

    public static void main(String[] args) {

        CursorLinkedList<Integer> list = new CursorLinkedList<>(11);

        // Create a new list
        int head1 = list.createList();

        // Insert nodes
        list.insertAtTail(5, head1);
        list.insertAtTail(4, head1);
        list.insertAtTail(3, head1);

        // Print list
        list.traverseList(head1);
        System.out.println(list.lengthRecursive(head1));
//        list.delete(5,head1);
//        list.traverseList(head1);
//        System.out.println(list.getLength(head1));
//
//        // Create another list
//        int head2 = list.createList();
//
//        // Insert nodes
//        list.insertAtHead(8, head2);
//        list.insertAtHead(9, head2);
//
//        // Print lists
//        list.traversList(head2);
//        CursorLinkedList<Integer> list2 = new CursorLinkedList<>(11);
//        list2.insertAtHead(4, head2);


    }

}
