package src;

import java.util.Iterator;

public class DLinkedList<T extends Comparable<T>> implements Iterable<T> {
    DNode<T> head;

    public DLinkedList() {
        // Create a new DNode with a null value
        head = new DNode<>(null);
    }

    public void addFirst(T data) {
        // Create a new node with the provided data
        DNode<T> newNode = new DNode<>(data);
        // Check if the list is empty
        if (isEmpty()) {
            // If the list is empty, set the new node as the next node after the head
            // and set the previous node of the new node as the head
            head.next = newNode;
            newNode.prev = head;
        } else {
            // If the list is not empty, set the next node of the new node as the current
            // first node
            // and set the previous node of the new node as the head
            newNode.next = head.next;
            newNode.prev = head;
            // Set the previous node of the current first node as the new node
            // and set the next node of the head as the new node
            head.next.prev = newNode;
            head.next = newNode;
        }
    }

    public void addLast(T data) {
        // Create a new node with the given data
        DNode<T> newNode = new DNode<>(data);
        // Check if the list is empty
        if (isEmpty()) {
            // If the list is empty, add the new node after the head
            head.next = newNode;
            // Set the new node's prev reference to the head
            newNode.prev = head;
        } else {
            // If the list is not empty, find the last node
            DNode<T> last = head.next;
            while (last.next != null) {
                last = last.next;
            }
            // Add the new node after the last node
            last.next = newNode;
            // Set the new node's prev reference to the last node
            newNode.prev = last;
        }
    }

    public void addSorted(T data) {
        // Create a new node with the given data
        DNode<T> newNode = new DNode<>(data);
        DNode<T> curr = head.next;
        // Traverse the list to find the correct position to insert the new node
        // The correct position is the first position where the data of the current node
        // is greater than the data of the new node
        while (curr != null && curr.data.compareTo(data) < 0) {
            curr = curr.next;
        }
        // If the correct position is found, insert the new node before the current node
        if (curr != null) {
            newNode.next = curr;
            newNode.prev = curr.prev;
            curr.prev.next = newNode;
            curr.prev = newNode;
        } else {
            // If no such position is found (which means the new node is the largest), add
            // the new node at the end of the list
            addLast(data);
        }
    }

    public T removeFirst() {
        // Check if the list is empty
        if (isEmpty()) {
            // If the list is empty, return null
            return null;
        } else {
            // If the list is not empty, store the data of the first node
            T data = head.next.data;
            // Update the head's next reference to skip the first node
            head.next = head.next.next;
            // If the new first node exists, update its prev reference to point to the head
            if (head.next != null) {
                head.next.prev = head;
            }
            // Return the data of the removed node
            return data;
        }
    }

    public T removeLast() {
        // Check if the list is empty
        if (isEmpty()) {
            // If the list is empty, return null
            return null;
        } else {
            // If the list is not empty, start from the first node
            DNode<T> last = head.next;
            // Traverse the list to find the last node
            while (last.next != null) {
                last = last.next;
            }
            // Store the data of the last node
            T data = last.data;
            // Remove the last node by setting the next reference of the second-last node to
            // null
            last.prev.next = null;
            // Clear the prev reference of the last node
            last.prev = null;
            // Return the data of the removed node
            return data;
        }
    }

    public T removeSorted(T data) {
        // Initialize the current node as the head node's next node
        DNode<T> curr = head.next;
        // If the linked list is empty, return null
        if (isEmpty()) {
            return null;
        } else {
            // Iterate through the linked list until the current node is null or the data in
            // the current node is greater than the given data
            while (curr != null && curr.data.compareTo(data) == 0) {
                curr = curr.next;
            }
            // If the current node is not null and the data in the current node is equal to
            // the given data
            if (curr != null && curr.data.equals(data)) {
                  curr.prev.next = curr.next;
                    if (curr.next != null) {
                        curr.next.prev = curr.prev;
                    }
            }
            // Return the data in the current node
            return curr.data;
        }
    }

    // This method searches for a specific data element in the doubly linked list.
    public T search(T data) {
        // Start from the first node
        for (DNode<T> curr = head.next; curr != null; curr = curr.next) {
            // If the data of the current node equals the searched data
            if (curr.data.equals(data)) {
                // Return the data
                return data;
            }
        }
        // If the data is not found, return null
        return null;
    }

    public void sort() {
        // Create a new empty list
        DLinkedList<T> sortedList = new DLinkedList<>();
        // Start from the first node
        DNode<T> current = head.next;
        while (current != null) {
            // Store the next node
            DNode<T> temp = current.next;
            // Remove the current node from the list
            current.prev.next = temp;
            if (temp != null) {
                temp.prev = current.prev;
            }
            // Add the current node to the sorted list
            sortedList.addSorted(current.data);
            // Move to the next node
            current = temp;
        }
        // Replace the original list with the sorted list
        head = sortedList.head;
    }

    /**
     * This method sorts the doubly linked list in ascending order.
     */
    public void insertionSort() {
        DNode<T> current = head.next;
        DNode<T> prev = null;
        while (current != null) {
            DNode<T> temp = current.next;
            // Cache the previous node of the current node
            prev = current.prev;
            // Remove the current node from the list
            prev.next = temp;
            if (temp != null) {
                temp.prev = prev;
            }
            // Find the correct position for the current node
            DNode<T> pos = head;
            while (pos.next != null && pos.next.data.compareTo(current.data) < 0) {
                pos = pos.next;
            }
            // Insert the current node after the found position
            current.next = pos.next;
            pos.next = current;
            current.prev = pos;
            if (current.next != null) {
                current.next.prev = current;
            }
            // Move to the next node
            current = temp;
        }
    }

    /**
     * Reverses the linked list.
     */
    public void reverse() {
        // Initialize the current node as the head node's next node
        DNode<T> current = head.next;
        // Initialize the previous node as null
        DNode<T> prev = null;
        // Initialize the next node as the current node's next node
        DNode<T> next = null;
        // Iterate through the linked list until the current node is null
        while (current != null) {
            // Store the next node in a temporary variable
            next = current.next;
            // Update the current node's next pointer to point to the previous node
            current.next = prev;
            // Update the previous node to be the current node
            prev = current;
            // Update the current node to be the next node
            current = next;
        }
        // Update the head node's next pointer to point to the previous node
        head.next = prev;
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

        // Return the data of the current node and update the current node to the next
        // node
        @Override
        public T next() {
            T data = curr.data;
            curr = curr.next;
            return data;
        }
    }
}
