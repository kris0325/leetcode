/**
 * You are given an array prices where prices[i] is the price of a given stock on
 * the iᵗʰ day.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions
 * as you like (i.e., buy one and sell one share of the stock multiple times) with
 * the following restrictions:
 * <p>
 * <p>
 * After you sell your stock, you cannot buy stock on the next day (i.e.,
 * cooldown one day).
 * <p>
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you
 * must sell the stock before you buy again).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: prices = [1]
 * Output: 0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming 👍 9375 👎 318
 */
       
/*
 2024-07-16 20:22:52
 Best Time to Buy and Sell Stock with Cooldown
Category	Difficulty	Likes	Dislikes
algorithms	Medium (58.08%)	9375	318
Tags
dynamic-programming

Companies
google
*/

class BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockWithCooldown().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp 问题：因为有cooldown冷冻期一天，影響買入操作需要隔一天，但是不影響賣出操作
        public int maxProfit(int[] prices) {
            //定義dp數組 dp[i][j] 表示第i天狀態為j的最大利潤
            // dp[i][0]表示第i天不持有股票的最大利潤
            //dp[i][1]表示第i天持有股票的最大利潤
            if (prices.length == 1) {
                // If there is only one day, we cannot make any profit，如果只有一天，无法獲得利潤
                return 0;
            }
            int[][] dp = new int[prices.length][2];
            //初始化 按照递推公式和实际需要去初始化dp数组
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
            //dp[1][1] 实际初始化为 = Math.max(dp[0][1], dp[1-2][0]-prices[1]); 而dp[1-2][0]
            dp[1][1] = Math.max(dp[0][1], -prices[1]);
            //遍歷順序 從前往後
            for (int i = 2; i < prices.length; i++) {
                //遞推公式
                //dp[i][0]表示第i天不持有股票的最大利潤可以分為case： 和前1天保持狀態不變 ｜ 前一天持有股票，第i天賣出股票
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                //dp[i][1]表示第i天持有股票的最大利潤可以分為case： 和前1天保持狀態不變 ｜ 前2天不持有股票，第i天買入股票(因为有cooldown冷冻期一天，买入操作需要隔一天，所以需要依赖前i-2天不持有股票的状态然后第i天买入)
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }
            return dp[prices.length - 1][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}