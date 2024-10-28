/**
 * Given two strings s and t, return the number of distinct subsequences of s
 * which equals t.
 * <p>
 * The test cases are generated so that the answer fits on a 32-bit signed
 * integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "rabbbit", t = "rabbit"
 * Output: 3
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from s.
 * rabbbit
 * rabbbit
 * rabbbit
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "babgbag", t = "bag"
 * Output: 5
 * Explanation:
 * As shown below, there are 5 ways you can generate "bag" from s.
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length, t.length <= 1000
 * s and t consist of English letters.
 * <p>
 * <p>
 * Related Topics String Dynamic Programming 👍 6630 👎 291
 */
       
/*
 2024-07-26 00:13:39
 Distinct Subsequences
Category	Difficulty	Likes	Dislikes
algorithms	Hard (46.89%)	6630	291
Tags
string | dynamic-programming

Companies
Unknown
*/

class DistinctSubsequences {
    public static void main(String[] args) {
        Solution solution = new DistinctSubsequences().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question
        public int numDistinct(String s, String t) {
            //1.定義dp數組：dp[i][j]表示以i-1结尾的s子序列中出現以j-1結尾的t的個數
            int[][] dp = new int[s.length() + 1][t.length() + 1];
            //3.初始化 從遞推公式可以看出dp[i][j]從上方和左上方狀態推導而來（（因為是從s中找t，所無需考慮t往前一個元素的case,即dp[i][j-1]的情況），即左方），所以dp[i][0],dp[0][j]一定要初始化
            //根據dp數組定義，dp[i][0]表示以i-1结尾的s子序列中可（刪除元素），出現空字符串t的個數，所以值為1,dp[i][0]=1
            for (int i = 1; i <= s.length(); i++) {
                dp[i][0] = 1;
            }
            //dp[0][j]表示空字符中，出現字符串以j-1結尾的t的個數，那麼值為0,dp[0][j] =0
            for (int j = 1; j <= t.length(); j++) {
                dp[0][j] = 0;
            }
            //同理空字符串中，出现空字符串的個數，那麼值為1
            dp[0][0] = 1;

            //4.遍歷順序 從前往後
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j <= t.length(); j++) {
                    //2遞推公式：這一類問題，基本分2種情況
                    //case1. s[i-1] == t[j-1]
                    //此時dp[i][j]由2部分組成： 一部分使用s[i-1]來為結尾元素匹配，那麼個數最後一個字符確定相等後，就依賴上一狀態所以為dp[i-1][j-1]；
                    //另一部分是不使用s[i-1]，直接從s[i-2]來結尾元素匹配，那麼個數就是dp[i-1][j]
                    //例如： s：bagg 和 t：bag ，s[3] 和 t[2]是相同的，但是字符串s也可以不用s[3]来匹配，即用s[0]s[1]s[2]组成的bag。
                    //当然也可以用s[3]来匹配，即：s[0]s[1]s[3]组成的bag。
                    //所以当s[i - 1] 与 t[j - 1]相等时，dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    //case2. s[i-1] != t[j-1], dp[i][j]只由一部分組成，明確來最後一個元素不想等，那麼不使用s[i-1],直接從s[i-2]來結尾元素匹配，個數就是dp[i-1][j]
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        //case1:使用以s[i-1]結尾｜case2:不使用以s[i-1]結尾, 求和
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        //不相等，那直接s往前一個元素，以s[i-2]結尾開始匹配，（因為是從s中找t，所無需考慮t往前一個元素的case,即dp[i][j-1]的情況）
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[s.length()][t.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}