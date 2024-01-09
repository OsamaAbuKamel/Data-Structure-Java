public class BST<T extends Comparable<T>> {
    private TNode<T> root;

    public T search(T data) {
        return search(data, root);
    }

    public void add(T data) {
        if (isEmpty()) {
            root = new TNode<>(data);
        } else {
            add(data, root);
        }
    }

    public int size() {
        return size(root);
    }

    public int countParent() {
        return countParent(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    public boolean isFull() {
        return isFull(root);
    }

    public void traversePostOrder() {
        traverseInPost(root);
    }

    public void traversePreOrder() {
        traverseInPre(root);
    }

    public int height() {
        return height(root);
    }

    public T smallest() {
        return smallest(root);
    }

    public T largest() {
        return largest(root);
    }

    public void traverseLevelOrder() {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            traverseLevelOrder(root, i);
        }
    }

    public boolean isComplete() {
        if (root == null) {
            return true;
        }
        int index = 0;
        int nodeCount = countParent(root);
        return isComplete(root, index, nodeCount);
    }

    private boolean isComplete(TNode<T> node, int index, int nodeCount) {
        if (node == null) {
            return true;
        }
        if (index >= nodeCount) {
            return false;
        }
        boolean isLeftComplete = isComplete(node.left, 2 * index + 1, nodeCount);
        boolean isRightComplete = isComplete(node.right, 2 * index + 2, nodeCount);
        return isLeftComplete && isRightComplete;
    }

    private boolean isFull(TNode<T> node) {
        if (node == null)
            return true;
        if (node.left == null && node.right == null)
            return true;
        if (node.left != null && node.right != null)
            return (isFull(node.left) && isFull(node.right));
        return false;
    }

    private void traverseLevelOrder(TNode<T> root2, int level) {
        // Check if the root node is null
        if (root2 == null) {
            return;
        }
        // Check if the level is 1
        if (level == 1) {
            // Print the data of the root node
            System.out.print(root2.data + " ");
        } else if (level > 1) {
            // Traverse the left and right subtrees
            traverseLevelOrder(root2.left, level - 1);
            traverseLevelOrder(root2.right, level - 1);
        }
    }

    // Method to return the largest node in the tree
    public T largest(TNode<T> node) {
        // If the node is null, return null
        if (node == null)
            return null;
        // If the node has a right child, return the smallest node in the right subtree
        if (node.hasRight())
            return smallest(node.right);
        // Otherwise, return the node's data
        else
            return node.data;
    }

    // Method to return the smallest node in the tree
    public T smallest(TNode<T> node) {
        // Check if the node is null
        if (node == null)
            // Return null if the node is null
            return null;
        // Check if the node has a left child
        if (node.hasLeft())
            // Return the smallest node in the left subtree if the node has a left child
            return smallest(node.left);
        else
            // Return the node if the node has no left child
            return node.data;
    }

    // Method to return the height of the tree
    public int height(TNode<T> node) {
        // Check if the node is null
        if (node == null)
            return 0;
        // If the node is a leaf, return 1
        if (node.isLeaf())
            return 1;
        // Initialize the left and right heights to 0
        int left = 0;
        int right = 0;
        // If the node has a left child, set the left height to the height of the left
        // child
        if (node.hasLeft()) {
            left = height(node.left);
        }
        // If the node has a right child, set the right height to the height of the
        // right child
        if (node.hasRight()) {
            right = height(node.right);
        }
        // Return the larger of the left and right heights, plus 1
        return (left > right) ? (left + 1) : (right + 1);
    }

    private void traverseInPre(TNode<T> node) {
        if (node != null) {
            if (node.left != null)
                traverseInPre(node.left);
            if (node.right != null)
                traverseInPre(node.right);
        }
        System.out.print(node.data + " ");
    }

    private void traverseInPost(TNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            if (node.left != null)
                traverseInPost(node.left);
            if (node.right != null)
                traverseInPost(node.right);
        }
    }

    public T remove(T data) {
        TNode<T> curr = root;
        TNode<T> parent = null;
        boolean isLeftChild = false;
        if (isEmpty()) {
            return null;
        }
        // Find the node to be removed
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
            return null; // Node not found
        }
        // Case 1: Node is a leaf
        if (!curr.hasLeft() && !curr.hasRight()) {
            if (curr == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // Case 2: Node has only left child
        else if (curr.hasLeft() && !curr.hasRight()) {
            if (curr == root) {
                root = curr.left;
            } else if (isLeftChild) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
        }
        // Case 2: Node has only right child
        else if (!curr.hasLeft() && curr.hasRight()) {
            if (curr == root) {
                root = curr.right;
            } else if (isLeftChild) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        }
        // Case 3: Node has both left and right children
        else {
            TNode<T> successor = getSuccessor(curr);
            if (curr == root) {
                root = successor;
            } else if (isLeftChild) {
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
        if (successor != node.right) {
            parentOfSuccessor.left = successor.right;
            successor.right = node.right;
        }
        return successor;
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

    private int size(TNode<T> node) {
        if (node == null)
            return 0;
        if (node.isLeaf())
            return 1;
        return 1 + size(node.left) + size(node.right);
    }

    private int countParent(TNode<T> node) {
        if (node == null || node.isLeaf())
            return 0;
        return 1 + countParent(node.left) + countParent(node.right);
    }

    private void traverseInOrder(TNode<T> node) {
        if (node != null) {
            if (node.left != null)
                traverseInOrder(node.left);
            System.out.print(node.data + " ");
            if (node.right != null)
                traverseInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(6);
        System.out.println(tree.remove(1));
        // System.out.println(tree.smallest());
        // System.out.println(tree.height());
        // System.out.println("==================POST=================");
        // tree.traversePostOrder();
        // System.out.println();
        // System.out.println("==================PRE=================");
        // tree.traversePreOrder();
        // System.out.println();
        System.out.println("==================IN=================");
        tree.traverseInOrder();
        // System.out.println();
        // System.out.println("==================LEVEL=================");
        // tree.traverseLevelOrder();
        // System.out.println(tree.isComplete());
        // System.out.println(tree.isFull());
    }
}
