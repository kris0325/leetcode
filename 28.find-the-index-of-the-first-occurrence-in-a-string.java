/*
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Find the Index of the First Occurrence in a String
 *
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 *
 * algorithms
 * Easy (42.25%)
 * Likes:    5645
 * Dislikes: 390
 * Total Accepted:    2.4M
 * Total Submissions: 5.8M
 * Testcase Example:  '"sadbutsad"\n"sad"'
 *
 * Given two strings needle and haystack, return the index of the first
 * occurrence of needle in haystack, or -1 if needle is not part of
 * haystack.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= haystack.length, needle.length <= 10^4
 * haystack and needle consist of only lowercase English characters.
 * 
 * 
 */

 /**
  * 思路：滑動窗口 / haystack.substring(i, i+needle.length()) 也是雙指針法，只是前後指針同步移動
        1.needle.length > haystack,直接return -1;
        2.從haystack的起始位置i=0開始,取needle.length長度子字符串比needle比較，
          字符串不相同，則滑動needle.length長度繼續比較，直到i到達haystack.length-1-needle.length位置，
          字符串相同，則return 位置i;
  **/
// @lc code=start
class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack.length()<needle.length()){
            return -1;
        }
        for(int i = 0; i <= haystack.length()-needle.length();i++ ){
            if(needle.equals(haystack.substring(i, i+needle.length()))){
                return i;
            }
        }
        return -1;
    }
}
// @lc code=end

