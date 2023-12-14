package com.example.projectii;
public interface Stackable<T> extends Iterable<T> {
    void push(T data);
    T pop();
    T peek();
    void clear();
    boolean isEmpty();
}
