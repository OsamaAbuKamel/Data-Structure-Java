public class SelectionSort<T extends Comparable<T>> {
    // Method to perform selection sort on an array of elements
    public static <T extends Comparable<T>> void selection(T[] list) {
        int minIndex; // Variable to hold the index of the smallest element
        T temp; // Temporary variable for swapping elements
        // Loop over each element in the array
        for (int i = 0; i < list.length - 1; i++) {
            minIndex = i; // Assume the first element is the smallest
            // Loop over the rest of the elements to find the smallest
            for (int j = i + 1; j < list.length; j++) {
                // If a smaller element is found, update minIndex
                if (list[j].compareTo(list[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // If the smallest element is not the first element, swap them
            if (i != minIndex) {
                temp = list[i]; // Store the current element in temp
                list[i] = list[minIndex]; // Replace the current element with the smallest element
                list[minIndex] = temp; // Replace the smallest element with the value from temp (original current
                                       // element)
            }
        }
    }
}
