package ProblemSolving;

import src.DLinkedList;
import src.DNode;

public class RemoveDuplicates {
    // Removes duplicate nodes from the given linked list.
    public static <T extends Comparable<T>> void removeDuplicates(DLinkedList<T> list) {
        // Get the first node in the linked list
        DNode<T> curr = list.getHead().getNext();
        // Iterate through the linked list until the current node is null
        while (curr != null) {
            // Get the next node
            DNode<T> next = curr.getNext();
            // Iterate through the remaining nodes until the next node is null
            while (next != null) {
                // If the current node and the next node have the same data,
                // remove the next node by updating its prev and next references
                if (curr.getData().compareTo(next.getData()) == 0) {
                    next.getPrev().setNext(next.getNext());
                    if (next.getNext() != null) {
                        next.getNext().setPrev(next.getPrev());
                    }
                } else {
                    // Move to the next node
                    curr = next;
                }
                // Move to the next node
                next = next.getNext();
            }
            // Move to the next node
            curr = curr.getNext();
        }
    }
}
