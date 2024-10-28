/**
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 * <p>
 * Return the number of combinations that make up that amount. If that amount of
 * money cannot be made up by any combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * The answer is guaranteed to fit into a signed 32-bit integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: amount = 10, coins = [10]
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming 👍 9337 👎 168
 */
       
/*
 2024-07-17 23:12:37
 Coin Change II
Category	Difficulty	Likes	Dislikes
algorithms	Medium (63.80%)	9337	168
*/

class CoinChangeIi {
    public static void main(String[] args) {
        Solution solution = new CoinChangeIi().new Solution();
        System.out.println(solution.change(0, new int[]{2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp：完全背包問題
        public int change(int amount, int[] coins) {
            //定義dp數組：dp[j]表示：湊成金額為j的硬幣組合數，容量（錢總數量）為j的背包，從物品coins[]選擇物品放入，總共有多少種放法的組合
            int [] dp = new int[amount + 1];
            //初始化dp數組
            //因為根據遞推公式當前dp[j] 依賴上一個個dp[j-coins[i]]的狀態，所以dp[0]不能初始化為0，否則全部為0；
            //可以考慮容量為0的組合只有一種，就是不選物品，所以初始化為 dp[0] = 1
            //dp[0] = 1 表示凑成金额为0的唯一组合方式，即什么都不选，表示空集合的组合数为1。
            dp[0] = 1;
            //遍歷順序
                //遍歷物品
            for (int i = 0; i < coins.length; i++) {
                //遍歷背包重量, 完全背包，可以重複選擇物品，所以從前往後
                for (int j = coins[i]; j <= amount; j++) {
                    //遞推公式
                    // 对于每种硬币 coins[i] 和每个金额 j，如果考虑将 coins[i] 加入到组合中，则 dp[j] += dp[j - coins[i]]，表示当前金额 j 的组合数可以通过增加当前硬币的组合数来得到。
                    // 要求總共組合數, 則通過j 遍歷[coins[i], amount],所以累加所有的 dp[j]即可得到， 金額為amount的硬幣組合數
                    dp[j] += dp[j - coins[i]];
                }
            }
            return dp[amount];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}