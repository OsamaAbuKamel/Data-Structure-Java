package queue;

import queue.Stack.SLStack;

public class StackQueue<T extends Comparable<T>> implements Queueable<T> {
    SLStack<T> stack = new SLStack<T>();
    SLStack<T> tempStack = new SLStack<T>();

    @Override
    public void enqueue(T data) {
        if (stack.isEmpty()) {
            stack.push(data);
        }
        if (stack.isEmpty() == false) {
            while (stack.isEmpty() == false) {
                T element = stack.pop();
                tempStack.push(element);
            }
            stack.push(data);
            while (tempStack.isEmpty() == false) {
                T element = tempStack.pop();
                stack.push(element);
            }
        }
    }

    @Override
    public T dequeue() {
        return stack.pop();
    }

    @Override
    public T getFront() {
        return stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void clear() {
        stack.clear();
    }
}