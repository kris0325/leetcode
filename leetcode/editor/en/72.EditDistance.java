/**
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 * <p>
 * <p>
 * Related Topics String Dynamic Programming 👍 14837 👎 233
 */
       
/*
 2024-07-28 01:03:45
 Edit Distance
Category	Difficulty	Likes	Dislikes
algorithms	Medium (56.38%)	14837	233
Tags
string | dynamic-programming

Companies
Unknown
*/

class EditDistance {
    public static void main(String[] args) {
        Solution solution = new EditDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question: 編輯距離
        //注意題意：虽然题目要求从 word1 转换为 word2，但实际上你可以理解为在两个字符串之间进行操作，无论是操作 word1 还是 word2，目标都是让两个字符串相等
        //時間複雜度：o(n*m)
        //空間複雜度：o(n*m)
        public int minDistance(String word1, String word2) {
            //1.定义dp数组 dp[i][j]表示以i-1結尾字符串word1，與j-1結尾字符串的編輯距離
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            //3.初始化数组  根據定義dp[i][0],需要刪除全部元素，所以操作數為 dp[i][0] =i, 同理dp[0][j] =j;
            for (int i = 0; i <= word1.length(); i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j <= word2.length(); j++) {
                dp[0][j] = j;
            }
            //4.遍歷順序
            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    //2.遞推公式
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        //無須編輯操組
                        dp[i][j] = dp[i - 1][j - 1] ;
                    } else {
                        //delete操作： dp[i][j] = dp[i-1][j]+1;
                        //repalce操作： 替換word1.charAt(i-1)，使末尾字符相等，則dp[i][j]取決餘上一個狀態，即 dp[i][j] = dp[i-1][j-1]+1；
                        //add操作： word1增加字符，word1增加一個字符，和word2刪除一個字符在計算編輯距離時是等效的，那麼dp[i][j] = dp[i][j-1]+1;
                        // (不寫成形式：dp[i][j]=dp[i+1][j]+1, 这是因为 dp 数组中的 i 和 j 是用来表示 word1 和 word2 的前缀长度。如果写成 dp[i][j] = dp[i + 1][j] + 1，那么 dp[i + 1][j] 就会涉及 word1 的第 i  个字符，而 dp[i][j] 的值是基于 word1 的以 i-1 个字符結尾的。因此，这种写法不符合递推公式的定义。
                        // 正确的递推方向是基于已经计算的较短前缀的编辑距离来计算更长前缀的编辑距离。根據定義，為方便執行遞推公式，所以選擇插入編輯word2)
                        //dp[i][j] = min(delete,replace,add)
                        dp[i][j] = Math.min(dp[i][j] = dp[i - 1][j] + 1,
                                Math.min(dp[i][j] = dp[i-1][j-1]+1, dp[i][j - 1] + 1));

                    }
                }
            }
            return dp[word1.length()][word2.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}