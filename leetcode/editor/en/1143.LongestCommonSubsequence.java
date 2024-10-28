/**
 * Given two strings text1 and text2, return the length of their longest common
 * subsequence. If there is no common subsequence, return 0.
 * <p>
 * A subsequence of a string is a new string generated from the original string
 * with some characters (can be none) deleted without changing the relative order of
 * the remaining characters.
 * <p>
 * <p>
 * For example, "ace" is a subsequence of "abcde".
 * <p>
 * <p>
 * A common subsequence of two strings is a subsequence that is common to both
 * strings.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 * <p>
 * <p>
 * Related Topics String Dynamic Programming 👍 13511 👎 194
 */
       
/*
 2024-07-24 23:23:16
 Longest Common Subsequence
Category	Difficulty	Likes	Dislikes
algorithms	Medium (57.78%)	13511	194
Tags
Unknown

Companies
Unknown
*/

class LongestCommonSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestCommonSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question: 與718.最長重複子數組的區別在於，這裡不要求連續
        public int longestCommonSubsequence(String text1, String text2) {
            //定義dp數組：dp[i][j]表示長度為[0,i-1]的字符串text1 與 長度為[0,j-1]的字符串text2的最長公共子序列
            int[][] dp = new int[text1.length() + 1][text2.length() + 1];
            //初始化 因為text與空字符串 的最長公共子序列肯定為0，其他下標都是隨著遞推公式逐步覆蓋，所以初始值可以為任意值，那麼就統一初始化為0
//        dp[i][0] = 0;
//        dp[0][j] = 0;
            //遍歷順序 根據遞推公式，遍歷順序都是從前往後
            for (int i = 1; i <= text1.length(); i++) {
                for (int j = 1; j <= text2.length(); j++) {
                    //遞推公式：根據dp數組定義，可以推導dp[i][j]可由上一個狀態推導，
                    // 由於不要求連續，那麼上一個元素就分兩種case, case1: text1[i-1] == text2[j-1] , 那么找到了一个公共元素，此時dp[i][j] = dp[i-1][j-1]+1;
                    // case2:text1[i-1] != text2[j-1]， 那就兩個字符分別嘗試往前退一個字符，進行比較，max(text1[0,i-2]與text2[0,j-1], text1[0,i-1]與text2[0,j-2]),取二者公共子序列的最大值
                    if (text1.charAt(i-1) == text2.charAt(j-1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[text1.length()][text2.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}