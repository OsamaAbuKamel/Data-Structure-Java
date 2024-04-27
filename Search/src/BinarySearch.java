public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16};
        int target = 112;
        int index = binarySearch(arr, target);

        if (index != -1) {
            System.out.println("Target element found at index: " + index);
        } else {
            System.out.println("Target element not found.");
        }
    }

    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length-1);
    }
    private static int binarySearch(int[] arr, int target, int low, int high) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, high);
        } else
            return binarySearch(arr, target, low, mid - 1);
    }
}
