public class FindRotationCount {
    /*
     * Input: nums = [8, 9, 10, 2, 5, 6]
     * Output: The array is rotated 3 times Input:
     * nums = [2, 5, 6, 8, 9, 10]
     * Output: The array is rotated 0 times
     */
    public static void main(String[] args) {
        int[] nums = { 8, 9, 10, 1, 2, 3, 4, 5, 6, 7 };
        System.out.println("Array is rotated " + findRotationCount(nums) + " times");
    }

    public static int findRotationCount(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                return left;
            }
            int mid = (left + right) / 2;
            int next = (mid + 1) % nums.length;
            int prev = (mid - 1 + nums.length) % nums.length;
            if (nums[mid] <= nums[next] && nums[mid] <= nums[prev]) {
                return mid;
            } else if (nums[mid] <= nums[right]) {
                right = mid - 1;
            } else if (nums[mid] >= nums[left]) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
