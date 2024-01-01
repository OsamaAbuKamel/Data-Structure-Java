
public class BST<T extends Comparable<T>> {
    private TNode<T> root;

    public T search(T data) {
        return search(data, root);
    }

    private T search(T data, TNode<T> node) {
        if (node != null) {
            int comp = node.data.compareTo(data);
            if (comp == 0) {
                return node.data;
            } else if (comp > 0 && node.hasLeft()) {
                search(data, node.left);
            } else if (comp < 0 && node.hasRight()) {
                search(data, node.right);
            }
        }
        return null;
    }

    public void add(T data) {
        if (isEmpty()) {
            root = new TNode<>(data);
        } else {
            add(data, root);
        }
    }

    private void add(T data, TNode<T> node) {
        if (data.compareTo(node.data) >= 0) {
            if (!node.hasRight()) {
                node.right = new TNode<>(data);
            } else
                add(data, node.right);
        } else if (!node.hasLeft()) {
            node.left = new TNode<>(data);
        } else
            add(data, node.left);
    }

    public T remove(T data) {
        TNode<T> curr = root;
        TNode<T> parent = root;
        boolean isLeftChild = false;
        if (isEmpty()) {
            return null;
        }
        while (curr != null && !curr.data.equals(data)) {
            parent = curr;
            if (data.compareTo(curr.data) < 0) {
                curr = curr.left;
                isLeftChild = true;
            } else {
                curr = curr.right;
                isLeftChild = false;
            }
        }
        if (curr == null) {
            return null;
        }
        // case 1 : node is leaf
        if (!curr.hasLeft() && !curr.hasRight()) {
            if (curr == root) {
                root = null;
            } else {
                if (isLeftChild) {
                    parent.left = null;
                } else
                    parent.right = null;
            }
        }
        // Case 2 broken down further into 2 separate cases
        else if (curr.hasLeft()) { // current has left child only
            if (curr == root) {
                root = curr.left;
            } else if (isLeftChild) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
        } else if (curr.hasRight()) { // current has right child only
            if (curr == root) {
                root = curr.right;
            } else if (isLeftChild) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        } else {
            TNode<T> successor = getSuccessor(curr);
            if (curr == root)
                root = successor;
            else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = curr.left;
        }

        return curr.data;
    }

    private TNode<T> getSuccessor(TNode<T> node) {
        TNode<T> parentOfSuccessor = node;
        TNode<T> successor = node;
        TNode<T> current = node.right;
        while (current != null) {
            parentOfSuccessor = successor;
            successor = current;
            current = current.left;
        }
        if (successor != node.right) { // fix successor connections
            parentOfSuccessor.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(TNode<T> node) {
        if (node != null) {
            if (node.left != null)
                traverseInOrder(node.left);
            System.out.println(node.data + " ");
            if (node.right != null)
                traverseInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(30);
        tree.add(20);
        tree.add(40);
        tree.add(70);
        tree.add(60);
        tree.traverseInOrder();
        System.out.println("-------------");
        tree.remove(30);
        tree.traverseInOrder();
    }
}
