/**
 * Given two strings s and t, return true if s is a subsequence of t, or false
 * otherwise.
 * <p>
 * A subsequence of a string is a new string that is formed from the original
 * string by deleting some (can be none) of the characters without disturbing the
 * relative positions of the remaining characters. (i.e., "ace" is a subsequence of
 * "abcde" while "aec" is not).
 * <p>
 * <p>
 * Example 1:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10⁴
 * s and t consist only of lowercase English letters.
 * <p>
 * <p>
 * <p>
 * Follow up: Suppose there are lots of incoming
 * s, say
 * s1, s2, ..., sk where
 * k >= 10⁹, and you want to check one by one to see if
 * t has its subsequence. In this scenario, how would you change your code?
 * <p>
 * Related Topics Two Pointers String Dynamic Programming 👍 9620 👎 532
 */
       
/*
 2024-07-25 20:34:01
 Is Subsequence
Category	Difficulty	Likes	Dislikes
algorithms	Easy (47.93%)	9620	532
Tags
binary-search | dynamic-programming | greedy

Companies
Unknown
*/

class IsSubsequence {
    public static void main(String[] args) {
        Solution solution = new IsSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question
        public boolean isSubsequence(String s, String t) {
            //1.定義dp數組: dp[i][j]表示長度為（以s[i-1]結尾的字符串s）[0,i-1]的字符串s 與 長度為[0,j-1]的字符串t的最長公共子序列的長度，
            //i-1,j-1以s[i-1]結尾,是為方便處理初始化邏輯
            // 那麼題目轉化為return dp[i][j] == s.length();
            int[][] dp = new int[s.length()+1][t.length()+1];
            //3.dp數組初始化，
            // 根据dp[i][j]的定义，dp[i][0] 和dp[0][j]其实都是没有意义的！
            //但dp[i][0] 和dp[0][j]要初始值，因为 为了方便递归公式dp[i][j] = dp[i - 1][j - 1] + 1;
            //所以dp[i][0] 和dp[0][j]初始化为0。
//        dp[i][o]=0;
//        dp[0][j] =0;
            // 所以定義數組時就統一初始化為0
            //4.遍歷順序  內外層循環s,t 均可遍歷， 根據遞推公式，當前狀態依賴上一個狀態，所以從前往後
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j <= t.length(); j++) {
                    //2.遞推公式：dp[i][j]由2種狀態推導
                    //case1: s.charAt(i-1) == t.charAt(j-1), 公共子序列長度+1，dp[i][j] = dp[i-1][j-1]+1;
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        //case2:則考慮字符串t需要往前回退，比較上一個字符串是否相等
                        //1143.最長公共子序列不同，s.charAt(i-2) == t.charAt(j-1) 與 s.charAt(i-1) == t.charAt(j-2)，然後取二者較大值
                        //與1143.最長公共子序列不同，本題是判斷s是否是t的子序列，在不相等時，所以s不用回退，只需考慮字符串t需要往前回退的case即可，所以
//                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j-1]);
                          dp[i][j] = dp[i][j-1];
                    }

                }
            }
            return s.length() == dp[s.length()][t.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}