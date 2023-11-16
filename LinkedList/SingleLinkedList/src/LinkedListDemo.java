import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {

    }
    public static <T extends Comparable<T>> MyLinkedList<T> mergeSorted(MyLinkedList<T> list1, MyLinkedList<T> list2) {
        MyLinkedList<T> mergedList = new MyLinkedList<>(); // Create a new linked list to hold the merged result
        Node<T> current1 = list1.getHead().getNext(); // Start at the first node of list1, skipping the dummy head node
        Node<T> current2 = list2.getHead().getNext(); // Start at the first node of list2, skipping the dummy head node
        Node<T> current = mergedList.getHead(); // Start at the head of the merged list
        // Merge the nodes from list1 and list2 in sorted order
        while (current1 != null && current2 != null) {
            if (current1.getData().compareTo(current2.getData()) < 0) {
                current.setNext(new Node<>(current1.getData())); // Add the smaller node to the merged list
                current1 = current1.getNext(); // Move to the next node in list1
            } else {
                current.setNext(new Node<>(current2.getData())); // Add the smaller node to the merged list
                current2 = current2.getNext(); // Move to the next node in list2
            }
            current = current.getNext(); // Move to the next node in the merged list
        }
        // If there are remaining nodes in list1, add them to the merged list
        while (current1 != null) {
            current.setNext(new Node<>(current1.getData()));
            current1 = current1.getNext();
            current = current.getNext();
        }
        // If there are remaining nodes in list2, add them to the merged list
        while (current2 != null) {
            current.setNext(new Node<>(current2.getData()));
            current2 = current2.getNext();
            current = current.getNext();
        }
        return mergedList; // Return the merged list
    }

}