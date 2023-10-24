public class BubbleSort<T extends Comparable<T>> {
    // Method to perform bubble sort on an array of elements
    public static <T extends Comparable<T>> void bubble(T[] arr) {
        T temp; // Temporary variable for swapping elements
        // Outer loop to iterate over each element in the array
        for (int i = 0; i < arr.length - 1; i++) {
            // Inner loop to compare each element with its adjacent element
            for (int j = 0; j < arr.length - i - 1; j++) {
                // If the next element is smaller than the current element, swap them
                if (arr[j + 1].compareTo(arr[j]) < 0) {
                    temp = arr[j]; // Store the current element in temp
                    arr[j] = arr[j + 1]; // Replace the current element with the next element
                    arr[j + 1] = temp; // Replace the next element with the value from temp (original current element)
                }
            }
        }
    }
}
