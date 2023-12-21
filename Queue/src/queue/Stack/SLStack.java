package queue.Stack;

import java.util.Iterator;

public class SLStack<T extends Comparable<T>> implements Stackable<T> {
    SLinkedList<T> list = new SLinkedList<>();

    @Override
    public void push(T data) {
        list.insertAtHead(data);
    }

    @Override
    public T pop() {
        return list.deleteAtHead();
    }

    @Override
    public T peek() {
        if (!isEmpty())
            return list.getHead().getData();
        return null;
    }

    public int length() {
        int length = list.length();
        return length;
    }

    @Override
    public void clear() {
        list = new SLinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}