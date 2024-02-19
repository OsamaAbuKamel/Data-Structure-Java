package Hash;

public class Main {
    public static void main(String[] args) {
        QuadraticReHashing<Integer> quadraticReHashing = new QuadraticReHashing<>(3);
        quadraticReHashing.add(7);
        quadraticReHashing.add(3);
        quadraticReHashing.add(3);
        quadraticReHashing.add(1);
        quadraticReHashing.add(1);
        quadraticReHashing.add(2);
        quadraticReHashing.add(1);
        System.out.println(quadraticReHashing);
    }
}