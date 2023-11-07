package ProblemSolving;

import src.DLinkedList;
import src.DNode;

public class InsertionSort {
    // Sorts the given linked list using the insertion sort algorithm.
    public static <T extends Comparable<T>> void insertionSort(DLinkedList<T> list) {
        // Get the first node in the linked list
        DNode<T> current = list.getHead().getNext();
        // Create a new node to represent the previous node in the linked list
        DNode<T> prev = new DNode<T>(null);
        // Iterate through the linked list until the current node is null
        while (current != null) {
            // Get the next node
            DNode<T> next = current.getNext();
            // Set the previous node of the current node to be the previous node of the
            // linked list
            prev.setNext(current);
            // If the next node in the linked list is not null, set its previous node to be
            // the previous node of the current node
            if (next != null) {
                next.setPrev(prev);
            }
            // Get the position of the current node in the sorted linked list
            DNode<T> pos = list.getHead();
            while (pos.getNext() != null && pos.getNext().getData().compareTo(current.getData()) < 0) {
                pos = pos.getNext();
            }
            // Get the next node of the position node
            DNode<T> posNext = pos.getNext();
            // Set the current node as the next node of the position node
            pos.setNext(current);
            // Set the current node as the previous node of the position node
            current.setPrev(pos);
            // If the next node of the position node is not null, set its previous node to
            // be the current node
            if (posNext != null) {
                posNext.setPrev(current);
            }
            // Set the next node of the current node to be the next node of the position
            // node
            current.setNext(posNext);
            // Move to the next node in the linked list
            current = next;
        }
    }
}