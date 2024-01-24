package Hash;
public class Main {
    public static void main(String[] args) {
        SeparateHashing<Integer> hashing = new SeparateHashing<>(50);
        hashing.insert(8);
        hashing.insert(9);
        hashing.insert(98);
        hashing.insert(10);
        System.out.println(hashing);

        // for (Integer integer : hashing) {
        //     System.out.println(integer);
        // }







        // System.out.println(hashing.contains(8));
        // System.out.println(hashing.delete(8));
        // System.out.println(hashing);
        // for (int i = 0; i < 100; i++) {
        //     int data = (int) (Math.random() * 100);
        //     hashing.insert(data);
        // }
        // System.out.println(hashing);
        // int sum = 0;
        // for (int i = 0; i < hashing.length(); i++) {
        //     sum += hashing.getTable()[i].length();
        // }
        // int avg = 0;
        // avg = sum / hashing.length();
        // System.out.println("avg: " + avg);
        // System.out.println();
        }
}