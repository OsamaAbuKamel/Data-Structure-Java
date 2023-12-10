package problems;

import list.CursorLinkedList;

public class SwapNode<T extends Comparable<T>> {
    CursorLinkedList<T> list ;

    public SwapNode(CursorLinkedList<T> list) {
        this.list=list; 
    }

    public void swap(T x, T y, int head) {
        if(head == 0 || list.find(x, head) == -1 || list.find(y, head) == -1) {
            return; 
        }
        int xPrev = list.findPrevious(x, head);
        int xCurr = list.getNodeArray()[xPrev].getNext();

        int yPrev = list.findPrevious(y, head);
        int yCurr = list.getNodeArray()[yPrev].getNext();

        if (xPrev != head) {
            list.getNodeArray()[xPrev].setNext(yCurr);
        } else {
            head = yCurr;
        }
        if (yPrev != head) {
            list.getNodeArray()[yPrev].setNext(xCurr);
        } else {
            head = xCurr;
        }
        int temp = list.getNodeArray()[xCurr].getNext();
        list.getNodeArray()[xCurr].setNext(list.getNodeArray()[yCurr].getNext());
        list.getNodeArray()[yCurr].setNext(temp);
    }
}
