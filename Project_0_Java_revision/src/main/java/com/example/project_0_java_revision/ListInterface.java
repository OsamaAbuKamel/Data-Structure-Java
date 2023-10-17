package com.example.project_0_java_revision;

public interface ListInterface<T extends Comparable<T>> {
    // This method adds a data element to the end of the list.
    void add(T data);

    // This method removes an element from the list
    T delete(int index);

    // This method returns i if the data is found in the set, -1 otherwise
    int find(T data);

    // clear the array
    void clear();

    // check if the array is empty
    boolean isEmpty();

    // return the size of the array
    int size();

    // Sorts the array using the MergeSort algorithm
    void sort();

    // return the data at the given index
    T get(int index);
}
