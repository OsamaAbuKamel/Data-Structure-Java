package com.example.projectii;

public class CStack<T extends Comparable<T>> implements Stackable<T> {
    CursorLinkedList<T> list;
    private int size;

    public CStack(int size) {
        this.size = size;
        list = new CursorLinkedList<T>(size);
    }

    public void push(T data, int top) {
        list.insertAtHead(data, top);
    }

    public T pop(int top) {
        return list.deleteAtHead(top);
    }

    public T peek(int top) {
        return list.getFirst(top);
    }

    public void clear(int top) {
        while (!isEmpty(top)) {
            pop(top);
        }
    }

    public void clear() {
        list = new CursorLinkedList<T>(size);
    }

    public boolean isEmpty(int top) {
        return list.isEmpty(top);
    }

    public int createStack() {
        return list.createList();
    }

    public T get(int top, int i) {
        return list.get(top, i);
    }
}