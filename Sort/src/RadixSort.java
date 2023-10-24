import java.util.LinkedList;

public class RadixSort {
    // Method to perform radix sort on an array of integers
    public static void radixSort(Integer[] arr) {
        // Find the maximum number to know the number of digits
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Create output buckets
        LinkedList<Integer>[] output = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            output[i] = new LinkedList<Integer>();
        }

        // Start from the least significant digit
        int exp = 1;
        while (max / exp > 0) {
            // Distribute the numbers to buckets according to their digit at exp's place
            for (int i = 0; i < arr.length; i++) {
                output[(arr[i] / exp) % 10].add(arr[i]);
            }

            // Collect numbers from the buckets and put them back to the array
            int i = 0;
            for (int j = 0; j < 10; j++) {
                while (!output[j].isEmpty()) {
                    arr[i++] = output[j].poll();
                }
            }

            // Move to next significant digit
            exp *= 10;
        }
    }
}
