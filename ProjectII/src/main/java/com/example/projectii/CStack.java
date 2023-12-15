package com.example.projectii;

public class CStack<T extends Comparable<T>> {
    CursorLinkedList<T> list;

    public CStack(int size) {
        list = new CursorLinkedList<T>(size);
    }

    public void push(T data, int top) {
        list.insertFirst(data, top);
    }

    public T pop(int top) {
        return list.deleteFirst(top);
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

    public CursorLinkedList<T> getList() {
        return this.list;
    }

    public void setList(CursorLinkedList<T> list) {
        this.list = list;
    }

    public static void main(String[] args) {
        CStack<Integer> stack = new CStack<Integer>(52);
        int head = stack.getList().createList();
        int head1 = stack.getList().createList();
        stack.push(1, head);
        stack.push(2, head);
        stack.push(3, head);
        stack.push(34, head);
        stack.push(35, head);
        while (!stack.isEmpty(head)) {
            System.out.println(stack.pop(head));

            // }
            // Stack<Integer> stack = new Stack<>();
            // stack.push(1);
            // stack.push(2);
            // stack.push(3);
            // while (!stack.isEmpty()) {
            // System.out.println(stack.pop()); {

            // }
            // System.out.println(stack.pop());
            // System.out.println(stack.pop());
            // System.out.println(stack.pop());
            // System.out.println(stack.pop());
            // System.out.println(stack.pop());

        }
    }
}