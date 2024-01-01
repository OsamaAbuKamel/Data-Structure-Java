package com.example.projectii;

import java.util.NoSuchElementException;

public class CursorLinkedList<T extends Comparable<T>> {
    private Node<T>[] cursorArray;

    public CursorLinkedList(int size) {
        this.cursorArray = new Node[size];
        initialization();
    }

    public int initialization() {
        for (int i = 0; i < cursorArray.length - 1; i++)
            cursorArray[i] = new Node<>(null, i + 1);
        cursorArray[cursorArray.length - 1] = new Node<>(null, 0);
        return 0;
    }

    public int malloc() {
        int p = cursorArray[0].next;
        cursorArray[0].next = cursorArray[p].next;
        return p;
    }

    public void free(int p) {
        cursorArray[p] = new Node<>(null, cursorArray[0].next);
        cursorArray[0].next = p;
    }

    public boolean isNull(int l) {
        return cursorArray[l] == null;
    }

    public boolean isEmpty(int l) {
        return cursorArray[l].next == 0;
    }

    public boolean isLast(int p) {
        return cursorArray[p].next == 0;
    }

    public T getFirst(int l) {
        // Check if the list is empty
        if (isEmpty(l)) {
            throw new NoSuchElementException("List is empty");
        }
        // Get the data from the first node
        int firstNodeIndex = cursorArray[l].next;
        return cursorArray[firstNodeIndex].data;
    }

    public int createList() {
        int l = malloc();
        if (l == 0)
            System.out.println("Error: Out of space!!!");
        else
            cursorArray[l] = new Node<>(null, 0);
        return l;
    }

    public void insertAtHead(T data, int l) {
        if (isNull(l)) // list not created
            return;
        int p = malloc();
        if (p != 0) {
            cursorArray[p] = new Node<>(data, cursorArray[l].next);
            cursorArray[l].next = p;
        } else
            System.out.println("Error: Out of space!!!");
    }

    public T deleteAtHead(int l) {
        if (isNull(l) || isEmpty(l)) {
            return null;
        } else {
            int p = cursorArray[l].next;
            cursorArray[l].next = cursorArray[p].next;
            return cursorArray[p].data;
        }
    }

    public void traversList(int l) {
        System.out.print("list_" + l + "-->");
        while (!isNull(l) && !isEmpty(l)) {
            l = cursorArray[l].next;
            System.out.print(cursorArray[l] + "-->");
        }
        System.out.println("null");
    }

    public int find(T data, int l) {
        while (!isNull(l) && !isEmpty(l)) {
            l = cursorArray[l].next;
            if (cursorArray[l].data.equals(data))
                return l;
        }
        return -1; // not found
    }

    public int findPrevious(T data, int l) {
        while (!isNull(l) && !isEmpty(l)) {
            if (cursorArray[cursorArray[l].next].data.equals(data))
                return l;
            l = cursorArray[l].next;
        }
        return -1; // not found
    }

    public Node<T> delete(T data, int l) {
        int p = findPrevious(data, l);
        if (p != -1) {
            int c = cursorArray[p].next;
            Node<T> temp = cursorArray[c];
            cursorArray[p].next = temp.next;
            free(c);
        }
        return null;
    }

    public T get(int l, int index) {
        int p = cursorArray[l].next;
        int count = 0;
        while (!isNull(p) || !isEmpty(p)) {
            if (count == index) {
                return cursorArray[p].data;
            }
            p = cursorArray[p].next;
            count++;
        }
        return null;
    }

    public int size(int l) {
        int count = 0;
        while (!isNull(l) && !isEmpty(l)) {
            l = cursorArray[l].next;
            count++;
        }
        return count;
    }
}