package src;
public class App {
    public static void main(String[] args) throws Exception {
        AVL<Integer> tree = new AVL<>();
        tree.insert(2017);
        tree.insert(2018);
        tree.insert(2019);
        tree.insert(2020);
        tree.insert(2021);
        tree.insert(2022);
        tree.insert(2023);
        for (Integer integer : tree) {
            System.out.println(integer);
        }



    }
}
