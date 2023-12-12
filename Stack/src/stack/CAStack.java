package stack;
import java.util.Iterator;
public class CAStack<T extends Comparable<T>>implements StackADT<T> {
    private CursorLinkedList<T> list;
    public CAStack(int size) {
        list = new CursorLinkedList<T>(size);
    }
    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
    public void push(T data, int index) {
       list.insertAtHead(data, index);
    }
    @Override
    public T pop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }
    @Override
    public T peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }
    @Override
    public int length() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'length'");
    }
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }
private  class CursorLinkedList<T extends Comparable<T>> {
    private class Node<T extends Comparable<T>>{
    T data;
    int next;
    public Node(T data, int next) {
        this.data = data;
        this.next = next;
    }
    }
    private Node<T>[] nodeArray;
    // Constructor
    public CursorLinkedList(int size) {
        this.nodeArray = new Node[size];
        initialize();
    }
    private void initialize() {
        for (int i = 0; i < nodeArray.length - 1; i++) {
            nodeArray[i] = new Node<T>(null, i + 1);
        }
        nodeArray[nodeArray.length - 1] = new Node<T>(null, 0);
    }
    public int allocateNode() {
        int nextFreeIndex = nodeArray[0].next;
        nodeArray[0].next = nodeArray[nextFreeIndex].next;
        return nextFreeIndex;
    }
    public void free(int nodeIndex) {
        if (nodeIndex < 0 || nodeIndex >= nodeArray.length) {
            throw new IllegalArgumentException("Invalid node index: " + nodeIndex);
        }
        int nextIndex = nodeArray[nodeIndex].next;
        nodeArray[nodeIndex].next = nodeArray[0].next;
        nodeArray[nodeIndex] = new Node<>(null, nextIndex);
        nodeArray[0].next = nodeIndex;
    }
    public boolean isNodeNull(int nodeIndex) {
        return nodeArray[nodeIndex] == null;
    }
    public boolean isEmpty(int nodeIndex) {
        return nodeArray[nodeIndex].next == 0;
    }
    public boolean isLast(int nodeIndex) {
        return nodeArray[nodeIndex].next == 0;
    }
    public int createList() {
        int currentIndex = allocateNode();
        if (currentIndex == 0) {
            throw new OutOfMemoryError("No free nodeArray available");
        }
        nodeArray[currentIndex] = new Node<>(null, 0);
        return currentIndex;
    }
    public void insertAtHead(T data, int nodeIndex) {
        if (isNodeNull(nodeIndex)) {
            return;
        }
        int currentIndex = allocateNode();
        if (currentIndex != 0) {
            nodeArray[currentIndex] = new Node<>(data, nodeArray[nodeIndex].next);
            nodeArray[nodeIndex].next = currentIndex;
        } else {
            throw new OutOfMemoryError("No free nodeArray available");
        }
    }
public void deleteFirst(int headNodeIndex) {
        if (isNodeNull(headNodeIndex)) {
            return;
        }
        int firstNodeIndex = headNodeIndex;
        int secondNodeIndex = nodeArray[firstNodeIndex].next;
        headNodeIndex = secondNodeIndex;
        free(firstNodeIndex);
        if (isNodeNull(headNodeIndex)) {
            headNodeIndex = 0; // Set head pointer to null
        }
    }
    public void insertAtTail(T data, int headNodeIndex) {
        int currentIndex = headNodeIndex;
        while (nodeArray[currentIndex].next != 0) {
            currentIndex = nodeArray[currentIndex].next;
        }
        int newNodeIndex = allocateNode();
        if (newNodeIndex != 0) {
            nodeArray[newNodeIndex] = new Node<>(data, 0);
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
    public Node<T>[] getNodeArray() {
        return this.nodeArray;
    }
}
@Override
public void push(T data) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'push'");
}
}