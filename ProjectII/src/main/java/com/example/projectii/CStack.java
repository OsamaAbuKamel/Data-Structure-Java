package com.example.projectii;

public class CStack<T extends Comparable<T>> {
    CursorLinkedList<T> list;

    public CStack(int size) {
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

    public boolean isEmpty(int top) {
        return list.isEmpty(top);
    }

    public int createStack(){
        return list.createList();
    }

    public static void main(String[] args) {
        CStack<Integer> stack = new CStack<Integer>(52);
        int head = stack.createStack();
        stack.push(1, head);
        stack.push(2, head);
        stack.push(3, head);

        System.out.println(stack.peek(head));
        System.out.println(stack.pop(head));
        System.out.println(stack.pop(head));
        System.out.println(stack.pop(head));
        System.out.println(stack.pop(head));

    }
}