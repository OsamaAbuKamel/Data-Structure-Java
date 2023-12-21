package com.example.projectii;

public class Node<T extends Comparable<T>> {
    T data;
    int next;

    public Node(T data, int next) {
        this.data = data;
        this.next = next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public String toString() {
        return "[" + data + " , " + next + "]";
    }
}
