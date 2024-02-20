package Hash.HashMap;

public interface MyMap<K,V>{
        void clear();
        boolean containsKey(K key);
        boolean containsValue(V value);
        V get(K key);
        boolean isEmpty();
        void put(K key, V value);
        V remove(K key);
        int size();
}
