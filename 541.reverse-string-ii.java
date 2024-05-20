/*
 * @lc app=leetcode id=541 lang=java
 *
 * [541] Reverse String II
 *
 * https://leetcode.com/problems/reverse-string-ii/description/
 *
 * algorithms
 * Easy (50.66%)
 * Likes:    1944
 * Dislikes: 3753
 * Total Accepted:    243.3K
 * Total Submissions: 479.8K
 * Testcase Example:  '"abcdefg"\n2'
 *
 * Given a string s and an integer k, reverse the first k characters for every
 * 2k characters counting from the start of the string.
 * 
 * If there are fewer than k characters left, reverse all of them. If there are
 * less than 2k but greater than or equal to k characters, then reverse the
 * first k characters and leave the other as original.
 * 
 * 
 * Example 1:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 * Example 2:
 * Input: s = "abcd", k = 2
 * Output: "bacd"
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^4
 * 
 * 
 */

import java.lang.reflect.Array;

/**
  * 思路：双指针法
        本题与344.Reverse String解法思路相同，都是用双指针法，
        按照规律，
        1.可以将字符串s 每2k间段进行切分，每段都是reverse前k个字符，
        2.然后考虑边界条件，即末尾分情况讨论：
          2.1 末尾剩余长度lenth满足 k <lenth < 2k, 即 pos+k < s.length,
              则反转前k个字符，其实操作和第1.步一样, 即 reverse(ch, pos, pos+k-1)；
          2.2 末尾剩余长度lenth满足 lenth < k, 即pos+k > s.length,
              则反转所有剩余字符，即 reverse(ch, pos, ch.length-1);

 */
// @lc code=start
class Solution {
    public String reverseStr(String s, int k) {
        char[]s2char = s.toCharArray();
        for(int pos = 0; pos < s.length(); pos += 2*k){
            if(pos+k < s.length()){
                reverse(s2char, pos, pos+k-1);
            } else {
                reverse(s2char, pos, s2char.length-1);
            }
        }
        return new String(s2char);
    }

    public void reverse(char[] str, int leftPos, int rightPos){
        while(leftPos < rightPos){
            char tmp = str[leftPos];
            str[leftPos] = str[rightPos];
            str[rightPos] = tmp;
            leftPos++;
            rightPos--;
        }
    }
}
// @lc code=end

