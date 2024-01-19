package com.example.projectiii;

import java.util.ArrayList;
import java.util.Iterator;

public class BST<T extends Comparable<T>> implements Iterable<T> {
    private TNode<T> root;

    public void add(T data) {
        if (isEmpty()) {
            root = new TNode<>(data);
        } else {
            add(data, root);
        }
    }

    public int size() {
        return size(root);
    }

    public int countParent() {
        return countParent(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    public boolean isFull() {
        return isFull(root);
    }

    public void traversePostOrder() {
        traverseInPost(root);
    }

    public void traversePreOrder() {
        traverseInPre(root);
    }

    public int height() {
        return height(root);
    }

    public T smallest() {
        return smallest(root);
    }

    public T largest() {
        return largest(root);
    }

    public String traverseLevelOrder() {
        StringBuilder builder = new StringBuilder();
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            builder.append(traverseLevelOrder(root, i));
        }
        return builder.toString();
    }

    public T search(T data) {
        // start from root node
        TNode<T> curr = root;
        // loop until data is found or node is null
        while (curr != null && !curr.data.equals(data)) {
            // if data is less than current node data
            if (data.compareTo(curr.data) < 0) {
                // move to left
                curr = curr.left;
                // if data is greater than current node data
            } else if (data.compareTo(curr.data) > 0)
                // move to right
                curr = curr.right;
        }
        // if data is not found
        if (curr == null)
            // return null
            return null;
        // if data is found
        else
            // return data
            return curr.data;
    }

    public boolean isComplete() {
        if (root == null) {
            return true;
        }
        int index = 0;
        int nodeCount = countParent(root);
        return isComplete(root, index, nodeCount);
    }

    public T get(int index) {
        return get(root, index);
    }

    protected T get(TNode<T> curr2, int index) {
        // Create a new ArrayList to store the values
        ArrayList<T> list = new ArrayList<>();
        // Call the inOrder method to add the values to the ArrayList
        inOrder(curr2, list);
        // Return the value at the specified index
        return list.get(index);
    }

    private void inOrder(TNode<T> curr2, ArrayList<T> list) {
        // Check if the current node is null
        if (curr2 == null) {
            // Return if it is
            return;
        }
        // Recursively call the inOrder method on the left child
        inOrder(curr2.left, list);
        // Add the current node's data to the list
        list.add(curr2.data);
        // Recursively call the inOrder method on the right child
        inOrder(curr2.right, list);
    }

    protected boolean isComplete(TNode<T> node, int index, int nodeCount) {
        if (node == null) {
            return true;
        }
        if (index >= nodeCount) {
            return false;
        }
        boolean isLeftComplete = isComplete(node.left, 2 * index + 1, nodeCount);
        boolean isRightComplete = isComplete(node.right, 2 * index + 2, nodeCount);
        return isLeftComplete && isRightComplete;
    }

    protected boolean isFull(TNode<T> node) {
        // Check if the node is null
        if (node == null)
            return true;
        // Check if the node has no children
        if (node.left == null && node.right == null)
            return true;
        // Check if the node has both children
        if (node.left != null && node.right != null)
            return (isFull(node.left) && isFull(node.right));
        // Return false if the node has only one child
        return false;
    }

    protected String traverseLevelOrder(TNode<T> root2, int level) {
        StringBuilder builder = new StringBuilder();
        // Check if the root node is null
        if (root2 == null) {
            return null;
        }
        // Check if the level is 1
        if (level == 1) {
            // Print the data of the root node
            // System.out.print(root2.data + " ");
            builder.append(root2.data).append(" ");
        } else if (level > 1) {
            // Traverse the left and right subtrees
            builder.append(traverseLevelOrder(root2.left, level - 1));
            builder.append(traverseLevelOrder(root2.right, level - 1));
        }
        return builder.toString();
    }

    // Method to return the largest node in the tree
    protected T largest(TNode<T> node) {
        // If the node is null, return null
        if (node == null)
            return null;
        // If the node has a right child, return the smallest node in the right subtree
        if (node.hasRight())
            return smallest(node.right);
        // Otherwise, return the node's data
        else
            return node.data;
    }

    // Method to return the smallest node in the tree
    protected T smallest(TNode<T> node) {
        // Check if the node is null
        if (node == null)
            // Return null if the node is null
            return null;
        // Check if the node has a left child
        if (node.hasLeft())
            // Return the smallest node in the left subtree if the node has a left child
            return smallest(node.left);
        else
            // Return the node if the node has no left child
            return node.data;
    }

    // Method to return the height of the tree
    protected int height(TNode<T> node) {
        // Check if the node is null
        if (node == null)
            return 0;
        // If the node is a leaf, return 1
        if (node.isLeaf())
            return 1;
        // Initialize the left and right heights to 0
        int left = 0;
        int right = 0;
        // If the node has a left child, set the left height to the height of the left
        // child
        if (node.hasLeft()) {
            left = height(node.left);
        }
        // If the node has a right child, set the right height to the height of the
        // right child
        if (node.hasRight()) {
            right = height(node.right);
        }
        // Return the larger of the left and right heights, plus 1
        return (left > right) ? (left + 1) : (right + 1);
    }

    protected void traverseInPre(TNode<T> node) {
        if (node != null) {
            if (node.left != null)
                traverseInPre(node.left);
            if (node.right != null)
                traverseInPre(node.right);
        }
        System.out.print(node.data + " ");
    }

    protected void traverseInPost(TNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            if (node.left != null)
                traverseInPost(node.left);
            if (node.right != null)
                traverseInPost(node.right);
        }
    }

