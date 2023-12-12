package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class LStack<T extends Comparable<T>> implements StackADT<T> {
    // reference to the top node of the stack
    private Node<T> top;
    Stack <T> stack = new Stack<>();

    @Override
    public void push(T data) {
        // Create a new node with the given data
        Node<T> newNode = new Node<>(data);
        // Set the next pointer of the new node to the current top node
        newNode.setNext(top);
        // Update the top pointer to the new node
        top = newNode;
    }

    @Override
    public T pop() {
        // Check if the stack is empty
        if (isEmpty()) {
            // Return null if the stack is empty
            return null;
        }
        // Get the data of the top element
        T data = top.getData();
        // Move the top pointer to the next element
        top = top.getNext();
        // Return the data of the top element
        return data;
    }

    @Override
    public T peek() {
        return top.getData();
    }

    @Override
    public int length() {
        // Initialize count to keep track of the number of elements
        int count = 0;
        // Start at the top of the linked list
        Node<T> current = top;
        // Traverse the linked list and increment count for each element
        while (current != null) {
            count++;
            current = current.getNext();
        }
        // Return the total count
        return count;
    }

    @Override
    public void clear() {
        // Set the top element of the stack to null
        top = null;
    }

    @Override
    public boolean isEmpty() {
        // Check if the top element of the stack is null
        return top == null;
    }

    public T get(int index) {
        // Get the node at the top of the stack
        Node<T> curr = top;
        // Initialize counter to 0
        int i = 0;
        // Loop until the node is null
        while (curr != null) {
            // If the index matches the counter, return the data
            if (i == index) {
                return curr.getData();
            }
            // Otherwise, increment the counter and move to the next node
            curr = curr.getNext();
            i++;
        }
        // If the index is out of bounds, return null
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        // Loop through the list and add each element to the string
        Node<T> current = top;
        while (current != null) {
            s += current.getData() + " ";
            current = current.getNext();
        }
        return s;
    }

    @Override
    public Iterator<T> iterator() {
        // Return a new StackIterator object with the top element of this Stack
        return new StackIterator<>(top);
    }

    private static class StackIterator<T extends Comparable<T>> implements Iterator<T> {
        private Node<T> current;

        public StackIterator(Node<T> top) {
            current = top;
        }

        @Override
        public boolean hasNext() {
            // Check if the current element is not null
            return current != null;
        }

        @Override
        public T next() {
            // Check if there is a next element
            if (!hasNext()) {
                // Throw an exception if there is no next element
                throw new NoSuchElementException();
            }
            // Get the data of the current element
            T data = current.getData();
            // Set the current element to the next element
            current = current.getNext();
            // Return the data of the current element
            return data;
        }
    }
}
