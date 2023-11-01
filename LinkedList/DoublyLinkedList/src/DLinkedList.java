import java.util.Iterator;

public class DLinkedList<T extends Comparable<T>> implements Iterable<T> {
    DNode<T> head;

    public DLinkedList() {
        // Create a new DNode with a null value
        head = new DNode<>(null);
    }

    public void insertAtHead(T data) {
        // Create a new node with the provided data
        DNode<T> newNode = new DNode<>(data);
        // Check if the list is empty
        if (isEmpty()) {
            // If the list is empty, set the new node as the first node
            head.next = newNode;
        } else {
            // If the list is not empty, insert the new node at the beginning
            // Set the next of the new node to the current first node
            newNode.next = head.next;
            // Set the previous of the current first node to the new node
            head.prev = newNode;
            // Set the new node as the first node
            head.next = newNode;
        }
    }

    public void insertAtLast(T data) {
        // Create a new node with the provided data
        DNode<T> newNode = new DNode<>(data);
        // Check if the list is empty
        if (isEmpty()) {
            // If the list is empty, set the new node as the first node
            head = newNode;
        } else {
            // Start from the first node
            DNode<T> last = head;
            // Traverse the list until the end
            while (last.next != null) {
                last = last.next;
            }
            // Now, 'last' is the last node in the list
            // Add the new node at the end of the list
            last.next = newNode;
            newNode.prev = last;
        }
    }

    // Delete the node at the head of the doubly linked list
    public T deleteAtHead() {
        // Check if the list is empty
        if (isEmpty()) {
            // If empty, return null
            return null;
        } else {
            // Store the data from the current head
            T data = head.next.data;
            // Update the head to point to the next node
            head.next = head.next.next;
            // Check if new head is not null
            if (head.next != null) {
                // If not null, set the new head's prev pointer to null
                head.prev = head;
            }
            // Return the data from the deleted node
            return data;
        }
    }
        public T deleteAtLast() {
            if (isEmpty()) {
                return null;
            } else {
                T data = head.next.data;
                DNode<T> curr= head.next;
                while (curr!=null) {
                    curr=curr.next;
                }
                curr=null;
                return data;    
            }
        }

    // Method to check if the list is empty
    public boolean isEmpty() {
        // The list is empty if the 'next' reference of the head node is null
        return head.next == null;
    }

    // Method to clear the list
    public void clear() {
        // Setting the 'next' reference of the head node to null
        // effectively removes all nodes from the list
        head.next = null;
    }

    public int length() {
        // Initialize a counter for the length
        int length = 0;
        // Start from the first node in the list
        DNode<T> curr = head.next;
        // Traverse the list until the end
        while (curr != null) {
            // Increment the length counter
            ++length;
            // Move to the next node
            curr = curr.next;
        }
        // Return the final length
        return length;
    }

    // This method returns the head of the DNode list
    public DNode<T> getHead() {
        return this.head;
    }

    @Override
    public String toString() {
        // Initialize an empty string for the result
        String s = "";
        // Start from the first node in the list
        DNode<T> curr = head.next;
        // Iterate over each node in the list
        for (int i = 0; i < length(); i++) {
            // Append the data of the current node to the result string
            s = s + String.valueOf(curr.data) + " ";
            // Move to the next node
            curr = curr.next;
        }
        // Return the final result string
        return s;
    }

   @Override
    public Iterator<T> iterator() {
        // Create a new iterator for the Double Linked List
        return new DLinkedListIterator();
    }

    // Create a new iterator for the Double Linked List
    private class DLinkedListIterator implements Iterator<T> {
        // Initialize the current node to the head's next node
        private DNode<T> curr = head.next;

        // Check if the current node is not null
        @Override
        public boolean hasNext() {
            return curr != null;
        }

        // Return the data of the current node and update the current node to the next node
        @Override
        public T next() {
            T data = curr.data;
            curr = curr.next;
            return data;
        }
    }
}
