/*
 * @lc app=leetcode id=1047 lang=java
 *
 * [1047] Remove All Adjacent Duplicates In String
 *
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/
 *
 * algorithms
 * Easy (69.48%)
 * Likes:    6528
 * Dislikes: 252
 * Total Accepted:    566.2K
 * Total Submissions: 814K
 * Testcase Example:  '"abbaca"'
 *
 * You are given a string s consisting of lowercase English letters. A
 * duplicate removal consists of choosing two adjacent and equal letters and
 * removing them.
 * 
 * We repeatedly make duplicate removals on s until we no longer can.
 * 
 * Return the final string after all such duplicate removals have been made. It
 * can be proven that the answer is unique.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation: 
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent
 * and equal, and this is the only possible move.  The result of this move is
 * that the string is "aaca", of which only "aa" is possible, so the final
 * string is "ca".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "azxxzy"
 * Output: "ay"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * 
 * 
 */

 /*
  * 思路：
          solution1: 队列实现
         遍历字符串，并存入栈，
         当当前元素和队列第一个元素相同时，跳过此次循环i++，同时弹出队列第一个元素；
         否则将当前元素存入队列；
         遍历完后，遍历弹出队列元素组成新字符串即可


        solution2: in-place 将字符串当栈使用

        solution3: 双指针实现




        solution4: 栈实现
         遍历字符串，并存入栈，
         当当前元素和栈顶元素相同时，跳过此次循环i++，同时弹出栈顶元素；
         否则将当前元素存入栈；
         遍历完后，遍历弹出栈内元素组成新字符串，并反转字符串即可




 */

// @lc code=start

import java.sql.Array;
import java.util.ArrayDeque;

class Solution1 {
    public String removeDuplicates(String s) {
        ArrayDeque deque = new ArrayDeque<>();
        for(int i =0;i<s.length();i++){
            if(deque.isEmpty() || (char) deque.peek() != s.charAt(i)){
                deque.push(s.charAt(i));
            } else{
                deque.pop();
            }
        }
        String newS = "";
        while (!deque.isEmpty()) {
            newS = deque.pop()+ newS;  
        }
        return newS;
    }
}

class Solution {
    public String removeDuplicates(String s) {
        char[] charS = s.toCharArray();
        int fast = 0;
        int slow = 0;
        
        while (fast < charS.length) {  
            // Use the fast pointer to overwrite the value at the slow pointer
            
            // When encountering the same value before and after, the slow pointer retreats, 
            // and the same value will be overwritten by the fast pointer in the next loop
            if(slow > 0 && charS[slow-1] == charS[fast]){
                slow--;
            } else{
                charS[slow++] = charS[fast]; 
            } 
            //每次循环都需要移动fast
            fast++;
        }
        return new String(charS,0,slow);
    }
}

// @lc code=end
