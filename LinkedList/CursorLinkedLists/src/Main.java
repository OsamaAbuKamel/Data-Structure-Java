public class Main {

    public static void main(String[] args) {

        CursorLinkedList<Integer> list = new CursorLinkedList<>(11);

        // Create a new list
        int head1 = list.createList();

        // Insert nodes
        list.insertAtHead(5, head1);
        list.insertAtHead(4, head1);
        list.insertAtHead(3, head1);

        // Print list
        list.traversList(head1);

        // Create another list
        int head2 = list.createList();

        // Insert nodes
        list.insertAtHead(8, head2);
        list.insertAtHead(9, head2);

        // Print lists
        list.traversList(head2);



    }

}
