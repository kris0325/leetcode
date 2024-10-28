import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * You are given a 0-indexed integer array nums and an integer k.
 * <p>
 * You are initially standing at index 0. In one move, you can jump at most k
 * steps forward without going outside the boundaries of the array. That is, you can
 * jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 * <p>
 * <p>
 * You want to reach the last index of the array (index n - 1). Your score is the
 * sum of all nums[j] for each index j you visited in the array.
 * <p>
 * Return the maximum score you can get.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (
 * underlined above). The sum is 7.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3] (
 * underlined above). The sum is 17.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * Output: 0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length, k <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Queue Heap (Priority Queue) Monotonic
 * Queue 👍 3426 👎 115
 */
       
/*
 2024-09-28 23:16:38
*/

class JumpGameVi {
    public static void main(String[] args) {
        Solution solution = new JumpGameVi().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    //注意solution1 與solution2 遞推公式的定義 下標不同，含義也不同，
    //solution1 計算dp 在于计算前一个窗口；  而 solution2 計算dp 在于计算当前窗口
    //影響  point1 計算i - deque.peekFirst() 的窗口大小也不同，
    //point2.和while 循環的順序也不同；

    //solution1： dp[i+1] = nums[i+1] + dp[deque.peekFirst()];
    //表示第 i+1 个位置的最大得分，它依赖于"前一个窗口"中最优的得分 dp[deque.peekFirst()]，然后加上当前位置的值 nums[i+1]。
    // point1: //總窗口大小k= 前一个窗口大小k-1 + 元素nums[i+1] ，所以solution1中的窗口大小要維護為k-1

    //point2: 先进行维护窗口大小（k-1），再进行 队列单调递减的维护（为下一次迭代）， 所以最后再进行递推。


    //solution2 ： dp[i] = nums[i] + dp[deque.peekFirst()];

    /**
     * 这里 dp[i] 表示第 i 个位置的最大得分，它依赖于前一窗口中的最优得分 dp[deque.peekFirst()]，再加上当前位置的值 nums[i]。
     * <p>
     * point1: //總窗口大小k= 当前窗口大小k 在 Solution2 中，递推公式定义的是计算当前 i 位置的最大得分，而不是提前计算下一步位置 i+1 的得分。
     * point2: 先进行维护窗口大小（k），再进行然后再进行递推，（只关心当前窗口） ； 队列单调递减的维护（与下一次迭代有关）， 所以最后再进行单调递减的维护。
     */


    class Solution1 {
        //dp + sliding window  maximum + monotonic queue
        ////tc: o(n)
        public int maxResult(int[] nums, int k) {

            //dp[i], 以num[0]為起點,以num[i]為終點的可跳前綴和，即最大值score
            int[] dp = new int[nums.length];
            //初始化
            dp[0] = nums[0];
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offerLast(0);
            for (int i = 0; i < nums.length - 1; i++) {
                //1.維護窗口大小為k-1
                //總窗口大小k= 前一个窗口大小k-1 + 元素nums[i+1]
                while (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                    deque.pollFirst();
                }
                //2.維護隊列單調遞減
                while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
                //3.遞推公式 i+1的最大值 = 當前值nums[i+1] + 前一個窗口（大小k-1）已確定的的最大值
                //或理解為：1，2保證我們能得到從前一個窗口（k-1）的最大值， 然後跳到下一位位置，總共大小就是k
                //deque.offerLast(i); i入隊列後，我們獲取對首元素，即得到dp[i]的值，
                // 所以不用寫dp[i],而寫為i+1
                dp[i + 1] = nums[i + 1] + dp[deque.peekFirst()];
            }
            //跳到最後一個位置
            return dp[nums.length - 1];
        }
    }

    class Solution2 {
        //dp + sliding window maximum + monotonic queue/
        //tc: o(n)
        public int maxResult(int[] nums, int k) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offerLast(0);
            for (int i = 1; i < nums.length; i++) {
                //1.維護窗口大小k
                //總窗口大小k= 当前窗口大小k
                while (!deque.isEmpty() && i - deque.peekFirst() > k) {
                    deque.pollFirst();
                }
                //3.
                dp[i] = nums[i] + dp[deque.peekFirst()];
                //2
                while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
            }
            return dp[nums.length - 1];
        }
    }

    class Solution {
        //sliding window + pq
        //tc: o(nlogn)
        public int maxResult(int[] nums, int k) {
            int[] score = new int[nums.length];
            score[0] = nums[0];
            int n = nums.length;
            //最大堆pq
            //int[i]： 跳到i對應的score值， i下標值,
            //根據每個位置上的score對窗口內k個元素排序
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            //初始化
            pq.offer(new int[]{nums[0], 0});
            for (int i = 1; i < n; i++) {
                //維護窗口大小
                while (i - pq.peek()[1] > k) {
                    pq.poll();
                }
                //計算當前窗口最大值
                score[i] = nums[i] + score[pq.peek()[1]];
                //但前元素入隊列
                pq.offer(new int[]{score[i], i});
            }
            return score[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}