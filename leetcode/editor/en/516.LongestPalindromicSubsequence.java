

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * <p>
 * A subsequence is a sequence that can be derived from another sequence by
 * deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 * <p>
 * <p>
 * Related Topics String Dynamic Programming 👍 9549 👎 330
 */
       
/*
 2024-07-29 10:40:48
 Longest Palindromic Subsequence
Category	Difficulty	Likes	Dislikes
algorithms	Medium (62.56%)	9549	330
Tags
dynamic-programming

Companies
amazon | uber
*/

class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //與5.LongestPalindromicSubstring最長子數組的區別：(subsequence子序列可以刪除元素，subString子數組不能刪除，即為連續子序列)
        //求最長子序列的長度，即返回值為子序列字符串的長度
        //dp question:
        public int longestPalindromeSubseq(String s) {
            int longestLength = 0;
            //1.定義dp數組 dp[i][j]表示閉區間[i,j]上的字符串的最大回文子序列
            int[][] dp = new int[s.length()][s.length()];
            //3.初始化
            //case1 首先考慮i==j的情況單字符回文，此時長度為1，但即根據遞推公式 dp[i][j] = dp[i + 1][j - 1] + 2，可以看出無法計算i==j的情況，
            // 因為i==j時，根據dp數組定義，就是單字符回文，dp[i + 1][j - 1]實際上超出了數組邊界，不存在，所以需要手動初始化為dp[i][j] =1；
            //case2. 愛他情況初始化為0即可
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = 1;
            }
            //4.遍歷順序：從下到上，從左往右
            for (int i = s.length() - 1; i >= 0; i--) {
                //注意：j初始化為j = i+1，因為當字符情況已經初始化為1，
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        //2.遞推公式 且首尾字符串相等时 ｜首位字符串不相等
                        // case1.相等，內層字符串是回文的最大長度+2
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        //case2.不相等時，由于 s[i] 和 s[j] 不相等時，不能将它们两者都包括在最长回文子序列中符，所以分別移除 首｜尾字符再比較，求較大值
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[0][s.length() - 1];
        }
    }

    class Solution2 {
        //求最長子序列，即返回值為子序列字符串，（打印子序列）
        //dp question:
        public String longestPalindromeSubseq(String s) {
            //1.定義dp數組 dp[i][j]表示閉區間[i,j]上的字符串的最大回文子序列
            int[][] dp = new int[s.length()][s.length()];
            //紀錄操組狀態：choice 数组：记录在计算 dp[i][j] 时的选择：
            //0 表示 s[i] == s[j]，匹配字符。(或者可理解為保留首尾元素)
            //1 表示选择 i+1 到 j 的子串（即忽略 s[i] (或者可理解為刪除首元素)）。
            //2 表示选择 i 到 j-1 的子串（即忽略 s[j]  (或者可理解為刪除尾元素)）。
            int[][] choices = new int[s.length()][s.length()];
            String longestPalindrome = "";
            Integer longestPalindromeLength = 0;

            //4.遍歷順序：從下到上，從左往右
            for (int i = s.length() - 1; i >= 0; i--) {
                //注意：j初始化為j = i+1，因為當字符情況已經初始化為1，
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        //2.遞推公式 且首尾字符串相等时 ｜首位字符串不相等
                        // case1.相等，內層字符串是回文的最大長度+2
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                        //紀錄操作的選擇狀態,(或者可理解為保留首尾元素)
                        choices[i][j] = 0;
                    } else {
                        //case2.不相等時，由于 s[i] 和 s[j] 不相等時，不能将它们两者都包括在最长回文子序列中符，所以分別移除 首｜尾字符再比較，求較大值
//                        dp[i][j] = Math.max(dp[i + 1][j],dp[i][j - 1]);
                        if (dp[i + 1][j] > dp[i][j - 1]) {
                            //即忽略 s[i] (或者可理解為刪除首元素)
                            dp[i][j] = dp[i + 1][j];
                            choices[i][j] = 1;
                        } else {
                            //即忽略 s[j]  (或者可理解為刪除尾元素)
                            dp[i][j] = dp[i][j - 1];
                            choices[i][j] = 2;
                        }
                    }
                }
            }
            longestPalindromeLength = dp[0][s.length() - 1];

            //構建打印子序列
            StringBuilder left = new StringBuilder();
            StringBuilder right = new StringBuilder();
            int i = 0;
            int j = s.length() - 1;
            while (i <= j) {
                if (choices[i][j] == 0) {
                    //(或者可理解為保留首尾元素)
                    left.append(s.charAt(i));
                    //避免當字符回文，重複添加字符到right
                    if (i != j) {
                        right.append(s.charAt(j));
                    }
                    i++;
                    j--;
                } else if (choices[i][j] == 1) {
                    //即忽略 s[i] (或者可理解為刪除首元素)
                    i++;
                } else {
                    //即忽略 s[j]  (或者可理解為刪除尾元素)
                    j++;
                }
            }
            longestPalindrome = left.toString() + right.reverse().toString();
            return longestPalindrome;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)
}