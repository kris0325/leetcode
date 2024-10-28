import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Given an array of integers nums and an integer limit, return the size of the
 * longest non-empty subarray such that the absolute difference between any two
 * elements of this subarray is less than or equal to limit.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute
 * diff is |2-7| = 5 <= 5.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * 1 <= nums[i] <= 10⁹
 * 0 <= limit <= 10⁹
 * <p>
 * <p>
 * Related Topics Array Queue Sliding Window Heap (Priority Queue) Ordered Set
 * Monotonic Queue 👍 4082 👎 194
 */
       
/*
 2024-09-28 18:20:12
*/

class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public static void main(String[] args) {
        Solution solution = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit().new Solution();

        int[] nums = {8,2,4,7};
        solution.longestSubarray(nums ,4);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //monotonic queue
        //参考239.sliding window maximum，
        // 維護窗口極大值-極小值<=limit，就需要2個極值就需要維護2個單調隊列
        public int longestSubarray(int[] nums, int limit) {
            //遞減隊列
            Deque<Integer> maxDeque = new ArrayDeque<>();
            //遞增隊列
            Deque<Integer> minDeque = new ArrayDeque<>();
            int res = 0;
            int left = 0;
            for (int i = 0; i < nums.length; i++) {
                //為求窗口中的最大值，維護遞減隊列,隊首元素即為最大值
                while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[i]) {
                    maxDeque.pollLast();
                }
                maxDeque.offerLast(nums[i]);
                //為求窗口中的最小值，維護遞增隊列,隊首元素即為最小值
                while (!minDeque.isEmpty() && minDeque.peekLast() > nums[i]) {
                    minDeque.pollLast();
                }
                minDeque.offerLast(nums[i]);

                //維護窗口大小為limit，則需要移除窗口左邊的元素，
                // 那麼就需要判斷左邊的元素，是當前窗口的最大值，還是最小值，即屬於那個隊列（隊首元素）
                if (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                    if (nums[left] == maxDeque.peekFirst()) {
                        maxDeque.pollFirst();
                    }
                    if (nums[left] == minDeque.peekFirst()) {
                        minDeque.pollFirst();
                    }
                    left++;
                }
                //計算長度
                res = Math.max(res, i - left + 1);
            }
            return res;
        }
    }

    //two pointer + treemap  solution
    // tc: 0(nlog(n))
//leetcode submit region end(Prohibit modification and deletion)

}