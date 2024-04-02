import java.util.LinkedList;

public class MergeSortLL {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(4);
        list.add(3);
        list.add(0);
        list.add(1);
        System.out.println("Before sorting: " + list);
        list = mergeSort(list);
        System.out.println("After sorting: " + list);
    }

    /**
     * Sorts a linked list of integers in ascending order using merge sort.
     *
     * @param list The linked list to be sorted.
     * @return A new linked list containing the sorted integers.
     */
    public static LinkedList<Integer> mergeSort(LinkedList<Integer> list) {
        if (list.size() <= 1) {
            return list; // Base case: If the list has one or zero elements, it's already sorted.
        }

        int mid = list.size() / 2;

        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();

        // Divide the list into two halves
        for (int i = 0; i < mid; i++) {
            left.add(list.get(i));
        }
        for (int i = mid; i < list.size(); i++) {
            right.add(list.get(i));
        }

        // Sort the halves recursively
        left = mergeSort(left);
        right = mergeSort(right);

        // Merge the sorted halves
        return merge(left, right);
    }

    /**
     * Merges two sorted linked lists of integers into a single sorted linked list.
     *
     * @param left  A sorted linked list.
     * @param right A sorted linked list.
     * @return A new linked list containing the merged and sorted integers.
     */
    private static LinkedList<Integer> merge(LinkedList<Integer> left, LinkedList<Integer> right) {
        LinkedList<Integer> result = new LinkedList<>();
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.getFirst() <= right.getFirst()) {
                result.add(left.removeFirst());
            } else {
                result.add(right.removeFirst());
            }
        }

        // Add any remaining elements from left or right
        result.addAll(left);
        result.addAll(right);

        return result;
    }

}
