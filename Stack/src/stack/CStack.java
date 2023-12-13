package stack;

import java.util.Iterator;

public  class CStack<T extends  Comparable<T>> implements Stackable<T> {
    CursorLinkedList<T>  list;
    int top;
    public  CStack(int size) {
        list = new CursorLinkedList<T>(size);
        top = list.createList();
    }
    
    
    @Override
    public void push(T data) {
        list.insertAtHead(data,top);
    }
    
    @Override
    public T pop() {
        return list.deleteFirst(top);
    }
    
    @Override
    public T peek() {
        return  list.getFirst(top);
    }
    
   
    @Override
    public void clear() {
        while (!isEmpty()){
            pop();
        }
    }
    
    @Override
    public boolean isEmpty() {
        return isEmpty();
    }
    
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}