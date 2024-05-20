/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 *
 * https://leetcode.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (40.56%)
 * Likes:    23735
 * Dislikes: 1719
 * Total Accepted:    4.6M
 * Total Submissions: 11.3M
 * Testcase Example:  '"()"'
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * 
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "()"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "()[]{}"
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "(]"
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 * 
 * 
 */

 /*
  * 思路：用栈实现

  先来分析一下 这里有三种不匹配的情况，

第一种情况，字符串里左方向的括号多余了 ，所以不匹配。
第二种情况，括号没有多余，但是 括号的类型没有匹配上。
第三种情况，字符串里右方向的括号多余了，所以不匹配。



第一种情况：已经遍历完了字符串，但是栈不为空，说明有相应的左括号没有右括号来匹配，所以return false

第二种情况：遍历字符串匹配的过程中，发现栈里没有要匹配的字符。所以return false

第三种情况：遍历字符串匹配的过程中，栈已经为空了，没有匹配的字符了，说明右括号没有找到对应的左括号return false

那么什么时候说明左括号和右括号全都匹配了呢，就是字符串遍历完之后，栈是空的，就说明全都匹配了。

  *
  *
  */

// @lc code=start

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stackS = new Stack<>();
        for(int i=0; i< s.length(); i++){
            if('(' == s.charAt(i)){
                stackS.push(')');
            } else if('{' == s.charAt(i)){
                stackS.push('}');
            } else if('[' == s.charAt(i)){
                stackS.push(']');
            }
            //第三种情况：遍历字符串匹配的过程中，栈已经空了，右边还有没匹配的字符，说明右括号没有找到对应的左括号，return false;
            //第二种情况：遍历字符串匹配的过程中，发现栈里没有匹配的字符，说明括号类型没匹配上，return false;
             else if(stackS.isEmpty() || stackS.peek() != s.charAt(i) ){
                return false;
            }
            //右括号与栈顶元素匹配，则pop(), 进行下一次操作
            else {
                stackS.pop();
            }
        }
        //第一种情况：此时已遍历完字符串，栈不为空，说明左括号多了，没有对应的右括号来匹配，return false, 否则return true;
        return stackS.isEmpty();
    }
}
// @lc code=end

