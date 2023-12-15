package com.example.projectii;

public class CursorLinkedList<T extends Comparable<T>> {
    // Array of nodes
    private CNode<T>[] nodeArray;

    // Constructor
    public CursorLinkedList(int size) {
        // Initialize the node array with the given size
        this.nodeArray = new CNode[size];
        // Initialize each node in the array
        initialize();
    }

    // Initializes each node in the node array
    private void initialize() {
        for (int i = 0; i < nodeArray.length - 1; i++) {
            // Set the next index of each node to i+1
            nodeArray[i] = new CNode<T>(null, i + 1);
        }
        // Set the last node's next index to 0
        nodeArray[nodeArray.length - 1] = new CNode<T>(null, 0);
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
        nodeArray[nodeIndex] = new CNode<>(null, nextIndex);
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

    public void clear(int nodeIndex) {
        nodeArray[nodeIndex].next = 0;
    }

    public T getFirst(int l) {
        if (!isEmpty(l))
            return nodeArray[nodeArray[l].next].data;
        return null;
    }
    public void insertFirst(T data, int list) {
        
        // Check if list is valid
        if(list < 0 || list >= nodeArray.length) {
            throw new IllegalArgumentException("Invalid list index: " + list);
        }
        
        // Allocate a new node
        int newNode = allocateNode();
        
        // Set data in the new node
        nodeArray[newNode].data = data;
        
        // Point the new node to the current first node
        nodeArray[newNode].next = nodeArray[list].next;
        
        // Update the first pointer to point to the new node
        nodeArray[list].next = newNode;
        
    }
    
    
    public T deleteFirst(int list) {
        if (isEmpty(list)) {
            return null;
        }
        
        int first = nodeArray[list].next;
        T data = nodeArray[first].data;
        
        int newFirst = nodeArray[first].next;
        free(first);
        nodeArray[list].next = newFirst;
        
        return data;
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
        nodeArray[currentIndex] = new CNode<>(null, 0);
        // Return the index of the new node
        return currentIndex;
    }

    
    public void insertAtTail(T data, int headNodeIndex) {
        int currentIndex = headNodeIndex;
        while (nodeArray[currentIndex].next != 0) {
            currentIndex = nodeArray[currentIndex].next;
        }
        int newNodeIndex = allocateNode();
        if (newNodeIndex != 0) {
            nodeArray[newNodeIndex] = new CNode<>(data, 0);
            nodeArray[currentIndex].next = newNodeIndex;
        } else {
            throw new OutOfMemoryError("No free nodeArray available");
        }
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
        nodeArray[newNodeIndex] = new CNode<>(data, nodeArray[predecessorIndex].next);
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
    public CNode<T> delete(T data, int l) {
        // Find the previous node of the node to be deleted
        int p = findPrevious(data, l);
        // If the node exists
        if (p != -1) {
            // Store the next node of the node to be deleted
            int c = nodeArray[p].next;
            // Store the node in the next position
            CNode<T> temp = nodeArray[c];
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
        // Check if the node is not null and or empty
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

    public CNode<T>[] getNodeArray() {
        return this.nodeArray;
    }

    public static void main(String[] args) {
        CursorLinkedList<Integer> list = new CursorLinkedList<>(10);
        int headNodeIndex = list.createList();
        list.insertFirst(4,headNodeIndex);
        list.insertFirst(6,headNodeIndex);
        list.insertFirst(8,headNodeIndex);
        list.traversList(headNodeIndex);
    
    }
}