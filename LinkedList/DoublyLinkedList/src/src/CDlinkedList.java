package src;

public class CDlinkedList<T extends Comparable<T>> {
    private DNode<T> head;

    // This method adds a new node to the sorted list
    public void addSorted(T data) {
        // Create a new node with the data passed in
        DNode<T> newNode = new DNode<>(data);
        // If the list is empty, set the head to the new node and set the next and
        // previous nodes to themselves
        if (isEmpty()) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
            // Otherwise, loop through the list until the data is less than the current node
        } else {
            DNode<T> curr = head;
            while (curr.next != head && data.compareTo(curr.getData()) > 0) {
                curr = curr.next;
            }
            // Set the new node's next to the current node
            newNode.next = curr;
            // Set the new node's previous to the current node's previous
            newNode.prev = curr.prev;
            // Set the current node's next to the new node
            curr.prev.next = newNode;
            // Set the current node's previous to the new node
            curr.prev = newNode;
            // If the data passed in is less than the head, set the head to the new node
            if (data.compareTo(head.getData()) < 0) {
                head = newNode;
            }
        }
    }

    public void addAtHead(T data) {
        DNode<T> newNode = new DNode<>(data);
        if (isEmpty()) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            DNode<T> curr = head;
            newNode.next = curr;
            newNode.prev = curr.prev;
            curr.prev.next = newNode;
            curr.prev = newNode;
            head = newNode;
        }
    }
 public void addAtLast(T data) {
        DNode<T> newNode = new DNode<>(data);
        if (isEmpty()) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            DNode<T> curr = head;
            newNode.next = curr;
            newNode.prev = curr.prev;
            curr.prev.next = newNode;
            curr.prev = newNode;
        }
    }

    public void traverse() {
        // Initialize current node to the head node
        DNode<T> curr = head;
        // Check if the list is not empty
        if (curr != null) {
            // Do a loop until the current node is the head node
            do {
                // Print the data of the current node
                System.out.print("[ " + curr.data + " ] ");
                // Set the current node to the next node
                curr = curr.next;
            } while (curr != head);
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}