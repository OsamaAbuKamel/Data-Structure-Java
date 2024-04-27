public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        int target = 30;
        int index = search(arr, target);
        if (index != -1) {
            System.out.println("Target element found at index: " + index);
        } else {
            System.out.println("Target element not found.");
        }
    }

    public static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Target found, return its index
            }
        }
        return -1; // Target not found
    }
}
