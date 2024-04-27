public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 5;
        int result = binarySearch(arr, target);
        if (result == -1) {
            System.out.println("Element not found");
        }
        else {
            System.out.println("Element found at index " + result);
        }        
    }
    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, 0, arr.length - 1, target);
    }
    private static int binarySearch(int[] arr,int left,int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) /2 ;
        if (arr[mid] == target) {
            return mid;
        }
        else if (target < arr[mid]) {
            return binarySearch(arr, left, mid - 1, target);
        }
        return binarySearch(arr, mid+1, right, target);
    }
}

