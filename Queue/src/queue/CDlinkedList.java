package queue;


public class CDlinkedList<T extends Comparable<T>> {
    private static class DNode<T> {
        private T data;
        private DNode<T> next;
        private DNode<T> prev;

        public DNode(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    private DNode<T> head;

    public void addAtHead(T data) {
        // Create new node with data
        DNode<T> newNode = new DNode<>(data);
        if (isEmpty()) {
            // If empty list, point node to itself
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            // Store current head node
            DNode<T> curr = head;
            // Set new node's next to current head
            newNode.next = curr;
            // Set new node's prev to current tail
            newNode.prev = curr.prev;
            // Set current tail's next to new node
            curr.prev.next = newNode;
            // Set current head's prev to new node
            curr.prev = newNode;
            // Update the head reference
            head = newNode;
        }
    }

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

    public void addAtLast(T data) {
        // Create a new node with the data
        DNode<T> newNode = new DNode<>(data);
        if (isEmpty()) {
            // If list is empty, new node points to itself
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            // Get current head node
            DNode<T> curr = head;
            // Set new node's next to current head
            newNode.next = curr;
            // Set new node's prev to current tail
            newNode.prev = curr.prev;
            // Set current tail's next to new node
            curr.prev.next = newNode;
            // Set current head's prev to new node
            curr.prev = newNode;
        }
    }

   T removeFirst() {
        if (head == null) {
            System.out.println("List is empty. Nothing to remove.");
            return null;
        }

        T removedData = head.data;

        if (head.next == head) {
            head = null;
        } else {
            DNode<T> last = head.prev;
            DNode<T> second = head.next;
            last.next = second;
            second.prev = last;
            head = second;
        }

        return removedData;
    }

    public T removeLast() {
        // Check if the list is empty
        if (isEmpty()) {
            return null;
        }
        // Get the current head node
        DNode<T> curr = head;
        // If there is only one element in the list
        if (curr.next == curr) {
            head = null;
        } else {
            // Traverse to the last node
            while (curr.next != head) {
                curr = curr.next;
            }
            // Update references to skip the last node
            curr.prev.next = head;
            head.prev = curr.prev;
        }
        return curr.getData();
    }

    // This method removes a node from the sorted linked list
    public T removeSorted(T date) {
        // Check if the list is empty
        if (isEmpty()) {
            return null;
        }
        // Create a pointer to the head node
        DNode<T> curr = head;
        // Loop through the list until the node is found
        do {
            // Check if the current node is the node to be removed
            if (curr.getData().compareTo(date) == 0) {
                // If the node is the head node, remove the first node
                if (curr == head) {
                    removeFirst();
                }
                // Otherwise, remove the node
                else {
                    // Update the next reference of the previous node to skip the current node
                    curr.prev.next = curr.next;
                    // Update the previous reference of the next node to skip the current node
                    curr.next.prev = curr.prev;
                }
                // Return the removed node
                return date;
            }
        } while (curr != head);
        // Return null if the node is not found
        return null;
    }

    // This method removes the first element of the list and returns it
    public void removeDuplicates() {
        // Check if the list is empty or if the head node's next pointer is pointing to
        // itself
        if (isEmpty() || head.next == head) {
            // Return if the list is empty or if the head node's next pointer is pointing to
            // itself
            return;
        }
        // Create a curr pointer to the head node
        DNode<T> curr = head;
        // Iterate through the list until the end
        do {
            // Create a runner pointer to the next node
            DNode<T> runner = curr.next;
            // Iterate through the list until the end
            while (runner != head) {
                // Check if the data of the current node and the runner node are equal
                if (curr.data.equals(runner.data)) {
                    // Create a nextRunner pointer to the next node of the runner node
                    DNode<T> nextRunner = runner.next;
                    // Set the next pointer of the previous node to the next pointer of the runner
                    // node
                    runner.prev.next = runner.next;
                    // Set the previous pointer of the next pointer of the runner node to the
                    // previous pointer of the runner node
                    runner.next.prev = runner.prev;
                    // Set the runner pointer to the nextRunner pointer
                    runner = nextRunner;
                } else {
                    // Increment the runner pointer if the data of the current node and the runner
                    // node are not equal
                    runner = runner.next;
                }
            }
            // Set the curr pointer to the next node of the current node
            curr = curr.next;
        } while (curr != head);
    }

    public T search(T data) {
        // Loop through the list to find the data
        DNode<T> curr = head;
        if (curr != null) {
            do {
                // Check if the data is equal to the current node
                if (curr.getData().compareTo(data) == 0) {
                    // Return the data if it is found
                    return data;
                }
                // Move to the next node
                curr = curr.next;
            } while (curr != head);
        }
        // Return null if the data is not found
        return null;
    }

    public T getFirst() {
        return head.getData();
    }

    public T get(int index) {
        // Check if the index is negative
        if (index < 0) {
            // Throw an exception if the index is negative
            throw new IllegalArgumentException("Index cannot be negative" + index);
        }
        // Create a node to iterate through the list
        DNode<T> curr = head;
        // Create a variable to store the index
        int currIndex = 0;
        // Check if the list is not empty
        if (curr != null) {
            // Iterate through the list until the end
            do {
                // Check if the index is equal to the current index
                if (index == currIndex) {
                    // Return the data at the index
                    return curr.getData();
                }
                // Increment the index
                curr = curr.next;
                // Increment the current index
                currIndex++;
            } while (curr != head);
        }
        // Throw an exception if the index is out of bounds
        throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds for the list");
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

    public int length() {
        DNode<T> curr = head;
        int count = 0;
        // Check if the list is not empty
        if (curr != null) {
            // Do a loop until the current node is the head node
            do {
                count++;
                // Set the current node to the next node
                curr = curr.next;
            } while (curr != head);
        }
        return count;
    }

    public void clear() {
        // Set the head to null, effectively clearing the list
        head = null;
    }

    public DNode<T> getHead() {
        // Return the head node of the list
        return this.head;
    }

    public boolean isEmpty() {
        // Check if the list is empty by checking if the head is null
        return head == null;
    }

    @Override
    public String toString() {
        String s = "";
        DNode<T> curr = head;
        if (curr != null) {
            do {
                s += "[" + curr.data + "] "; // Append the data of the current node to the string
                curr = curr.next; // Move to the next node
            } while (curr != head); // Continue until we reach the head again
        }
        return s; // Return the string representation of the list
    }

    public static void main(String[] args) {
        CDlinkedList<Integer> list = new CDlinkedList<>();
        System.out.println("List is empty: " + list.isEmpty());
        list.addAtHead(50);
        list.addAtHead(51);
        list.addAtHead(52);
        list.traverse();
        System.out.println();
        System.out.println("=== === === === === === === === === === === === === ===");
        list.removeLast();
        // list.removeLast();
        // list.removeLast();
        list.traverse();
    }
}