public class LinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.insertSorted(3);
        list.insertSorted(5);
        list.insertSorted(7);
        MyLinkedList<Integer> list1 = new MyLinkedList<>();
        
        list1.insertSorted(6);
        list1.insertSorted(2);
        list1.insertSorted(1);;
        System.out.println( list.mergeSorted(list,list1));
        
        
        
    }
}