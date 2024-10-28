import java.util.List;

/**
 * Given an array of integers nums, sort the array in ascending order and return
 * it.
 * <p>
 * You must solve the problem without using any built-in functions in O(nlog(n))
 * time complexity and with the smallest space complexity possible.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Explanation: After sorting the array, the positions of some numbers are not
 * changed (for example, 2 and 3), while the positions of other numbers are changed (
 * for example, 1 and 5).
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * Explanation: Note that the values of nums are not necessairly unique.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 5 * 10⁴
 * -5 * 10⁴ <= nums[i] <= 5 * 10⁴
 * <p>
 * <p>
 * Related Topics Array Divide and Conquer Sorting Heap (Priority Queue) Merge
 * Sort Bucket Sort Radix Sort Counting Sort 👍 6549 👎 802
 */
       
/*
 2024-10-25 12:01:32
*/

class SortAnArray {
    public static void main(String[] args) {
        Solution solution = new SortAnArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //divide and conquer , merge sort
        int[] tmp;

        public int[] sortArray(int[] nums) {
            tmp = new int[nums.length];
            partitionMerge(nums, 0, nums.length - 1);
            return nums;
        }

        public void partitionMerge(int[] nums, int left, int right) {
            if (left >= right) return;
            //divide：分左右元素分别比较
            int mid = (left + right) / 2;
            partitionMerge(nums, left, mid);
            partitionMerge(nums, mid + 1, right);

            //conquer：合并
            int i = left, j = mid + 1;
            int tmpIndx = 0;
            while (i <= mid && j <= right) {
                if (nums[i] <= nums[j]) {
                    tmp[tmpIndx++] = nums[i++];
                } else {
                    tmp[tmpIndx++] = nums[j++];
                }
            }
            while (i <= mid) {
                tmp[tmpIndx++] = nums[i++];
            }
            while (j <= right) {
                tmp[tmpIndx++] = nums[j++];
            }
            //收集tmp中的有序元素
            for (int k = 0; k < right-left+1; k++) {
                nums[left + k] = tmp[k];
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}