import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> implements Iterable<T>, Comparable<MyLinkedList<T>> {
    // Head node of the linked list
    private Node<T> head;

    // Constructor: Initializes the head node of the linked list
    public MyLinkedList() {
        // Create a new head node with null data
        this.head = new Node<T>(null);
    }

    // Inserts a new node at the beginning of the linked list
    public void insertAtHead(T data) {
        Node<T> newNode = new Node<>(data); // Create a new node with the given data
        newNode.next = this.head.next; // Point the new node to the current first node
        this.head.next = newNode; // Point the head to the new node
    }

    // Inserts a new node at the end of the linked list
    public void insertAtEnd(T data) {
        Node<T> newNode = new Node<>(data); // Create a new node with the given data
        Node<T> curr = this.head.next; // Start at the first node
        Node<T> prev;
        // Traverse the list until the end
        for (prev = this.head; curr != null; curr = curr.next) {
            prev = curr; // Keep track of the previous node
        }
        prev.next = newNode; // Insert the new node at the end
    }

    // Inserts a new node in a sorted linked list
    public void insertSorted(T data) {
        Node<T> newNode = new Node<T>(data); // Create a new node with the given data
        Node<T> curr = this.head.next; // Start at the first node
        Node<T> prev;
        // Traverse the list until we find the correct position to insert
        for (prev = this.head; curr != null && data.compareTo(curr.data) > 0; curr = curr.next) {
            prev = curr; // Keep track of the previous node
        }
        newNode.next = curr; // Point the new node to the current node
        prev.next = newNode; // Point the previous node to the new node
    }

    public void insertAtIndex(T data, int index) {
        if (index < 0 || index > this.length()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int size = length();
        Node<T> newNode = new Node<T>(data); // Create a new node with the given data
        if (isEmpty()) {
            head.next = newNode;
        } else if (index == 0) {
            newNode.next = head.next;
            head.next = newNode;
        } else if (index == size) {
            Node<T> curr = head.next;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        } else {
            Node<T> curr = head.next;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            newNode.next = curr.next;
            curr.next = newNode;
        }
    }

    // Deletes the last node from the linked list
    public void deleteEnd(T data) {
        Node<T> curr = this.head.next; // Start at the first node
        // Traverse the list until the end
        for (Node<T> prev = this.head; curr.next != null; curr = curr.next) {
            // Keep moving to the next node
        }
        curr.next = null; // Remove the reference to the last node
    }

    // Deletes a specific node from a sorted linked list
    public void deleteSorted(T data) {
        Node<T> curr = this.head.next; // Start at the first node
        Node<T> prev;
        // Traverse the list until we find the node to delete
        for (prev = this.head; curr != null && data.compareTo(curr.data) > 0; curr = curr.next) {
            prev = curr; // Keep track of the previous node
        }
        // If we found the node and it matches the data, remove it
        if (curr != null && curr.data.equals(data)) {
            prev.next = curr.next; // Point the previous node to the next node, effectively removing the current
                                   // node
        }
    }

    public T deleteAtHead() {
        if (isEmpty())
            return null;
        T data = (T) head.next.data;
        head.next = head.next.next;
        return data;

    }

    // Searches for a specific node in the linked list
    public boolean search(T data) {
        // Start at the first node
        for (Node<T> curr = this.head.next; curr != null; curr = curr.next) {
            // If the data of the current node matches the data we're looking for, return
            // true
            if (curr.getData() == data) {
                return true;
            }
        }
        // If we've gone through the whole list and haven't found the data, return false
        return false;
    }

    // Traverses the linked list and prints each node's data
    public void traversList() {
        // Start at the first node
        for (Node<T> curr = this.head.next; curr != null; curr = curr.next) {
            // Print the data of the current node
            System.out.println(curr.data);
        }
    }

    // Reverses the linked list
    public void reverse() {
        Node<T> curr = this.head.next; // Start at the first node
        Node<T> prev = null; // Initialize previous node to null
        // Traverse the list
        for (Node<T> next = null; curr != null; curr = next) {
            next = curr.next; // Store the next node
            curr.next = prev; // Reverse the link
            prev = curr; // Move the prev pointer one step ahead
        }
        this.head.next = prev; // After the loop, prev will be pointing to the new head of the reversed list
    }

    // Returns the length of the linked list
    public int length() {
        int length = 0; // Initialize length to 0
        // Traverse the list
        for (Node<T> curr = this.head.next; curr != null; curr = curr.next) {
            ++length; // Increment length for each node
        }
        return length; // Return the length
    }

    // Checks if the linked list is empty
    public boolean isEmpty() {
        // The list is empty if the head node doesn't point to any other node
        return this.head.next == null;
    }

    // Clears the linked list
    public void clear() {
        // Remove all nodes by setting the head node's next pointer to null
        this.head.next = null;
    }

    // Returns a string representation of the linked list
    public String toString() {
        String s = " "; // Initialize the string
        // Traverse the list
        for (Node<T> curr = this.head.next; curr != null; curr = curr.next) {
            // Add each node's data to the string
            s = s + String.valueOf(curr.data) + " ";
        }
        return s; // Return the string
    }

    @Override
    public int compareTo(MyLinkedList<T> other) {
        Node<T> node1 = this.head;
        Node<T> node2 = other.head;

        while (node1 != null && node2 != null) {
            int comparison = node1.data.compareTo(node2.data);
            if (comparison != 0) {
                // As soon as we find a pair of elements that differ, we return the result of
                // their comparison
                return comparison;
            }
            node1 = node1.next;
            node2 = node2.next;
        }

        // If we've gone through the whole list and all elements are equal, then the
        // shorter list is considered "less than" the longer one
        if (node1 != null) {
            return 1; // this list is longer, so it's considered "greater"
        } else if (node2 != null) {
            return -1; // other list is longer, so it's considered "greater"
        } else {
            return 0; // both lists are the same length and all elements are equal, so the lists are
                      // considered "equal"
        }
    }

    public Node<T> getHead() {
        return head;
    }

    // Returns an iterator over the elements in this list in proper sequence
    @Override
    public Iterator<T> iterator() {
        // Create a new instance of the LinkedListIterator inner class
        return new LinkedListIterator();
    }

    // Inner class to define the iterator
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> curr = head.next; // Start at the first node
        // Checks if there is a next element in the list

        @Override
        public boolean hasNext() {
            // There is a next element if the current node is not null
            return curr != null;
        }

        // Returns the next element in the list
        @Override
        public T next() {
            T data = curr.data; // Store the data of the current node
            curr = curr.next; // Move to the next node
            return data; // Return the stored data
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.insertAtHead(4);
        list.insertAtHead(6);
        list.traversList();
        System.out.println("=====");
        list.insertAtIndex(5, 0);
        list.traversList();
    }
}
