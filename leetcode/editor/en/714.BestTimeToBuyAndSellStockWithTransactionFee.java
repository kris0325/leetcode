/**
 * You are given an array prices where prices[i] is the price of a given stock on
 * the iᵗʰ day, and an integer fee representing a transaction fee.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions
 * as you like, but you need to pay the transaction fee for each transaction.
 * <p>
 * Note:
 * <p>
 * <p>
 * You may not engage in multiple transactions simultaneously (i.e., you must
 * sell the stock before you buy again).
 * The transaction fee is only charged once for each stock purchase and sale.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= prices.length <= 5 * 10⁴
 * 1 <= prices[i] < 5 * 10⁴
 * 0 <= fee < 5 * 10⁴
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Greedy 👍 7130 👎 213
 */
       
/*
 2024-07-17 15:31:52

 Best Time to Buy and Sell Stock with Transaction Fee
Category	Difficulty	Likes	Dislikes
algorithms	Medium (68.48%)	7130	213
Tags
array | dynamic-programming | greedy

Companies
bloomberg | facebook
*/

class BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockWithTransactionFee().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        //dp question:
        //定義 dp[i][j] 表示 第i天狀態為j的最大利潤 。 j = 0 ：持有股票的最大利潤； j=1 ：不持有股票的最大利潤
        public int maxProfit(int[] prices, int fee) {
            //只有一天，不能產生利潤
            if (prices.length == 1) {
                return 0;
            }
            int[][] dp = new int[prices.length][2];
            //初始化dp
            //第0天買入股票，所以利潤為-prices[0]
            dp[0][0] = -prices[0];
            //第0天賣出股票，可以推導，同一天同一隻股票買進再賣出，實際上沒有意義，不會產生利潤，所以為0
            dp[0][1] = 0;
            for (int i = 1; i < prices.length; i++) {
                //遞推公式
                //dp[i][0] 表示 第i天持有股票的最大利潤, max(和前一天的狀態保持不變(即不操作)， 或者前一天（第i-1天）不持有股票，然後在第i天買入股票)
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
                //dp[i][0] 表示 第i天不持有股票的最大利潤，max(和前一天的狀態保持不變(即不操作)， 或者前一天（第i-1天）持有股票，然後在第i天賣出股票(賣出時支付交易fee))
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
            }
            return dp[prices.length - 1][1];
        }
    }

    class Solution2 {
        //dp question:
        //1定義 dp[i][j] 表示 第i天狀態為j的最大利潤 。與solution中j表示的狀態相反，此時 j = 0 ：不持有股票的最大利潤； j=1 ：持有股票的最大利潤
        public int maxProfit(int[] prices, int fee) {
            //只有一天，不能產生利潤
            if (prices.length == 1) {
                return 0;
            }
            //3初始化dp
            int[][] dp = new int[prices.length][2];
            //不操作
            dp[0][0] = 0;
            //買進股票
            dp[0][1] = -prices[0];
            //4遍歷順序：第i天依賴第i-1天，所以從前往後遍歷
            for (int i = 1; i < prices.length; i++) {
                //2遞推公式
                //dp[i][0]表示第i天不持有股票的最大利潤
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
                //dp[i][1]表示第i天持有股票的最大利潤
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
            return dp[prices.length - 1][0];
        }
    }

    class Solution3 {
        //greedy question:
         /*
        注意如果是用貪心算法： 將本題 LeetCode 714和  LeetCode 122 比較
        * LeetCode 122 - Best Time to Buy and Sell Stock II
题目要求：
可以多次买卖股票（不限次数），找出能获取的最大利润。
贪心算法的思路：
每天都交易，只要当天的价格高于前一天的价格，就在前一天买入，今天卖出，获取差价作为利润。
这样做是因为，只要有任何两天之间的价格差是正的，就一定可以获取利润。
*/
        public int maxProfitLeetcode122(int[] prices) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                maxProfit += Math.max(prices[i] - prices[i - 1], 0);
            }
            return maxProfit;
        }

        /**
         * LeetCode 714 - Best Time to Buy and Sell Stock with Transaction Fee
         * 题目要求：
         * 可以多次买卖股票，但每次交易都需要支付一定的费用（交易手续费），找出能获取的最大利润。
         * <p>
         * 为什么不能直接使用与LeetCode 122相同的贪心算法：
         * <p>
         * 交易费用使得每天都交易收集正利润的方法不再适用，因为有时可能由于交易费用的存在，频繁交易反而会降低总利润。
         * 因此，需要在某些时候选择不交易以避免支付过多的交易费用。
         * <p>
         * 贪心算法的核心在于在每一步都做出局部最优选择，以期望最终获得全局最优解。
         * <p>
         * 对于LeetCode 714，贪心算法的思路如下：
         * <p>
         * 如果当前价格高于买入价格并且扣除手续费后有利润，则卖出。
         * 如果当前价格低于买入价格，则更新买入价格。
         * 需要特别注意的是，每次计算卖出时，要考虑到之前累积的利润。
         * <p>
         * 为了避免重复计算，在卖出后将minPrice更新为当前价格减去手续费。这样可以继续在后面的交易中考虑手续费。
         * 这个贪心算法通过更新买入价格和在合适的时机卖出股票，确保了每次交易都获得最大可能的利润，同时避免了频繁交易带来的手续费损失。
         */

        public int maxProfit(int[] prices, int fee) {
            int maxProfit = 0;
            //初始化最小價格
            int minPrice = prices[0];
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < minPrice) {
                    minPrice = prices[i];
                } else if (prices[i] > minPrice + fee) {
                    //如果當前價格高於（買入價格加上手續費），則賣出股票，累加利潤
                    maxProfit += prices[i] - minPrice - fee;
                    //注意更新買入價格為當前價格減去手續費，
                    // 不寫為“minPrice = prices[i]”，是为了避免重复计算，在卖出后将minPrice更新为当前价格减去手续费。这样可以继续在后面的交易中考虑手续费。
                    //相當於下次滿足賣出股票的條件時， 當前次交易利潤為profit = prices[i+1] -  minPrice = prices[i+1] -  (prices[i]-fee) + fee
                    // = prices[i+1] - prices[i] ;
                    //相當於i+1時，沒有交手續費，因為考慮到前一次交過手續費，再往前遞推，就是貪心的本質，确保了每次交易都获得最大可能的利润，同时避免了频繁交易带来的手续费损失，
                    //即減少交易次數，每次盡可能少繳交易費
                    minPrice = prices[i] - fee;
                }
            }
            return maxProfit;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}