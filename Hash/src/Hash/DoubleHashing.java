package Hash;

public class DoubleHashing<T extends Comparable<T>> {
    private HashNode<T>[] table;
    private int m;
    
    public DoubleHashing(int n) {
        this.m = getPrime(2 * n);
        this.table = new HashNode[m];
        for (int i = 0; i < m; i++) {
            table[i] = new HashNode<>();
        }
    }
    
    public void add(T data) {
        int key = data.hashCode();
        int h1 = key % m;  // First hash function
        int h2 = 1 + (key % (m - 1));  // Second hash function (relatively prime to m)
        
        int index = h1, i = 0;
        while (!table[index].isEmpty()) {
            i++;
            index = (h1 + i * h2) % m;  // Apply double hashing calculation
        }
        
        table[index] = new HashNode<>(data);
    }
    
    
    public T delete(T data) {
        int key = data.hashCode();
        int h0 = key % m;
        int index = h0, i = 0;
        while (!table[index].isEmpty()) {
            if (table[index].getData().compareTo(data) == 0) {
                table[index].setDeleted();
                return data;
            } else {
                i++;
                index = (h0 + (i * i)) % m;
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
                index = (h0 + (i * i)) % m;
            }
        }
        return null;
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
    
    public static void main(String[] args) {
        DoubleHashing<Integer> hash = new DoubleHashing<>(5);
        hash.add(94);
        hash.add(74);
        hash.add(13);
        hash.add(87);
        hash.add(66);
        System.out.println(hash);
    }
}
