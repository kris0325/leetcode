import java.util.Arrays;

/**
 * Given an integer n, return the least number of perfect square numbers that sum
 * to n.
 * <p>
 * A perfect square is an integer that is the square of an integer; in other
 * words, it is the product of some integer with itself. For example, 1, 4, 9, and 16
 * are perfect squares while 3 and 11 are not.
 * <p>
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * <p>
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * Constraints:
 * 1 <= n <= 10⁴
 * Related Topics Math Dynamic Programming Breadth-First Search 👍 11190 👎 471
 */
       
/*
 2024-07-23 00:01:34
 Perfect Squares 279. 完全平方数
Category	Difficulty	Likes	Dislikes
algorithms	Medium (54.78%)	11190	471
Tags
math | dynamic-programming | breadth-first-search

Companies
google
*/

class PerfectSquares {
    public static void main(String[] args) {
        Solution solution = new PerfectSquares().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //dp questions:  本題可以轉化為背包問題，即n為背包重量， 完全平方數i*i為物品。物品可以重複使用，所以可以轉化為完全背包
        //因為時求裝滿背包的最小物品數，所以遍歷物品/背包的先後順序都可以
        public int numSquares(int n) {
            // dp[j] 為裝滿背包容量為j，所需物品的最小數量
            int[] dp = new int[n + 1];
            //求最小值，所以需要初始化為Integer.MAX_VALUE
            Arrays.fill(dp, Integer.MAX_VALUE);
            //初始化
            dp[0] = 0;
            //遍歷物品
            for (int i = 1; i * i <= n; i++) {
                //遍歷背包 注意初始化j = i * i，背包必須從i * i開始才有意義，即背包必須裝得下當前物品才有意義,使得dp[j - i * i],j - i * i>=0
                for (int j = i * i; j <= n; j++) {
                    //考慮i*i｜不考慮i*i，然後取最小值
                    dp[j] = Math.min(dp[j - i * i] + 1, dp[j]);
                }
            }
            return dp[n];
        }
    }

    class Solution {
        //dp questions:  本題可以轉化為背包問題，即n為背包重量， 完全平方數i*i為物品。物品可以重複使用，所以可以轉化為完全背包
        //因為時求裝滿背包的最小物品數，所以遍歷物品/背包的先後順序都可以
        public int numSquares(int n) {
            // dp[j] 為裝滿背包容量為j，所需物品的最小數量
            int[] dp = new int[n + 1];
            //求最小值，所以需要初始化為Integer.MAX_VALUE
            Arrays.fill(dp, Integer.MAX_VALUE);
            //初始化
            dp[0] = 0;
            //遍歷背包 注意初始化j = i * i，背包必須從i * i開始，即背包必須裝得下當前物品才有意義，使得dp[j - i * i],j - i * i>=0
            for (int j = 1; j <= n; j++) {
                //遍歷物品
                for (int i = 1; i * i <= j; i++) {
                    //考慮i*i｜不考慮i*i，然後取最小值
                    dp[j] = Math.min(dp[j - i * i] + 1, dp[j]);
                }
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}