package com.example.projectii;

public interface Stackable<T> {
    void push(T data, int l);

    T pop(int l);

    T peek(int l);

    void clear(int l);

    boolean isEmpty(int l);
}
