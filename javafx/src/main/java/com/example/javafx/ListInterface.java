package com.example.javafx;

public interface ListInterface <T extends Comparable<T>>{
    
     void add(T data);
     void remove(T data);
    public T getIndex(int index);
    
    void printList();
    int find(T data);
    
}
