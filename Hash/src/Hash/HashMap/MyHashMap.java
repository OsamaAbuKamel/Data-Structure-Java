package Hash.HashMap;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashMap<K, V> implements MyMap<K, V>, Iterable<Entry<K, V>> {
    private static int INITIAL_CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] buckets;
    private int size;

    public MyHashMap() {
        this(INITIAL_CAPACITY);
    }

    public MyHashMap(int capacity) {
        this.buckets = new LinkedList[capacity];
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        LinkedList<Entry<K, V>> entries = new LinkedList<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                entries.addAll(bucket);
            }
        }
        return entries.iterator();
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getIndex(K key) {
        // Check if the key is null, if it is return 0
        if (key == null)
            return 0;
        // Otherwise return the hash code of the key modulo the number of buckets
        return Math.abs(key.hashCode()) % buckets.length;
    }

    @Override
    public boolean containsValue(V value) {
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (entry.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void put(K key, V value) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        LinkedList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
        size++;
    }

    @Override
    public V remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V value = entry.getValue();
                    bucket.remove(entry);
                    size--;
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    sb.append(entry.toString());
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();
        hashMap.put(1, "one");
        hashMap.put(2, "two");
        hashMap.put(3, "three");
        for (Entry<Integer, String> entry : hashMap) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("Size: " + hashMap.size());
        System.out.println("Get value for key 2: " + hashMap.get(2));
        hashMap.remove(2);
        System.out.println("Size after removing key 2: " + hashMap.size());
        for (Entry<Integer, String> entry : hashMap) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
