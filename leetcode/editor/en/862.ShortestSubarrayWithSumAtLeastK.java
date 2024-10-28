import java.util.*;

/**
 * Given an integer array nums and an integer k, return the length of the shortest
 * non-empty subarray of nums with a sum of at least k. If there is no such
 * subarray, return -1.
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1], k = 1
 * Output: 1
 * <p>
 * Example 2:
 * Input: nums = [1,2], k = 4
 * Output: -1
 * <p>
 * Example 3:
 * Input: nums = [2,-1,2], k = 3
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * -10⁵ <= nums[i] <= 10⁵
 * 1 <= k <= 10⁹
 * <p>
 * <p>
 * Related Topics Array Binary Search Queue Sliding Window Heap (Priority Queue)
 * Prefix Sum Monotonic Queue 👍 4306 👎 118
 */
       
/*
 2024-09-26 17:27:23
*/

class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        Solution solution = new ShortestSubarrayWithSumAtLeastK().new Solution();
        solution.shortestSubarray(new int[]{17, 85, 93, -45, -21}, 150);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //monotonic queue 維護前綴和為遞增的順序
        //tc：o(n)
        //本題難點：1.要轉化為前綴和之差求子數組，
        //2.基於1，要想到那麼存儲前綴和索引的隊列需要維護單調遞增的性質(后面的-前面的，所以要保证后面的大，即递增队列)

        //注意：子數組中元素是有順序的，即保留原數組中的順序
        // 1.求子數組问题可转化为求两个前缀和的差，即得到子数组的和 range sum
        // 2.因为是后面数组前缀和-前面数组前缀和，所以需要维护queue为单调递增，
        //另外一點，當prefixSum[i] <= prefixSum[deque.peekLast()，這個2個index作為左邊界時（即作為被減數越小越容易使子數組和滿足>=k ）
        // ，較小的數prefixSum[i]是更有優解, 而較大數deque.peekLast()不是更優解，所以需要從隊列去掉
        //所以右边加入元素时，需要判断是否满足单调递增，否则需要弹出队列元素
        // 即：因为子数组和等于当前前缀和-deque.peekFirst()的前缀和，因此需要维护dequeue的前缀和单调递增，range sum才为正数，才有意义
        public int shortestSubarray(int[] nums, int k) {
            //構建前綴和
            long[] prefixSum = new long[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
            //deque 存儲前綴和的索引
            Deque<Integer> deque = new ArrayDeque<>();
            int minLen = Integer.MAX_VALUE;
            //注意：这里取等号，i <= nums.length，因為根據前綴和的定義，需要遍历到最後一個元素nums[i]，來計算prefixSum[n]的前缀和
            for (int i = 0; i <= nums.length; i++) {
                //如果找到滿足條件的子數組，則更新最小長度，並移除對首元素
                while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                    //更新最小長度，同時移除對首左邊的元素：i - deque.pollFirst())
                    //左出队列
                    //取解
                    minLen = Math.min(minLen, i - deque.pollFirst());
                }

                //右边出queue時，要保證deque单调递增的性質
                //保持隊列prefixSum[i]前綴和為遞增的順序，移除无效的队尾元素（因为不poll deque.peekLast()的话，求满足条件的range sum >=k, 由于prefixSum[i] - prefixSum[deque.peekLast()] <0,
                // 所以这样i得继续向后移动，子数组的长度只会更大，所以deque.peekLast()是无效的，需要poll后，再继续迭代计算新的子数组）
                 //因为子数组和等于当前前缀和-deque.peekFirst()的前缀和，因此需要维护dequeue的前缀和单调递增，range sum才为正数，才有意义
                while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                    deque.pollLast();
                }
                //將當前的前綴和索引加入隊列
                deque.offerLast(i);
            }
            return minLen == Integer.MAX_VALUE ? -1 : minLen;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}