

/*
 2024-07-16 14:46:45

 Best Time to Buy and Sell Stock III
Category	Difficulty	Likes	Dislikes
algorithms	Hard (47.92%)	9642	189
Tags
array | dynamic-programming

Companies
Unknown

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously
 (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later,
as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
*/

class BestTimeToBuyAndSellStockIii {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //定義dp數組 dp[i][j] :  用j = 0，1，2，3，4分別紀錄持有股票的狀態

        //那么遞推公式：
        //0:不操作 dp[i][0] = dp[i-1][0]，没有操作，则和前一天一样，//其实dp[i][0]这一状态也可以不设置，没有操作，利润自然为0
        //1:第一次持有股票的最大利潤  dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]) 取 保持前一天第一次持有股票状态， 或者前一天不操作当天第一次买入股票 的较大值
        //2:第一次不持有股票的最大利潤 dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]) 取 保持前一天第一次不持有股票状态， 或者前一天第一次持有股票然后当天第一次卖出股票 的较大值
        //3:第二次持有股票的最大利潤   dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] - prices[i])  同上推导
        //4:第二次不持有股票的最大利潤  dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3]+prices[i]) 同上推导

        //初始化：
        //dp[0][0] 第0天 不操作最大利潤為 0
        //dp[0][1] 第0天 第一次持有股票最大利潤 -prices[0] = 0-prices[0]
        //dp[0][2] 第0天 第一次不持有股票的最大利潤 0 = -prices[0] + prices[0] ，第0天做第一次卖出的操作，这个初始值应该是多少呢？此时还没有买入，怎么就卖出呢？ 其实大家可以理解当天买入，当天卖出，所以dp[0][2] = 0;
        //dp[0][3] 第0天 第二次持有股票的最大利潤 -prices[0] = 0-prices[0]
        //dp[0][4] 第0天 第二次不持有股票的最大利潤 0 = -prices[0] + prices[0] , 推导同上

        //遍历顺序：由于第i天依赖第i-1天状态，所以从前往后遍历

        public int maxProfit(int[] prices) {
            int [][]dp = new int[prices.length][5];
            //没有操作，则和前一天一样
            //其实dp[i][0]这一状态也可以不设置，没有操作，利润自然为0
//                dp[i][0] = dp[i - 1][0];

            //初始化dp[0][j]數組
            dp[0][1] = -prices[0];
            dp[0][3] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                // j實際有意義的交易操作次數從j=1開始，即j=1 第1次買入，j=2,第1次賣出...
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
                dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
                dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
            }
            return dp[prices.length - 1][4];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}