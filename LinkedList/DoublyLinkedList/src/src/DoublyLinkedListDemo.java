package src;

import ProblemSolving.RemoveDuplicates;

public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        DLinkedList<Integer> list = new DLinkedList<>();
        list.addFirst(3);
        list.addFirst(7);
        list.addFirst(1);
        list.addFirst(9);
        list.addFirst(2);
        list.addFirst(2);
        // list.addSorted(4);
        // list.addSorted(7);
        list.addLast(9);
        list.addLast(2);
        // list.sort();
        System.out.println(list);
        System.out.println("-----------Sort------------");
        // list.removeDuplicates();
        RemoveDuplicates.removeDuplicates(list);
        System.out.println(list);
    }
}