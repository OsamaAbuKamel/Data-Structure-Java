package src;

import ProblemSolving.RadixSort;

public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        CDlinkedList<Integer> list = new CDlinkedList<>();
        list.addAtHead(2);
        list.addAtHead(70);
        list.addAtHead(3);
        list.addAtHead(2);
        list.addAtHead(5);
        list.addAtHead(80);
        System.out.println(list);
        System.out.println("------------------------");

        for (Integer integer : list) {
            System.err.println(integer);
            
        }
        // // list.traverse();
        // System.out.println(list.get(3));
        // System.out.println("-------------------------------");
        // list.removeSorted(5);
        // System.out.println(list);
    }
}