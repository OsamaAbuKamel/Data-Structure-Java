public class QuickSort {
    /**
     * Sorts an array of integers in ascending order using the QuickSort algorithm.
     * 
     * @param arr The array to be sorted.
     */
    public static void quickSort(int[] arr) {
        // Set the initial low and high indices for the entire array.
        int low = 0;
        int high = arr.length - 1;
        // Call the recursive quickSort function to sort the array.
        quickSort(arr, low, high);
    }

    /**
     * Recursive QuickSort function that partitions the array and sorts sub-arrays.
     * 
     * @param arr  The array to be sorted.
     * @param low  The starting index of the sub-array to sort.
     * @param high The ending index of the sub-array to sort.
     */
    private static void quickSort(int[] arr, int low, int high) {
        // Base case: If the sub-array has zero or one element, it's already sorted.
        if (low < high) {
            // Partition the array and get the pivot element's final position.
            int pi = partition(arr, low, high);
            // Recursively sort the sub-array on the left of the pivot (elements less than
            // the pivot).
            quickSort(arr, low, pi - 1);
            // Recursively sort the sub-array on the right of the pivot (elements greater
            // than the pivot).
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Partitions the sub-array by selecting a pivot element and rearranging
     * elements based on the pivot.
     * 
     * @param arr  The array containing the sub-array to partition.
     * @param low  The starting index of the sub-array.
     * @param high The ending index of the sub-array.
     * @return The final index of the pivot element in the sorted sub-array.
     */
    private static int partition(int[] arr, int low, int high) {
        // Choose the last element of the sub-array as the pivot.
        int pivot = arr[high];
        // Index 'i' keeps track of the element before which all elements are less than
        // or equal to the pivot.
        int i = (low - 1);
        // Iterate through the sub-array (excluding the pivot element).
        for (int j = low; j < high; j++) {
            // If the current element is less than or equal to the pivot.
            if (arr[j] <= pivot) {
                i++;
                // Swap the current element with the element at index 'i + 1' (effectively
                // placing it before the pivot).
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap the pivot element with the element at index 'i + 1' (placing it in its
        // final sorted position).
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        // Return the final index of the pivot element in the sorted sub-array.
        return i + 1;
    }
}
