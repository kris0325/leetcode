/**
 * You are given an integer array prices where prices[i] is the price of a given
 * stock on the iᵗʰ day, and an integer k.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most k
 * transactions: i.e. you may buy at most k times and sell at most k times.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you
 * must sell the stock before you buy again).
 * Example 1:
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-
 * 2 = 2.
 * <p>
 * Example 2:
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-
 * 2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0
 * = 3.
 * <p>
 * Constraints:
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 * <p>
 * Related Topics Array Dynamic Programming 👍 7414 👎 209
 */
       
/*
 2024-07-16 18:08:51
 Best Time to Buy and Sell Stock IV
Category	Difficulty	Likes	Dislikes
algorithms	Hard (42.68%)	7414	209
Tags
dynamic-programming

Companies
Unknown
*/

class BestTimeToBuyAndSellStockIv {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIv().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp問題
        public int maxProfit(int k, int[] prices) {
            //1.定義dp數組， dp[i][j] , 表示第i天，最多j次股票交易對應操作的最大利潤（k筆交易，對應為2k次（買/賣）操作， 注意要包含第0天不操作dp[0][0]， 所以j<=2k+1, ）
            //注意第0天不操作，所以利潤為0 dp[0][0] = 0;
            int[][] dp = new int[prices.length][2 * k+1];

            //初始化dp[0][j]數組 j實際有意義的交易操作次數從j=1開始，即j=1 第1次買入，j=2,第1次賣出...
            for (int j = 1; j <= 2 * k; j++) {
                //第0天第j次操作後的最大利潤，因為必須先買後賣，所以奇數次操作為買入操作(利潤為-prices[j])，偶數次操作為賣出操作(利潤為0)
                dp[0][j] = j % 2 == 1 ? -prices[0] : 0;
            }
            // 遍歷順序：從前往後
            for (int i = 1; i < prices.length; i++) {
                for (int j = 1; j <= 2 * k; j++) {
                    //currentOptProfit 第j次操作後的最大利潤，因為必須先買後賣，所以奇數次操作為買入操作，偶數次操作為賣出操作
                    int currentOptProfit = j % 2 == 1
                            ? dp[i - 1][j - 1] - prices[i]
                            : dp[i - 1][j - 1] + prices[i];
                    //遞推公式 recursive formula：
                    dp[i][j] = Math.max(dp[i - 1][j], currentOptProfit);
                }
            }
            return dp[prices.length - 1][2 * k];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}