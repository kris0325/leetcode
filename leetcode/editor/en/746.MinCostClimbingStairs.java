/**
 * You are given an integer array cost where cost[i] is the cost of iᵗʰ step on a
 * staircase. Once you pay the cost, you can either climb one or two steps.
 * <p>
 * You can either start from the step with index 0, or the step with index 1.
 * <p>
 * Return the minimum cost to reach the top of the floor.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming 👍 11430 👎 1765
 */
       
/*
 2024-08-01 17:43:07
*/

class MinCostClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new MinCostClimbingStairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCostClimbingStairs(int[] cost) {
            //定义dp数组
            int[] dp = new int[cost.length + 1];
            //初始化dp初始值，可以下标为0或1的台阶开始，因此初始值均为0
            dp[0] = 0;
            dp[1] = 0;
            //1.写出递推公式dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
            //2.选择递推顺序，进行循环递推求解
            for (int i = 2; i <= cost.length; i++) {
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
                //打印递推值
                //  System.out.println(dp[i]);
            }

            return dp[cost.length];
        }
    }

    class Solution2 {
        public int minCostClimbingStairs(int[] cost) {
            //定义dp数组：到达台阶i,最小花费
            int[] dp = new int[cost.length + 1];
            //初始化dp初始值，根据递推公式，下标为0或1的台阶开始，因此初始值应该为对应cost[i]
            dp[0] = cost[0];
            dp[1] = cost[1];
            for (int i = 2; i <= cost.length; i++) {
                dp[i] = Math.min(dp[i - 1], dp[i - 2]) + (i == cost.length ? 0 : cost[i]);
            }
            return dp[cost.length];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}