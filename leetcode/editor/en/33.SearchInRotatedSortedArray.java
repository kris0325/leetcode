/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown
 * pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k],
 * nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For
 * example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1
 * ,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target, return
 * the index of target if it is in nums, or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * <p>
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * <p>
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 5000
 * -10⁴ <= nums[i] <= 10⁴
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -10⁴ <= target <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Binary Search 👍 26768 👎 1627
 */
       
/*
 2024-10-14 21:54:51
*/

class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 即：有序数组 旋转后构成 旋转数组（局部有序）
         * 旋转数组特点: 中间分成左右两部分，一定有一半是有序的，所以可以使用二分搜索查找
         */
        //tc:o(logn)
        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) return -1;
            if (nums.length == 1) return nums[0] == target ? 0 : -1;
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                //1.如果左边有序
                if (nums[left] <= nums[mid]) {
                    if (nums[left] <= target && target < nums[mid]) {
                        //target落在左边，去左边搜索
                        right = mid - 1;
                    } else {
                        //target落在右边，去右边搜索
                        left = mid + 1;
                    }
                }//2.如果右边有序
                else {
                    //target落在右边
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        //target落在左边
                        right = mid - 1;
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}