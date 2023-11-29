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
            nodeArray[i] = new Node<>(null, i + 1);
        }
        // Set the last node's next index to 0
        nodeArray[nodeArray.length - 1] = new Node<>(null, 0);
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

    public void traversList(int l) {
        System.out.print("list_" + l + "-->");
        while (!isNodeNull(l) && !isEmpty(l)) {
            l = nodeArray[l].next;
            System.out.print(nodeArray[l] + "-->");
        }
        System.out.println("null");
    }
    public int getLength(int headIndex) {
        int length = 0;
        int currentIndex = headIndex;
        while (!isNodeNull(currentIndex) && !isEmpty(currentIndex)) {
            length++;
            currentIndex = nodeArray[currentIndex].next;
        }
        return length;
    }
    
}
