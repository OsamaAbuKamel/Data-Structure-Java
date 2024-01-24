package Hash;

public class QuadraticHashing<T extends Comparable<T>> {
    private HashNode<T>[] table;
    private int m;
    
    public QuadraticHashing(int n) {
        this.m = getPrime(2 * n);
        this.table = new HashNode[m];
        for (int i = 0; i < m; i++) {
            table[i] = new HashNode<>();
        }
    }
    
    public void add(T data) {
        int key = data.hashCode();
        int h0 = key % m;
        int index = h0, i = 0;
        while (!table[index].isEmpty()) {
            i++;
            index = (h0 + (i * i)) % m;
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
        QuadraticHashing<Integer> hash = new QuadraticHashing<>(5);
        hash.add(66);
        hash.add(89);
        hash.add(5);
        hash.add(16);
        hash.add(60);
        System.out.println(hash);
    }
}