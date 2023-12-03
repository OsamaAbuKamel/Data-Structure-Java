package ProblemSolving;

import src.CDlinkedList;
import src.DNode;

public class RadixSort {

    public static void radixSort(CDlinkedList<Integer> list) {
        int max = getMax(list); 
        int exp = 1;
        while (max / exp > 0) {
            CDlinkedList<Integer>[] buckets = new CDlinkedList[10];
            for(DNode<Integer> node = list.getHead(); node != null; node = node.getNext()) {
                int digit = (node.getData() / exp) % 10;
                if(buckets[digit] == null) {
                    buckets[digit] = new CDlinkedList<>(); 
                }
                buckets[digit].addAtLast(node.getData()); 
            }
            for(int i = 0; i < 10; i++) {
                if(buckets[i] != null) {
                    DNode<Integer> curr = buckets[i].getHead();  
                    while(curr != null) {
                        list.addAtLast(curr.getData());
                        curr = curr.getNext();
                    }
                }
            }
            exp *= 10;
        }
    }
    
    private static int getMax(CDlinkedList<Integer> list) {
        int max = Integer.MIN_VALUE;
        for(DNode<Integer> node = list.getHead(); node != null; node = node.getNext()) {
            if(node.getData() > max) {
                max = node.getData(); 
            }
        }
        return max;
    }
}
