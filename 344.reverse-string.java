/*
 * @lc app=leetcode id=344 lang=java
 *
 * [344] Reverse String
 *
 * https://leetcode.com/problems/reverse-string/description/
 *
 * algorithms
 * Easy (77.80%)
 * Likes:    8309
 * Dislikes: 1154
 * Total Accepted:    2.5M
 * Total Submissions: 3.2M
 * Testcase Example:  '["h","e","l","l","o"]'
 *
 * Write a function that reverses a string. The input string is given as an
 * array of characters s.
 * 
 * You must do this by modifying the input array in-place with O(1) extra
 * memory.
 * 
 * 
 * Example 1:
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s[i] is a printable ascii character.
 * 
 * 
 */

 /**
  * 思路：双指针算法
     本题要求使用原地算法 in-place算法 with O(1) extra memory，不能使用额外内存，所以考虑使用双指针算法，
     left,right指针分别指向数组首尾，
     使用临时变量tmp暂存 s[left]，然后交换首尾元素，
     然后左右指针收缩。

     扩展：本题如果不要求使用in-place算法，可以通过stack先进后出的数据结构实现求解
 */

// @lc code=start
class Solution {
    public void reverseString(char[] s) {
        int left = 0; 
        int right = s.length-1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}
// @lc code=end

