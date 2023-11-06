public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        DLinkedList<Integer> list = new DLinkedList<>();
        list.addFirst(3);
        list.addFirst(7);
        list.addFirst(1);
        list.addFirst(9);
        list.addFirst(2);
        list.addLast(77);
        list.addLast(0);
        // list.addSorted(5);
        // list.addSorted(7);
        // list.addSorted(1);
        // list.addSorted(9);
        // list.addSorted(2);
        System.out.println(list);
        System.out.println("-----------Sort------------");
        list.sort();
        System.out.println(list);
    }
}