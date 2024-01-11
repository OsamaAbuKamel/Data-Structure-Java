package com.example.projectiii;
public class TNode<T extends Comparable<T>> {
    T data;
    TNode<T> left;
    TNode<T> right;

    public TNode(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TNode<T> getLeft() {
        return this.left;
    }

    public void setLeft(TNode<T> left) {
        this.left = left;
    }

    public TNode<T> getRight() {
        return this.right;
    }

    public void setRight(TNode<T> right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
    public boolean hasLeft(){
        return left != null;
    }
    public boolean hasRight(){
        return right != null;
    }
    @Override
    public String toString() {
        return data+" ";
    }

}
