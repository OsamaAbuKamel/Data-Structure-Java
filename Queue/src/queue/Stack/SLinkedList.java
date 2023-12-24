package queue.Stack;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLinkedList<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> head;
    
    public SLinkedList() {
        head = new Node<>(null);
    }
    
    // This method inserts a new node with the given data at the head of the linked
    // list
    public void insertAtHead(T data) {
        // Create a new node with the given data
        Node<T> newNode = new Node<>(data);
        // Set the next node of the new node to the current head's next node
        newNode.setNext(head.getNext());
        // Set the current head's next node to the new node
        head.setNext(newNode);
    }
    
    // This method inserts a new node with the given data at the end of the linked
    // list
    public void insertAtLast(T data) {
        // Create a new node with the given data
        Node<T> newNode = new Node<>(data);
        // Initialize a pointer to the current node
        Node<T> curr = head.getNext();
        // Initialize a pointer to the previous node
        Node<T> prev;
        // Traverse the linked list until the end
        for (prev = head; curr != null; curr = curr.getNext()) {
            prev = curr;
        }
        // Set the next node of the new node to null, indicating the end of the linked
        // list
        newNode.setNext(null);
        // Set the previous node's next node to the new node
        prev.setNext(newNode);
    }
    
    // This method inserts a new node with the given data in the correct position in
    // the linked list based on the comparison result of the data with the current
    // node's data
    public void insertSorted(T data) {
        // Create a new node with the given data
        Node<T> newNode = new Node<>(data);
        // Initialize a pointer to the current node
        Node<T> curr = head.getNext();
        // Initialize a pointer to the previous node
        Node<T> prev;
        // Traverse the linked list until the end or until the data is less than or
        // equal to the current node's data
        for (prev = head; curr != null && data.compareTo(curr.getData()) >= 0; curr = curr.getNext()) {
            prev = curr;
        }
        // Set the next node of the new node to the current node or the next node of the
        // current node, indicating the correct position in the linked list
        newNode.setNext(curr);
        // Set the previous node's next node to the new node
        prev.setNext(newNode);
    }
    
    // This method deletes the first node from the linked list and returns its data
    public T deleteAtHead() {
        // Get the data from the first node
        T data = head.getNext().getData();
        // If the linked list is empty, return null
        if (isEmpty()) {
            return null;
        }
        // Set the head's next node to the next node of the current node, effectively
        // skipping the first node
        head.setNext(head.getNext().getNext());
        // Return the data from the first node
        return data;
    }
    
    // This method deletes the last node from the linked list and returns its data
    public T deleteAtEnd() {
        // If the linked list is empty, return null
        if (isEmpty()) {
            return null;
        }
        // Initialize a pointer to the current node
        Node<T> curr = head;
        // Initialize a pointer to the previous node
        Node<T> prev = null;
        // Traverse the linked list until the end
        while (curr.getNext() != null) {
            prev = curr;
            curr = curr.getNext();
        }
        // If the previous node is not null, set its next node to null, effectively
        // skipping the last node
        if (prev != null) {
            prev.setNext(null);
        } else {
            // If there was only one node in the list, set the head to null
            head = null;
        }
        // Return the data from the last node
        return curr.getData();
    }
    
    public T deleteSorted(T data) {
        Node<T> curr = head.getNext();
        Node<T> prev = head;
        if (isEmpty()) {
            return null; // List is empty, return null
        } else {
            while (curr != null && curr.getData().compareTo(data) != 0) {
                prev = curr;
                curr = curr.getNext();
            }
            if (curr == null) {
                return null; // Data not found in the list, return null
            } else {
                prev.setNext(curr.getNext()); // Remove the node from the list
                return curr.getData(); // Return the deleted data
            }
        }
    }
    
    public T search(T data) {
        // Start at the first node
        for (Node<T> curr = this.head.getNext(); curr != null; curr = curr.getNext()) {
            // If the data of the current node matches the data we're looking for, return
            // true
            if (curr.getData() == data) {
                return data;
            }
        }
        // If we've gone through the whole list and haven't found the data, return false
        return null;
    }
    
    public void reverse() {
        Node<T> curr = this.head.getNext(); // Start at the first node
        Node<T> prev = null; // Initialize previous node to null
        // Traverse the list
        for (Node<T> next = null; curr != null; curr = next) {
            next = curr.getNext(); // Store the next node
            curr.setNext(prev); // Reverse the link
            prev = curr; // Move the prev pointer one step ahead
        }
        this.head.setNext(prev); // After the loop, prev will be pointing to the new head of the reversed list
    }
    
    public boolean isEmpty() {
        return head.getNext() == null; // Returns true if the list is empty, false otherwise
    }
    
    public void clear() {
        head.setNext(null); // Clears the list by setting the next node of the head to null
    }
    
    public int length() {
        int length = 0; // Initialize the count variable to 0
        Node<T> curr = head.getNext(); // Start at the first node
        while (curr != null) {
            curr = curr.getNext(); // Move to the next node
            length++; // Increment the count
        }
        return length; // Return the count
    }
    
    // Traverses the linked list and prints each node's data
    public void traversList() {
        // Start at the first node
        for (Node<T> curr = this.head.getNext(); curr != null; curr = curr.getNext()) {
            // Print the data of the current node
            System.out.print(curr.getData() + " ");
        }
    }
    
    public String toString() {
        String s = " "; // Initialize the string
        // Traverse the list
        for (Node<T> curr = this.head.getNext(); curr != null; curr = curr.getNext()) {
            s += curr.getData() + " ";
        }
        return s; // Return the string
    }
    

    
    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative: " + index);
        }
        Node<T> current = head.getNext();
        int currentIndex = 0;
        while (current != null) {
            if (currentIndex == index) {
                return current.getData();
            }
            current = current.getNext();
            currentIndex++;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentIndex);
    }
    public T getFront(){
        return head.getNext().getData();
    }
    
    public Node<T> getHead() {
        return this.head;
    }
    
    public void setHead(Node<T> head) {
        this.head = head;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head.getNext();
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list");
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
    
}