    public T remove(T data) {
        root = remove(root, data);
        return data;
    }

    protected TNode<T> remove(TNode<T> node, T data) {
        TNode<T> curr = node;
        TNode<T> parent = null;
        boolean isLeftChild = false;
        if (isEmpty()) {
            return null;
        }
        // Find the node to be removed
        while (curr != null && !curr.data.equals(data)) {
            parent = curr;
            if (data.compareTo(curr.data) < 0) {
                curr = curr.left;
                isLeftChild = true;
            } else {
                curr = curr.right;
                isLeftChild = false;
            }
        }
        if (curr == null) {
            return node; // Node not found
        }
        // Case 1: Node is a leaf
        if (!curr.hasLeft() && !curr.hasRight()) {
            if (curr == node) {
                node = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // Case 2: Node has only left child
        else if (curr.hasLeft() && !curr.hasRight()) {
            if (curr == node) {
                node = curr.left;
            } else if (isLeftChild) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
        }
        // Case 2: Node has only right child
        else if (!curr.hasLeft() && curr.hasRight()) {
            if (curr == node) {
                node = curr.right;
            } else if (isLeftChild) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        }
        // Case 3: Node has both left and right children
        else {
            TNode<T> successor = getSuccessor(curr);
            if (curr == node) {
                node = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = curr.left;
        }
        return node;
    }

    private TNode<T> getSuccessor(TNode<T> node) {
        TNode<T> parentOfSuccessor = node;
        TNode<T> successor = node;
        TNode<T> current = node.right;
        while (current != null) {
            parentOfSuccessor = successor;
            successor = current;
            current = current.left;
        }
        if (successor != node.right) {
            parentOfSuccessor.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    protected void add(T data, TNode<T> node) {
        // Check if the data is greater than or equal to the node data
        if (data.compareTo(node.data) >= 0) {
            // If the node has no right child, create a new node with the data
            if (!node.hasRight()) {
                node.right = new TNode<>(data);
            } else
                // Otherwise, call the add method recursively with the data and the right child
                add(data, node.right);
            // If the data is less than the node data
        } else if (!node.hasLeft()) {
            // Create a new node with the data
            node.left = new TNode<>(data);
        } else
            // Otherwise, call the add method recursively with the data and the left child
            add(data, node.left);
    }

    protected int size(TNode<T> node) {
        // Check if the node is null
        if (node == null)
            // Return 0 if the node is null
            return 0;
        // Check if the node is a leaf
        if (node.isLeaf())
            // Return 1 if the node is a leaf
            return 1;
        // Return the size of the left and right subtrees
        return 1 + size(node.left) + size(node.right);
    }

    protected int countParent(TNode<T> node) {
        // Check if the node is null or a leaf
        if (node == null || node.isLeaf())
            // Return 0 if the node is null or a leaf
            return 0;
        // Return 1 plus the number of parent nodes on the left and right subtrees
        return 1 + countParent(node.left) + countParent(node.right);
    }

    protected void traverseInOrder(TNode<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.print(node.getData() + " ");
            traverseInOrder(node.getRight());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new OrderIterator(root);
    }

    protected class OrderIterator implements Iterator<T> {
        private TNode<T> curr;
        private SLinkedList<T> list = new SLinkedList<>();
        int index = 0;

        public OrderIterator(TNode<T> root) {
            curr = root;
            LevelOrder(curr);
        }

        private void LevelOrder(TNode<T> node) {
            if (node == null) {
                return;
            }
            SQueue<TNode<T>> queue = new SQueue<>();
            queue.enqueue(node);
            while (!queue.isEmpty()) {
                TNode<T> temp = queue.dequeue();
                list.insertAtLast(temp.getData());
                if (temp.getLeft() != null) {
                    queue.enqueue(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.enqueue(temp.getRight());
                }
            }
        }

        @Override
        public boolean hasNext() {
            if (index < list.length()) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            return list.get(index++);
        }
    }
}
