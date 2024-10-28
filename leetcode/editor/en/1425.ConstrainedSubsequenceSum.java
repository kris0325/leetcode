import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an integer array nums and an integer k, return the maximum sum of a non-
 * empty subsequence of that array such that for every two consecutive integers in
 * the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is
 * satisfied.
 * <p>
 * A subsequence of an array is obtained by deleting some number of elements (can
 * be zero) from the array, leaving the remaining elements in their original order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subsequence is [10, 2, 5, 20].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subsequence must be non-empty, so we choose the largest number.
 * <p>
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= k <= nums.length <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Queue Sliding Window Heap (Priority
 * Queue) Monotonic Queue 👍 2139 👎 103
 */
       
/*
 2024-09-28 10:27:47
*/

class ConstrainedSubsequenceSum {
    public static void main(String[] args) {
        Solution solution = new ConstrainedSubsequenceSum().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        //动态规划（DP） + monotonic queue

        //注意：與Solution1區分，1.使用动态规划計算sum[i]（ sum[i] 代表以索引 i 结尾的子数组的最大可能和，
        // 所以2.窗口大小為k，i - deque.peekFirst() > k (不能用>=k)

        /**
         * sum 数组是一个 动态规划（DP）数组，其中每个元素 sum[i] 代表以索引 i 结尾的子数组的最大可能和。也就是说：
         * <p>
         * sum[i] 的初始值是 nums[i]（因为每个子数组都必须包括 nums[i]）。
         * 如果从某个符合条件的之前的索引（距离不超过 k）加上子数组和能够增加当前 sum[i] 的值，则会将该子数组的和加到 sum[i] 上。
         * 用公式表示：
         * <p>
         * sum[i] = nums[i] + max(0, sum[j])，其中 j 是在窗口范围内（满足 i - j <= k）的能产生最大和的子数组的结束位置。
         */
        public int constrainedSubsetSum(int[] nums, int k) {
            //存储index，，+對首元素j，影響j+k=i的sum[i],所以需要维护递减的（对应的可跳子数组sum和）队列，保證每次當前窗口加的 隊首元素是最大值，從而使sum为可能的最大和
            Deque<Integer> deque = new ArrayDeque<>();
            //sum 数组是一个 动态规划（DP）数组，其中每个元素 sum[i] 代表以索引 i 结尾的子数组的最大可能和。
            int[] sum = new int[nums.length];
            int res = nums[0];
            for (int i = 0; i < nums.length; i++) {
                // Remove elements from the front of the deque if they are out of the window of size k
                while (!deque.isEmpty() && i - deque.peekFirst() > k) {
                    deque.pollFirst();
                }

                // Initialize sum[i] with nums[i]
                sum[i] = nums[i];
                // Add the maximum element from the deque (which stores indices) to sum[i]
                if (!deque.isEmpty()) {
//                    sum[i] += sum[deque.peekFirst()];
                    //計算sum[i]時， 考慮不加入對首和（為負數） ｜  加入對首和
                    sum[i] = Math.max(sum[i], nums[i] + sum[deque.peekFirst()]);
                }
                res = Math.max(res, sum[i]);

                // Maintain a decreasing deque of sums
                while (!deque.isEmpty() && sum[deque.peekLast()] <= sum[i]) {
                    deque.pollLast();
                }
                // Add current index to the deque if sum[i]
                //不论sum是正，負數，都入隊列，在145行 計算sum[i]求最值篩選即可
                deque.offerLast(i);
            }
            return res;
        }
    }



    class Solution2 {

        //monotonic queue
        //注意：與Solution2區分，1.使用累加計算sum[i] 表示的是以索引 i 结尾的子数组的“累加和”
        // 所以2.實際窗口大小為k-1， i - deque.peekFirst() >= k (不能用>k)
        //3.且入隊列時加了剪枝條件：if (sum[i] > 0) {...}

        public int constrainedSubsetSum(int[] nums, int k) {
            //存储index，，+對首元素j，影響j+k=i的sum[i],所以需要维护递减的（对应的可跳子数组sum和）队列，保證每次當前窗口加的 隊首元素是最大值，從而使sum为可能的最大和
            Deque<Integer> deque = new ArrayDeque<>();
            //sum 数组是一个 动态规划（DP）数组，其中每个元素 sum[i] 代表以索引 i 结尾的子数组的最大可能和。
            int[] sum = new int[nums.length];
            int res = nums[0];
            for (int i = 0; i < nums.length; i++) {

                // Initialize sum[i] with nums[i]
                sum[i] = nums[i];
                // Add the maximum element from the deque (which stores indices) to sum[i]
                if (!deque.isEmpty()) {
                    sum[i] += sum[deque.peekFirst()];
                }
                res = Math.max(res, sum[i]);

                // Remove elements from the front of the deque if they are out of the window of size k
                while (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                    deque.pollFirst();
                }

                // Maintain a decreasing deque of sums
                while (!deque.isEmpty() && sum[deque.peekLast()] <= sum[i]) {
                    deque.pollLast();
                }
                // Add current index to the deque if sum[i] is positive
                /**
                 * 这个条件确保队列中只保留对未来子数组和有用的索引 —— 也就是那些能帮助将来子数组和增大的索引。
                 *
                 * 为什么需要 sum[i] > 0？
                 * 如果 sum[i] 是 负数或零，意味着以 i 结尾的子数组对未来的子数组不会产生积极的贡献。将一个负数或零加到未来的子数组中只会减小总和，这与我们希望最大化子数组和的目标相违背。
                 * 因此，如果 sum[i] <= 0，将 i 加入队列是没有意义的，因为它不会帮助将来子数组的和变得更大。
                 * 简而言之：
                 *
                 * 如果 sum[i] <= 0，那么以 i 结尾的子数组不会对未来产生正面贡献，因此我们跳过它，不将其加入队列。
                 * 如果 sum[i] > 0，我们将其加入队列，因为它可能帮助将来子数组和的最大化（当它在窗口范围内时可以被考虑）。
                 *
                 *  即我们希望通过队列来维护能为将来子数组和提供积极贡献的索引，即即加入队列的i必须满足sum[i]>0
                 * sum[x] = nums[x] + max(0, sum[i])
                 * 負數對求最值沒有貢獻，所以sum[i] > 0，相當於剪枝
                 *  */
                if (sum[i] > 0) {
                    deque.offerLast(i);
                }
            }
            return res;
        }
    }



//leetcode submit region end(Prohibit modification and deletion)

}