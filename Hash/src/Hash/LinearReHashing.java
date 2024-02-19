package Hash;

public class LinearReHashing<T extends Comparable<T>> {
    private HashNode<T>[] table;
    private int m;
    private int size = 0; // Add a size variable to keep track of the number of elements

    public LinearReHashing(int n) {
        this.m = getPrime(2 * n);
        this.table = new HashNode[m];
        for (int i = 0; i < m; i++) {
            table[i] = new HashNode<>();
        }
    }

    public void add(T data) {
        if (data == null) {
            return; // Do not add null values
        }
        if (size >= m / 2) {
            rehash();
        }
        int key = data.hashCode();
        int h0 = key % m;
        int index = h0, i = 0;
        while (!table[index].isEmpty()) {
            i++;
            index = (h0 + i) % m;
        }
        table[index] = new HashNode<>(data);
        size++;
    }

    public T delete(T data) {
        if (size <= m / 4) { // If the load factor is less than or equal to 0.25
            shrink(); // Halve the size of the hash table
        }
        int key = data.hashCode();
        int h0 = key % m;
        int index = h0, i = 0;
        while (!table[index].isEmpty()) {
            if (table[index].getData().compareTo(data) == 0) {
                table[index].setDeleted();
                size--; // Decrement the size after deleting an element
                return data;
            } else {
                i++;
                index = (h0 + i) % m;
            }
        }
        return null;
    }

    public T search(T data) {
        int key = data.hashCode();
        int h0 = key % m;
        int index = h0, i = 0;
        while (!table[index].isEmpty()) {
            if (!table[index].isDeleted() && table[index].getData().compareTo(data) == 0) {
                return data;
            } else {
                i++;
                index = (h0 + i) % m;
            }
        }
        return null;
    }

    public void rehash() {
        HashNode<T>[] oldTable = table;
        allocateArray(getPrime(2 * table.length));
        size = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && !oldTable[i].isEmpty() && !oldTable[i].isDeleted()) {
                add(oldTable[i].getData());
            }
        }
    }

    private void allocateArray(int prime) {
        m = prime;
        table = new HashNode[m];
        for (int i = 0; i < m; i++) {
            table[i] = new HashNode<>();
        }
    }

    private void shrink() {
        int oldM = m;
        HashNode<T>[] oldTable = table;
        m = getPrime(m / 2);
        table = new HashNode[m];
        for (int i = 0; i < m; i++) {
            table[i] = new HashNode<>();
        }
        size = 0; // Reset size to zero, since we're rebuilding the table
        for (int i = 0; i < oldM; i++) {
            if (!oldTable[i].isEmpty() && !oldTable[i].isDeleted()) {
                add(oldTable[i].getData());
            }
        }
    }

    private int getPrime(int num) {
        if (num <= 1) {
            return 2; // Handle numbers less than or equal to 1
        }
        // Optimized prime checking loop:
        for (int i = num + 1; i <= 2 * num; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                return i;
            }
        }
        return -1; // No prime found within the checked range
    }

    @Override
    public String toString() {
        String s = "index\tdata" + "\n";
        for (int i = 0; i < m; i++) {
            s += i + "\t" + table[i] + "\n";
        }
        return s + " ";
    }
}
