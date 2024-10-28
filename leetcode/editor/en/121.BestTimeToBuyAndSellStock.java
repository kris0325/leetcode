

/*
 2024-07-15 18:11:19

 Best Time to Buy and Sell Stock
Category	Difficulty	Likes	Dislikes
algorithms	Easy (53.62%)	30890	1149
Tags
array | dynamic-programming

Companies
amazon | bloomberg | facebook | microsoft | uber

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104
*/

class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //greedy: 遍历prices，  紀錄並取左最左最小價格， 取最右最大值，那麼得到差值就是最大利潤
        public int maxProfit(int[] prices) {
            int lowPrice = Integer.MAX_VALUE;
            int maxProfit = 0;
            for (int i = 0; i < prices.length; i++) {
                lowPrice = Math.min(lowPrice, prices[i]);
                //相當於，當區間利潤變大時，才更新maxProfit
                maxProfit = Math.max(maxProfit, prices[i] - lowPrice);
            }
            return maxProfit;
        }
    }

    class Solution {
        //dp: 二維數組， 0｜1表示持有股票｜不持有股票的狀態

        //定義 dp[i][0]: 第i天持有股票的最大收益 ， 要么保持前一天持有股票的狀態，要么當天第一次買入股票（那麼收益需要減去當天股票價格，所以為負數：-prices[i]）。
        //遞推公式：dp[i][0] = Math.max(dp[i-1][0], -prices[i])

        //    dp[i][1]: 第i天不持有股票的最大收益，要么保持前一天不持有股票的狀態，要么在當天賣出股票。
        //  遞推公式：  dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i])  第i-1天有股票，在i天賣掉股票｜ 第i-1天就不擁有股票，那保持和i-1不變

        //初始化， 第0天現金為0， 持有股票，那麼一定就是買入股票，所以dp[0][0] = 0- prices[0]
        //  第0不持有股票後最多現金， ，不持有股票， 那現金就是0， 所以dp[0][1] = 0
        public int maxProfit(int[] prices) {
            int[][] dp = new int[prices.length][2];
            //第0天持有股票的收益（負值）
            dp[0][0] = -prices[0];
            //第0天不持有股票的收益
            dp[0][1] = 0;
            for (int i = 1; i < prices.length; i++) {
                //不能這樣定義，因為 當天[0]持有股票的狀態只能前一天由持有股票狀態推導
                //dp[i-1][1] 包涵了持有股票並賣出股票的case邏輯, 那麼 dp[i-1][1] -prices[i] 就相當於同一天先賣出股票，再買入股票，不合題意的限制
                //因為我們在同一天只能進行一次操作（要么買入，要么賣出）。所以，這裡的邏輯是不對的
//                bug:  dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);
                //如果允許同一天多次買賣即，122. Best Time to Buy and Sell Stock II，那麼就應該寫為：dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);
                dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            }
            return dp[prices.length - 1][1];
        }
    }


    class Solution0 {
        //暴力解法 : 两层for loop, 遍历每天，计算买当前股票，未来某一天卖出的最大值，如何结果取最大值
        //time limit exceeded
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    maxProfit = Math.max(prices[j] - prices[i], maxProfit);
                }
            }
            return maxProfit;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}