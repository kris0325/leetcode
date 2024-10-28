/**
 * You have intercepted a secret message encoded as a string of numbers. The
 * message is decoded via the following mapping:
 * <p>
 * "1" -> 'A' "2" -> 'B' ... "25" -> 'Y' "26" -> 'Z'
 * <p>
 * However, while decoding the message, you realize that there are many different
 * ways you can decode the message because some codes are contained in other codes
 * ("2" and "5" vs "25").
 * <p>
 * For example, "11106" can be decoded into:
 * <p>
 * <p>
 * "AAJF" with the grouping (1, 1, 10, 6)
 * "KJF" with the grouping (11, 10, 6)
 * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6"
 * is valid).
 * <p>
 * <p>
 * Note: there may be strings that are impossible to decode. Given a string s
 * containing only digits, return the number of ways to decode it. If the entire
 * string cannot be decoded in any valid way, return 0.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "12"
 * <p>
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * "12" could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "226"
 * <p>
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: s = "06"
 * <p>
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * "06" cannot be mapped to "F" because of the leading zero ("6" is different
 * from "06"). In this case, the string is not a valid encoding, so return 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 * <p>
 * <p>
 * Related Topics String Dynamic Programming 👍 12063 👎 4539
 */
       
/*
 2024-10-14 00:46:55
*/

class DecodeWays {
    public static void main(String[] args) {
        Solution solution = new DecodeWays().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp
        //tc:o(n)
        //sc:o(n)
        //具体地，设 f [i] 表示以i结尾的字符的解码方法数
        //  表示字符串 s 的前 i 个字符 s[1..i] 的解码方法数。在进行状态转移时，我们可以考虑最后一次解码使用了 s 中的哪些字符，那么会有下面的两种情况：
        //1.使用s[i]字符 s[i] 对应数字a 在[1,9] f[i] +=f[i-1]; 那么i 需初始化f[0] =1 空字符串也算1种方法，所以从i=1开始
        //2，使用s[i-1]s[i]2个字符 对应数字b在[10,26] f[i] +=f[i-2]; 至于i>1
        public int numDecodings(String s) {
            int[] dp = new int[s.length() + 1];
            dp[0] = 1;
            for (int i = 1; i <= s.length(); i++) {
                //取当前字符，由于数组下标从0开始，所以i需要i-1
                if (s.charAt(i - 1) != '0') {
                    dp[i] += dp[i - 1];
                }
                //取当前2个字符
                if (i>1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2)- '0') * 10  + s.charAt(i - 1) - '0') <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
            return dp[s.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}