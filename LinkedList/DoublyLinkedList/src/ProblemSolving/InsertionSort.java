package ProblemSolving;

import src.DLinkedList;
import src.DNode;

public class InsertionSort {
    public static <T extends Comparable<T>> void insertionSort(DLinkedList<T> list) {
        DNode<T> current = list.getHead().getNext();
        DNode<T> prev = new DNode<T>(null);
        while (current != null) {
            DNode<T> next = current.getNext();
            prev = current.getPrev();
            prev.setNext(next);
            if (next != null) {
                next.setPrev(prev);
            }
            DNode<T> pos = list.getHead();
            while (pos.getNext() != null && pos.getNext().getData().compareTo(current.getData()) < 0) {
                pos = pos.getNext();
            }
            DNode<T> posNext = pos.getNext();
            pos.setNext(current);
            current.setNext(posNext);
            current.setPrev(pos);
            if (current.getNext() != null) {
                current.getNext().setPrev(current);
            }
            current = next;
        }
    }
}
