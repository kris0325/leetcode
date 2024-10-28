/**
 * Given two strings word1 and word2, return the minimum number of steps required
 * to make word1 and word2 the same.
 * <p>
 * In one step, you can delete exactly one character in either string.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make
 * "eat" to "ea".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= word1.length, word2.length <= 500
 * word1 and word2 consist of only lowercase English letters.
 * <p>
 * <p>
 * Related Topics String Dynamic Programming 👍 5768 👎 87
 */
       
/*
 2024-07-26 22:27:29
 Delete Operation for Two Strings
Category	Difficulty	Likes	Dislikes
algorithms	Medium (61.50%)	5768	87
Tags
string

Companies
google


*/

class DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new DeleteOperationForTwoStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //dp question: 先求兩個字符串的最大公共子序列長度 longestCommonS，再取longestCommonS與兩個字符串長度差值的和
        public int minDistance(String word1, String word2) {
            //定義dp數組:dp[i][j]表示以i-1結尾字符串word1，與以j-1結尾字符串word2的最大公共子序列
            //初始化 ：根據定義dp[i][0], dp[i][0] 和dp[0][j]其实都是没有意义的！再結合遞推公式統一初始化為0
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            //遍歷順序
            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    //遞推公式：
                    //case1.最末位字符相等
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        //case2.最末位字符不相等，先字符串word1往前退一個字符再比較，或者先word2字符往前一個字符再比較，取二者較大值
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return word1.length() - dp[word1.length()][word2.length()] + word2.length() - dp[word1.length()][word2.length()];
        }
    }

    class Solution {
        //dp question:
        public int minDistance(String word1, String word2) {
            //定義dp數組: 與solution定義不同，dp[i][j]表示以i-1結尾字符串word1，與以j-1結尾字符串word2，達到相等時，所需刪除元素的最小操作次數
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            //初始化 遞推公式需要依賴dp[i][0], dp[0][j],dp[0][0] 根據定義dp[i][j]
            //dp[i][0]表示字符串word1變為空字符串，那麼最小操作數為i， 同理dp[0][j]=j, dp[0][0]=0
            for (int i = 0; i <= word1.length(); i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j <= word2.length(); j++) {
                dp[0][j] = j;
            }
            //遍歷順序
            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    //遞推公式：
                    //case1.最末位字符相等，無須刪除末尾字符操作，繼續下一輪
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        //case2.最末位字符不相等，此時有三種情況,取三種最小值
                        // 1.先刪除字符串word1末尾字符word1[i-1]再比較，最小操作次數:dp[i-1][j]+1
                        // 2.先刪除字符串word2末尾字符word2[j-1]再比較，最小操作次數:dp[i][j-1]+1
                        // 3.同時刪除字符串的末尾字符word1[i-1]，word2[j-1]再比較，最小操作次數:dp[i-1][j-1]+2
                        dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 2));
                    }
                }
            }
            return dp[word1.length()][word2.length()];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}