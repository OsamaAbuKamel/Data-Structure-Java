import java.util.Arrays;

public class MargeSort {
    public static void main(String[] args) {
        int[] data = { 5, 3, 8, 4, 2, 7, 1, 6 };
        margeSort(data);
        // printing the sorted array
        System.out.println(Arrays.toString(data));
    }

    public static void margeSort(int[] data) {
        margeSort(data, 0, data.length - 1);
    }

    private static void margeSort(int[] data, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            margeSort(data, start, mid);
            margeSort(data, mid + 1, end);
            marge(data, start, mid, end);
        }
    }

    private static void marge(int[] data, int start, int mid, int end) {
        // Create a new array to store the merged data
        int temp[] = new int[end - start + 1];
        // Initialize the left and right pointers
        int i = start, j = mid + 1, k = 0;
        // Merge the data in the array
        while (i <= mid && j <= end) {
            // Compare the data at the left and right pointers
            if (data[i] <= data[j]) {
                // If the data at the left pointer is smaller, add it to the temp array
                temp[k++] = data[i++];
            } else {
                // If the data at the right pointer is smaller, add it to the temp array
                temp[k++] = data[j++];
            }
        }
        // Add the remaining data from the left pointer
        while (i <= mid) {
            temp[k++] = data[i++];
        }
        // Add the remaining data from the right pointer
        while (j <= end) {
            temp[k++] = data[j++];
        }
        // Update the original array with the merged data
        for (i = start; i <= end; i++) {
            data[i] = temp[i - start];
        }
    }
}
