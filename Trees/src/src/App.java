package src;

public class App {
    public static void main(String[] args) throws Exception {
        BST<Integer> bst = new BST<>();
        bst.add(10);
        bst.add(15);
        bst.add(7);
        bst.add(20);
        bst.add(3);
        bst.add(8);
        bst.add(13);
        bst.bfs();
        System.out.println("\n----------------");
        bst.dfsTraversal();
        System.out.println("\n----------------");
        bst.dfsRecursive();
    }
}
