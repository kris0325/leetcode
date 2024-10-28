/**
 * Given a string s, return the number of palindromic substrings in it.
 * <p>
 * A string is a palindrome when it reads the same backward as forward.
 * <p>
 * A substring is a contiguous sequence of characters within the string.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 * <p>
 * <p>
 * Related Topics Two Pointers String Dynamic Programming 👍 10773 👎 238
 */
       
/*
 2024-07-28 21:19:02
 Palindromic Substrings
Category	Difficulty	Likes	Dislikes
algorithms	Medium (70.08%)	10773	238
Tags
string | dynamic-programming

Companies
facebook | linkedin


*/

class PalindromicSubstrings {
    public static void main(String[] args) {
        Solution solution = new PalindromicSubstrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question: 判断是否是回文字符串，依賴除去首尾字符後內層字符串是否是回文，可根據這個特性定義dp[i][j]，然后累加计算字符串数量
        public int countSubstrings(String s) {
            int count = 0;
            //1.定义dp[i][j]表示闭区间[i,i]上的字符串是否是回文字符串，
            boolean[][] dp = new boolean[s.length()][s.length()];
            //3.初始化，比較前默認不是回文，所以都是false

            //4.遍歷順序，由於外層字符串依賴內層字符串是否是回文字符串
            //即dp[i][j] 依賴左下方的狀態dp[i+1][j-1]，
            // 那麼遍歷順序應該為從下到上，從左到右
                   //從下到上
            for (int i = s.length() - 1; i >= 0; i--) {
                   //從左到右
                for (int j = i; j < s.length(); j++) {
                    //2.遞推公式：根據s[i]，s[j]是否相等分2種情況
                    // c1. s[i]!=s[j],那麼dp[i][j] = false；
                    // c2. s[i]==s[j] ,又分3種情況
                    //case1. i==j,一個字符的字符串“a”，dp[i][j] = true；
                    //case2. j-i=1,2個字符的字符串"aa",dp[i][j] = true；
                    //case3. j-i>1,多個字符的字符串"a...a",dp[i][j]依賴除去首尾字符的內層字符串的狀態，即if(dp[i+1][j-1]) dp[i][j] = true；
                    if (s.charAt(i) == s.charAt(j)) {
                        if (j - i <= 1) {
                            count++;
                            dp[i][j] = true;
                        } else if (dp[i + 1][j - 1]) {
                            count++;
                            dp[i][j] = true;
                        }
                    }
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}