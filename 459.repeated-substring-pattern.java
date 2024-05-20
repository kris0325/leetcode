/*
 * @lc app=leetcode id=459 lang=java
 *
 * [459] Repeated Substring Pattern
 *
 * https://leetcode.com/problems/repeated-substring-pattern/description/
 *
 * algorithms
 * Easy (46.08%)
 * Likes:    6350
 * Dislikes: 517
 * Total Accepted:    447K
 * Total Submissions: 970.5K
 * Testcase Example:  '"abab"'
 *
 * Given a string s, check if it can be constructed by taking a substring of it
 * and appending multiple copies of the substring together.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aba"
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc"
 * twice.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 * 
 * 
 */

 /**
  * 思路：移动匹配
        如果s是repeatedSubstringPattern,
        那么s+s，去除首尾字符，剪切后的字符串必然包含s
  *
 */

// @lc code=start
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String twoS = s+s;
        return twoS.substring(1, 2*s.length()-1).contains(s) ?
               true : false;
    }
}
// @lc code=end

