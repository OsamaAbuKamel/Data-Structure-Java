package Hash;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Hash.LinkedList.SLinkedList;

public class SeparateHashing<T extends Comparable<T>> implements Iterable<T> {
    private int m;
    private SLinkedList<T>[] table;
    
    public SeparateHashing(int n) {
        this.m = n / 5;
        table = new SLinkedList[m];
        for (int i = 0; i < m; i++) {
            table[i] = new SLinkedList<T>();
        }
    }
    
    public void insert(T data) {
        int index = data.hashCode() % m;
        table[index].insertAtHead(data);
    }
    
    public T search(T data) {
        int index = data.hashCode() % m;
        return table[index].search(data);
    }
    
    public boolean contains(T data) {
        int index = data.hashCode() % m;
        return table[index].search(data) != null;
    }
    
    public T delete(T data) {
        int index = data.hashCode() % m;
        return table[index].deleteSorted(data);
    }
    
    int length() {
        return table.length;
    }
    
    public SLinkedList<T>[] getTable() {
        return table;
    }
    
    @Override
    public String toString() {
        String s = "index\t data\n";
        for (int i = 0; i < m; i++) {
            s += i + "\t" + table[i].toString() + "\n";
        }
        return s;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new SeparateHashingIterator<>();
    }
    
    private class SeparateHashingIterator<T extends Comparable<T>> implements Iterator<T> {
        // index is used to keep track of the current element in the list
        private int index;
        // iterator is used to iterate through the list
        private Iterator<T> iterator;
        
        public SeparateHashingIterator() {
            index = 0;
            // get the first element in the list
            iterator = getIndex();
        }
        
        private Iterator<T> getIndex() {
            // loop through the list until the first non-empty element is found
            while (index < m && table[index].isEmpty()) {
                index++;
            }
            // return the iterator of the first non-empty element
            return index < m ? (Iterator<T>) table[index].iterator() : null;
        }
        
        @Override
        public boolean hasNext() {
            // check if the list is empty
            if (iterator == null) {
                return false;
            }
            // check if the current element is non-empty
            if (iterator.hasNext()) {
                return true;
            } else {
                // increment the index and get the next element in the list
                index++;
                iterator = getIndex();
                // check if the next element is non-empty
                return iterator != null && iterator.hasNext();
            }
        }
        
        @Override
        public T next() {
            // check if there are any elements in the list
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list");
            }
            // return the next element in the list
            return iterator.next();
        }
    }
}
