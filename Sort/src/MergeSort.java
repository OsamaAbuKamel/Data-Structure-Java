public class MergeSort<T extends Comparable<T>> {
    // Method to perform merge sort on an array of elements
    public static <T extends Comparable<T>> void mergeSort(T[] list) {
        if (list.length > 1) {
            // Create first half
            T[] firstHalf = (T[]) new Comparable[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf); // Recursively sort the first half

            // Create second half
            int secondHalfLength = list.length - list.length / 2;
            T[] secondHalf = (T[]) new Comparable[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf); // Recursively sort the second half

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list);
        }
    }

    // Method to merge two sorted lists
    private static <T extends Comparable<T>> void merge(T[] list1, T[] list2, T[] temp) {
        int current1 = 0, current2 = 0, current3 = 0; // Current indices in list1, list2, and temp

        // While both lists have elements left
        while (current1 < list1.length && current2 < list2.length) {
            // Compare elements and add the smaller one to the temp list
            if (list1[current1].compareTo(list2[current2]) < 0)
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        // Copy remaining elements from list1, if any
        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }

        // Copy remaining elements from list2, if any
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
    }
}
