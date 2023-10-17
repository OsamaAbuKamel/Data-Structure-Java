package com.example.project_0_java_revision;

import java.util.Arrays;

public class MyList<T extends Comparable<T>> implements ListInterface<T> {
    private final int INITIAL_SIZE = 2;
    // Declare an array of type T and assign it to the variable array
    private T[] array;
    // Declare a variable to store the count of elements in the array
    private int count;

    public MyList() {
        // Create a new array of type Comparable with the initial size
        array = (T[]) new Comparable[INITIAL_SIZE];
        // Set the count to 0
        count = 0;
    }

    @Override
    public void add(T data) {
        // Check if data is null
        if (data == null) {
            throw new NullPointerException("Data is null");
        }
        // If the array is full, double the size
        if (count >= array.length)
            doublingSize();
        array[count++] = data;
    }

    // This method doubles the size of the array and returns a copy of the array
    private void doublingSize() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    public T delete(int index) {
        // Check if index is within bounds
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        // Store the element at the index to be deleted
        T deletedElement = array[index];
        // Shift elements to the left to fill the empty space
        for (int i = index; i < count - 1; i++) {
            array[i] = array[i + 1];
        }
        // Set the last element to null to release the reference
        array[count - 1] = null;
        // Decrement the count
        count--;
        // Return the element that was deleted
        return deletedElement;
    }
    

    @Override
    public int find(T data) {
        // Loop through the array
        for (int i = 0; i < count; i++) {
            // Check if the data is equal to the array element
            if (data.compareTo(array[i]) == 0)
                // Return the index if it is found
                return i;
        }
        // Return -1 if the data is not found
        return -1;
    }

    public void sort() {
        // Loop through the array
        for (int i = 0; i < array.length; i++) {
            // Loop through the array again, starting from the next element
            for (int j = 0; j < array.length - i - 1; j++) {
                // Check if the elements are not null
                if (array[j] != null && array[j + 1] != null) {
                    // Compare the elements and swap if the first element is greater than the second
                    if (array[j].compareTo(array[j + 1]) > 0) {
                        T temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
    }

    @Override
    public void clear() {
        // Set the array to a new Comparable array of initial size
        array = (T[]) new Comparable[INITIAL_SIZE];
        // Set the count to 0
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        // Check if the count of the array is 0
        return count == 0;
    }

    @Override
    public int size() {
        // Return the count of the array
        return count;
    }

    @Override
    public T get(int index) {
        // Return the element at the given index
        return array[index];
    }

    public int getCount() {
        // Return the count of the array
        return this.count;
    }

    @Override
    public String toString() {
        // Create a string to store the elements of the array
        String s = "";
        // Loop through the array and add each element to the string
        for (int i = 0; i < count; i++) {
            s += array[i] + " ";
        }
        // Return the string
        return s;
    }
}
