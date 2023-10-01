package com.example.javafx;

import com.example.javafx.ListInterface;

import java.util.Arrays;

public class MyList<T extends Comparable<T>> implements ListInterface<T> {
    
    T[] list;
    int count;
    
    public MyList(int size) {
        list = (T[]) new Comparable[size];
        count = 0;
    }
    
    @Override
    public void add(T data) {
        if (count < list.length) {
            list[count] = data;
            count++;
        } else{
            System.out.println("List Is Full");
    }}
    
    
    @Override
    public void remove(T data) {
        for (int i = 0; i < count; i++) {
            if (list[i].equals(data)) {
                list[i] = list[count - 1];
                count--;
            } else
                System.out.println("Element not found");
        }
    }
    
    @Override
    public int find(T data) {
        for (int i = 0; i < count; i++) {
            if (data.compareTo(list[i]) == 0)
                return i;
        }
        return -1;
    }
    
    public void clear() {
        list = (T[]) new Comparable[list.length];
    }
    
    @Override
    public T getIndex(int index) {
        return list[index];
    }
    
    public int size() {
        return count;
    }
    
    @Override
    public void printList() {
        //print list
        for (int i = 0; i < count; i++) {
            System.out.println(list[i]);
        }
    }
    
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < count; i++)
            s += list[i] + "\t\n";
        return s;
    }
}
