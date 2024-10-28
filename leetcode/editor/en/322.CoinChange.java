import java.util.Arrays;

/**
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 * <p>
 * Return the fewest number of coins that you need to make up that amount. If
 * that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: coins = [1], amount = 0
 * Output: 0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2³¹ - 1
 * 0 <= amount <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Breadth-First Search 👍 18909 👎 449
 */
       
/*
 2024-07-23 11:28:08
 Coin Change
Category	Difficulty	Likes	Dislikes
algorithms	Medium (43.88%)	18909	449
Tags
dynamic-programming

Companies
Unknown
*/

class CoinChange {
    public static void main(String[] args) {
        Solution solution = new CoinChange().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question: 完全背包問題

        public int coinChange(int[] coins, int amount) {
            //dp[j] 表示裝滿背包重量為j的最少coins數
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            //初始化
            //首先凑足总金额为0所需钱币的个数一定是0，那么dp[0] = 0;
            //其他下标对应的数值呢？
            //考虑到递推公式的特性，dp[j]必须初始化为一个最大的数，否则就会在min(dp[j - coins[i]] + 1, dp[j])比较的过程中被初始值覆盖。
            //所以下标非0的元素都是应该是最大值。
            dp[0] = 0;
            //iterative order，求最小，所以先背包後物品，或者先物品後背包都可以
            //物品遍歷
            for (int i = 0; i < coins.length; i++) {
                //背包遍歷
                for (int j = coins[i]; j <= amount; j++) {
                    //recessive formula
                    //只有dp[j - coins[i]]不是初始值時 才有選擇的必要,
                    // 因為dp[j - coins[i]] == Integer.MAX_VALUE，表示使用前面的coins無法組合，湊出j - coins[i]的amount，所以需要跳過，繼續增加總錢數amount來尋找硬幣組合
                    if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
                    }

                }
            }
            //dp[amount] == Integer.MAX_VALUE，表示使用coins無法組合
            if(dp[amount] == Integer.MAX_VALUE){
                return -1;
            }
            return dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}