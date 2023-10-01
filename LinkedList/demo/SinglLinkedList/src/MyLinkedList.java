
public class MyLinkedList<T extends Comparable<T>> {
    private Node<T> head;

    public MyLinkedList() {
        head = new Node<>(null);
    }

    public void insertAtHead(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head.next;
        head.next = newNode;
    }

    public void insertSorted(T data) {
        Node<T> newNode = new Node<>(data);
        Node<T> curr = head.next;
        Node<T> prev = head;
        while (curr != null && data.compareTo(curr.data) > 0) {
            prev = curr;
            curr = curr.next;
        }
        newNode.next = curr;
        prev.next = newNode;
    }

    public void deleteSorted(T data) {
        Node<T> curr = head.next;
        Node<T> prev = head;
        while (curr != null && data.compareTo(curr.data) > 0) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != null && curr.data.equals(data)) {
            prev.next = curr.next;
        }
    }

    // public Node<T> search(T data) {
    // Node<T> curr = head.next;
    // while (curr != null) {
    // if (curr.getData() == data)
    // return curr;
    // curr = curr.next;
    // }
    // return null;
    // }
    public Node<T> search(T data) {
        return search(data, head.next);
    }

    public Node<T> search(T data, Node<T> curr) {
        if (curr == null || curr.getData().compareTo(data) > 0)
            return null;
        if (curr.data.equals(data))
            return curr;
        return search(data, curr.next);
    }

    public void traversList() {
        Node<T> curr = head.next;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    public int length() {
        int length = 0;
        Node<T> curr = head.next;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }
    // public int length() {
    // return length(head.next);
    // }

    // private int length(Node node) {
    // if (node == null)
    // return 0;
    // return 1 + length(node.next);
    // }
}
