package problems;

import list.CursorLinkedList;

public class IsPalindrome {
    CursorLinkedList<?> list;
    public IsPalindrome(CursorLinkedList<?> list){
        this.list=list;
    }
    public boolean isPalindrome(int head){
        if (list.isEmpty(head)) {
            return true;
        }
        int slow = head;
        int fast = head;

        while (list.getNodeArray()[fast].getNext() != 0 && list.getNodeArray()[list.getNodeArray()[fast].getNext()].getNext() != 0) {
            slow = list.getNodeArray()[slow].getNext();
            fast = list.getNodeArray()[fast].getNext();
        }

        int prev = 0;
        int current = slow;
        while (current != 0) {
            int next = list.getNodeArray()[current].getNext();
            list.getNodeArray()[current].setNext(prev);
            prev = current;
            current = next;
        }
        int firstHalf = head;
        int secondHalf = prev;
        while (firstHalf != 0 && secondHalf != 0) {
            if (list.getNodeArray()[firstHalf].getData() != list.getNodeArray()[secondHalf].getData()) {
                return false;
            }
            firstHalf = list.getNodeArray()[firstHalf].getNext();
            secondHalf = list.getNodeArray()[secondHalf].getNext();
        }
        return true;
        
    }
}
