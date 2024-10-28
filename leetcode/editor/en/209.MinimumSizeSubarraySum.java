/**
 * Given an array of positive integers nums and a positive integer target, return
 * the minimal length of a subarray whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem
 * constraint.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= target <= 10⁹
 * 1 <= nums.length <= 10⁵
 * 1 <= nums[i] <= 10⁴
 * <p>
 * <p>
 * <p>
 * Follow up: If you have figured out the
 * O(n) solution, try coding another solution of which the time complexity is
 * O(n log(n)).
 * <p>
 * Related Topics Array Binary Search Sliding Window Prefix Sum 👍 12631 👎 440
 */
       
/*
 2024-08-05 12:14:17
*/

class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        Solution solution = new MinimumSizeSubarraySum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int reslut = Integer.MAX_VALUE;
            //滑动窗口数组值之和
            int sum = 0;
            //左边慢指针即滑动窗口起始位置
            int slow = 0;
            //滑动窗口的长度
            int subLength = 0;
            //右边快指针即滑动窗口结束位置
            for (int fast = 0; fast < nums.length; fast++) {
                sum += nums[fast];
                //当每次循环判断滑动窗口的截断出的数组值之和sum满足条件时， 进行比较并更新滑动窗口长度 subLength，
                //然后下一次循环，先更新sum，后更新向右移动慢指针，
                while (sum >= target) {
                    subLength = fast - slow + 1;
                    reslut = Math.min(reslut, subLength);
                    // sum -= nums[slow++];
                    //sum -= nums[slow++];注意顺序，即：
                    sum -= nums[slow];
                    slow++;
                }
            }
            return reslut == Integer.MAX_VALUE ? 0 : reslut;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}