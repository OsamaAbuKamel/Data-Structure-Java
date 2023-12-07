public class CursorLinkedList<T extends Comparable<T>> {
    // Array of nodes
    private Node<T>[] nodeArray;

    // Constructor
    public CursorLinkedList(int size) {
        // Initialize the node array with the given size
        this.nodeArray = new Node[size];
        // Initialize each node in the array
        initialize();
    }

    // Initializes each node in the node array
    private void initialize() {
        for (int i = 0; i < nodeArray.length - 1; i++) {
            // Set the next index of each node to i+1
            nodeArray[i] = new Node<T>(null, i + 1);
        }
        // Set the last node's next index to 0
        nodeArray[nodeArray.length - 1] = new Node<T>(null, 0);
    }

    // Allocates a free node from the node array
    public int allocateNode() {
        // Get the index of the next free node
        int nextFreeIndex = nodeArray[0].next;
        // Set the next free node's next index to the next free node
        nodeArray[0].next = nodeArray[nextFreeIndex].next;
        // Return the index of the allocated node
        return nextFreeIndex;
    }

    // Frees a node from the node array
    public void free(int nodeIndex) {
        // Validate the node index
        if (nodeIndex < 0 || nodeIndex >= nodeArray.length) {
            throw new IllegalArgumentException("Invalid node index: " + nodeIndex);
        }
        // Get the next index of the node to be freed
        int nextIndex = nodeArray[nodeIndex].next;
        // Set the node to be freed's next index to the next free node
        nodeArray[nodeIndex].next = nodeArray[0].next;
        // Set the node to be freed to a new node with the next index as nextIndex
        nodeArray[nodeIndex] = new Node<>(null, nextIndex);
        // Set the next free node to the node index to be freed
        nodeArray[0].next = nodeIndex;
    }

    // Checks if the node at the given index is null
    public boolean isNodeNull(int nodeIndex) {
        return nodeArray[nodeIndex] == null;
    }

    // Checks if the node at the given index is empty
    public boolean isEmpty(int nodeIndex) {
        return nodeArray[nodeIndex].next == 0;
    }

    // Checks if the node at the given index is the last node in the list
    public boolean isLast(int nodeIndex) {
        return nodeArray[nodeIndex].next == 0;
    }

    // Creates a new list and returns the index of the head node
    public int createList() {
        // Allocate a new node
        int currentIndex = allocateNode();
        // Check if there are any free nodes available
        if (currentIndex == 0) {
            throw new OutOfMemoryError("No free nodeArray available");
        }
        // Set the new node's next index to 0
        nodeArray[currentIndex] = new Node<>(null, 0);
        // Return the index of the new node
        return currentIndex;
    }

    public void insertAtHead(T data, int nodeIndex) {
        // Check if the node at the given index is null
        if (isNodeNull(nodeIndex)) {
            // If the node is null, return
            return;
        }
        // Allocate a new node
        int currentIndex = allocateNode();
        // Check if there are any free nodes available
        if (currentIndex != 0) {
            // Create a new node with the given data and the next index of the node at the
            // given index
            nodeArray[currentIndex] = new Node<>(data, nodeArray[nodeIndex].next);
            // Set the next index of the node at the given index to the index of the new
            // node
            nodeArray[nodeIndex].next = currentIndex;
        } else {
            // If there are no free nodes available, throw an OutOfMemoryError
            throw new OutOfMemoryError("No free nodeArray available");
        }
    }

    public void insertAtTail(T data, int headNodeIndex) {
        // Check if head node is null
        if (isNodeNull(headNodeIndex)) {
            return;
        }
        // Get index of tail node
        int tailNodeIndex = getTail(headNodeIndex);
        // Allocate new tail node
        int newNodeIndex = allocateNode();
        if (newNodeIndex == 0) {
            throw new OutOfMemoryError("No free nodes");
        }
        // Update next pointer of current tail to new tail
        nodeArray[tailNodeIndex].next = newNodeIndex;
        // Initialize new tail node
        nodeArray[newNodeIndex] = new Node<T>(data, 0);
    }

    private int getTail(int headNodeIndex) {
        // Initialize the current node to the head node index
        int current = headNodeIndex;
        // Loop until the current node is the last node in the list
        while (!isLast(current)) {
            // Set the current node to the next node in the list
            current = nodeArray[current].next;
        }
        // Return the index of the current node
        return current;
    }

    public void insertAtSorted(T data, int headNodeIndex) {
        // Check if head node is null
        if (isNodeNull(headNodeIndex)) {
            return;
        }
        // Find the predecessor node for the insertion
        int predecessorIndex = findPredecessor(data, headNodeIndex);
        // Allocate a new node
        int newNodeIndex = allocateNode();
        if (newNodeIndex == 0) {
            throw new OutOfMemoryError("No free nodes");
        }
        // Create a new node with the given data and the next index of the predecessor
        // node
        nodeArray[newNodeIndex] = new Node<>(data, nodeArray[predecessorIndex].next);
        // Set the next index of the predecessor node to the index of the new node
        nodeArray[predecessorIndex].next = newNodeIndex;
    }

    private int findPredecessor(T data, int headNodeIndex) {
        // Initialize predecessor index to head node index
        int predecessorIndex = headNodeIndex;
        // Initialize current index to head node index
        int current = nodeArray[headNodeIndex].next;
        // Loop until current index is not null and data is less than current node data
        while (current != 0 && data.compareTo(nodeArray[current].data) > 0) {
            // Update predecessor index to current index
            predecessorIndex = current;
            // Update current index to next index of current node
            current = nodeArray[current].next;
        }
        // Return predecessor index
        return predecessorIndex;
    }

    // public method to traverse a list
    public void traversList(int l) {
        // print the list number
        System.out.print("list_" + l + "-->");
        // while the node is not null and the list is not empty
        while (!isNodeNull(l) && !isEmpty(l)) {
            // set the list to the next node
            l = nodeArray[l].next;
            // print the node
            System.out.print(nodeArray[l] + "-->");
        }
        // print null
        System.out.println("null");
    }

    public int lengthRecursive(int nodeIndex) {
        // Base case: if the current node is empty (nodeIndex == 0), return 0
        if (isEmpty(nodeIndex)) {
            return 0;
        }
        // Recursive case: move to the next node and add 1 to the length
        return 1 + lengthRecursive(nodeArray[nodeIndex].next);
    }

    public void deleteFirst(int headNodeIndex) {
        // Check if list is empty
        if (isNodeNull(headNodeIndex)) {
            return;
        }
        // Get index of first node
        int firstNodeIndex = headNodeIndex;
        // Get index of second node
        int secondNodeIndex = nodeArray[firstNodeIndex].next;
        // Update head pointer to second node
        headNodeIndex = secondNodeIndex;
        // Free first node
        free(firstNodeIndex);
        // Check if list is now empty
        if (isNodeNull(headNodeIndex)) {
            headNodeIndex = 0; // Set head pointer to null
        }
    }

    // This method returns the length of a linked list starting from a given head
    // index
    public int getLength(int headIndex) {
        // Initialize the length to 0
        int length = 0;
        // Set the current index to the given head index
        int currentIndex = headIndex;
        // Loop until the node at the current index is null or the list is empty
        while (!isNodeNull(currentIndex) && !isEmpty(currentIndex)) {
            // Increment the length
            length++;
            // Set the current index to the next node
            currentIndex = nodeArray[currentIndex].next;
        }
        // Return the length
        return length;
    }

    public int find(T data, int l) {
        // Check if the node is not null and not empty
        while (!isNodeNull(l) && !isEmpty(l)) {
            // Set the next node to the current node
            l = nodeArray[l].next;
            // Check if the data is equal to the data passed in
            if (nodeArray[l].data.equals(data))
                return l;
        }
        // Return -1 if the data is not found
        return -1;
    }

    // public method to find the previous node of a given data element
    public int findPrevious(T data, int l) {
        // while loop to iterate through the linked list
        while (!isNodeNull(l) && !isEmpty(l)) {
            // if the data element of the node is equal to the given data element
            if (nodeArray[nodeArray[l].next].data.equals(data))
                // return the index of the node
                return l;
            // move to the next node
            l = nodeArray[l].next;
        }
        // return -1 if the node is not found
        return -1;
    }

    // This method deletes a node from the linked list
    public Node<T> delete(T data, int l) {
        // Find the previous node of the node to be deleted
        int p = findPrevious(data, l);
        // If the node exists
        if (p != -1) {
            // Store the next node of the node to be deleted
            int c = nodeArray[p].next;
            // Store the node in the next position
            Node<T> temp = nodeArray[c];
            // Set the next node of the node to be deleted to the next node of the stored
            // node
            nodeArray[p].next = temp.next;
            // Free the memory of the node to be deleted
            free(c);
        }
        // Return null if the node to be deleted does not exist
        return null;
    }
    public T get(int index, int l) {
        // Check if the node is not null and not empty
        while (!isNodeNull(l) && !isEmpty(l)) {
            // Set the next node to the current node
            l = nodeArray[l].next;
            // Check if the data is equal to the data passed in
            if (index == 0)
                return nodeArray[l].data;
            // Decrement the index
            index--;
        }
        // Return null if the data is not found
        return null;
    }
    public boolean isPalindrome(int l){
        while (!isNodeNull(l)&& !isEmpty(l)) {
        
        }
    }

}
