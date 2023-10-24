public class InsertionSort<T extends Comparable<T>> {
    // Method to perform insertion sort on an array of elements
    public static <T extends Comparable<T>> void insertionSort(T[] list) {
        T current; // Variable to hold the current element
        int j; // Variable to track the position for insertion

        // Loop over each element in the array
        for (int i = 0; i < list.length; i++) {
            current = list[i]; // Store the current element
            j = i - 1; // Start from the previous element

            // Move elements of list[0..i-1], that are greater than current, to one position ahead of their current position
            while (j >= 0 && list[j].compareTo(current) > 0) {
                list[j + 1] = list[j]; // Shift the greater element to the right
                j--; // Move to the previous element
            }

            // Insert the current element at the correct position
            list[j + 1] = current;
        }
    }
}
