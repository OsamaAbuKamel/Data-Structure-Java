package com.example.projectiii;

import java.util.Iterator;

public class AVL<T extends Comparable<T>> extends BST<T> {
    private TNode<T> root;

    public void insert(T data) {
        if (isEmpty()) {
            root = new TNode<>(data);
        } else {
            TNode<T> rootNode = root;
            addEntry(data, rootNode);
            root = rebalance(rootNode);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new OrderIterator(root);
    }

    public T get(int index) {
        return get(root, index);
    }

    public int height() {
        return height(root);
    }

    public T search(T data) {
        // Initialize curr to root
        TNode<T> curr = root;
        // Loop until data is found or curr is null
        while (curr != null && !curr.data.equals(data)) {
            // If data is less than curr.data, move to left
            if (data.compareTo(curr.data) < 0) {
                curr = curr.left;
                // If data is greater than curr.data, move to right
            } else if (data.compareTo(curr.data) > 0)
                curr = curr.right;
        }
        // If data is not found, return null
        if (curr == null)
            return null;
        // Otherwise, return the data
        else
            return curr.data;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    public String traverseLevelOrder() {
        StringBuilder builder = new StringBuilder();
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            builder.append(traverseLevelOrder(root, i));
        }
        return builder.toString();
    }

    public T delete(T data) {
        root = delete(root, data);
        return data;
    }

    private TNode<T> delete(TNode<T> node, T data) {
        TNode<T> temp = super.remove(node, data);
        if (temp != null) {
            TNode<T> rootNode = node;
            node = rebalance(rootNode);
        }
        return temp;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    public void clear() {
        root = null;
    }

    private TNode<T> rotateRight(TNode<T> node) {
        TNode<T> nodeC = node.left;
        node.left = nodeC.right;
        nodeC.right = node;
        return nodeC;
    }

    private TNode<T> rotateLeft(TNode<T> node) {
        TNode<T> nodeC = node.right;
        node.right = nodeC.left;
        nodeC.left = node;
        return nodeC;
    }

    private TNode<T> rotateRightLeft(TNode<T> node) {
        TNode<T> nodeC = node.right;
        node.right = rotateRight(nodeC);
        return rotateLeft(node);
    }

    private TNode<T> rotateLeftRight(TNode<T> node) {
        TNode<T> nodeC = node.left;
        node.left = rotateLeft(nodeC);
        return rotateRight(node);
    }

    private TNode<T> rebalance(TNode<T> nodeN) {
        int diff = getHeightDifference(nodeN);
        if (diff > 1) { // addition was in node's left subtree
            if (getHeightDifference(nodeN.left) > 0)
                nodeN = rotateRight(nodeN);
            else
                nodeN = rotateLeftRight(nodeN);
        } else if (diff < -1) { // addition was in node's right subtree
            if (getHeightDifference(nodeN.right) < 0)
                nodeN = rotateLeft(nodeN);
            else
                nodeN = rotateRightLeft(nodeN);
        }
        return nodeN;
    }

    private int getHeightDifference(TNode<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private void addEntry(T data, TNode<T> rootNode) {
        // assert that the root node is not null
        assert rootNode != null;
        // if the data is less than the root node's data
        if (data.compareTo(rootNode.data) < 0) {
            // if the root node has a left child
            if (rootNode.hasLeft()) {
                // set the left child to the root node's left child
                TNode<T> left = rootNode.left;
                // call the addEntry method recursively with the left child
                addEntry(data, left);
                // set the root node's left child to the rebalanced left child
                rootNode.left = rebalance(left);
            } else
                // if the root node does not have a left child, set the root node's left child
                // to a new TNode with the data
                rootNode.left = new TNode<>(data);
        } else {
            // if the data is greater than or equal to the root node's data
            if (rootNode.hasRight()) {
                // set the right child to the root node's right child
                TNode<T> right = rootNode.right;
                // call the addEntry method recursively with the right child
                addEntry(data, right);
                // set the root node's right child to the rebalanced right child
                rootNode.right = rebalance(right);
            } else {
                // if the root node does not have a right child, set the root node's right child
                // to a new TNode with the data
                rootNode.right = new TNode<>(data);
            }
        }
    }

    public String toString() {
        return toStringInorder(root);
    }

    public String toStringInorder(TNode<T> root) {
        String s = "";
        if (root == null) {
            return "";
        }
        s += toStringInorder(root.left);
        s += root.toString();
        s += toStringInorder(root.right);
        return s;
    }
}
