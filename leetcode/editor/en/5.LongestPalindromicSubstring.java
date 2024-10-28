/**
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 * <p>
 * <p>
 * Related Topics Two Pointers String Dynamic Programming 👍 29365 👎 1791
 */
       
/*
 2024-07-29 16:05:13
 Longest Palindromic Substring
Category	Difficulty	Likes	Dislikes
algorithms	Medium (33.82%)	29365	1791
Tags
string | dynamic-programming

Companies
amazon | bloomberg | microsoft


*/

class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //與516.LongestPalindromicSubsequence最長子數組的區別：(subsequence子序列可以刪除元素，subString子數組不能刪除，即為連續子序列)
        //dp question
        public String longestPalindrome(String s) {
            String longest = "";
            //1.定義dp數組 dp[i][j]為[i,j]閉區間上是否為回文字符串
            boolean[][] dp = new boolean[s.length()][s.length()];
            //2.初始化 默認值為0
            //3.遍歷順序 從下到上 從左到右
            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = i; j < s.length(); j++) {
                    //2.遞推公式：
                    if (s.charAt(i) == s.charAt(j)) {
                        if (j - i <= 1) {
                            dp[i][j] = true;
                            if (longest.length() < j - i + 1) {
                                longest = s.substring(i, j + 1);
                            }
                        } else {
                            if (dp[i + 1][j - 1]) {
                                dp[i][j] = true;
                                if (longest.length() < j - i + 1) {
                                    longest = s.substring(i, j + 1);
                                }
                            }
                        }
                    }
                }
            }
            return longest;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}