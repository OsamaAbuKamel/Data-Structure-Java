public class MargeTwoSortedLL {
    public static void main(String[] args) {
        // make two sort linked list
        Node head1 = new Node(1);
        head1.setNext(new Node(3));
        head1.getNext().setNext(new Node(5));

        Node head2 = new Node(2);
        head2.setNext(new Node(4));
        head2.getNext().setNext(new Node(6));

        // merge two linked list
        Node mergedHead = mergeTwoSortedLists(head1, head2);

        // print merged linked list
        Node curr = mergedHead;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }

    }

    private static MargeTwoSortedLL.Node mergeTwoSortedLists(MargeTwoSortedLL.Node l1, MargeTwoSortedLL.Node l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.data <= l2.data) {
            l1.next = mergeTwoSortedLists(l1.next, l2);
            return l1;
        } else
            l2.next = mergeTwoSortedLists(l1, l2.next);
        return l2;
    }

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        // set and get
        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }
}
