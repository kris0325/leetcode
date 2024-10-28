  /**
Given two strings s and goal, return true if and only if s can become goal 
after some number of shifts on s. 

 A shift on s consists of moving the leftmost character of s to the rightmost 
position. 

 
 For example, if s = "abcde", then it will be "bcdea" after one shift. 
 

 
 Example 1: 
 Input: s = "abcde", goal = "cdeab"
Output: true
 
 Example 2: 
 Input: s = "abcde", goal = "abced"
Output: false
 
 
 Constraints: 

 
 1 <= s.length, goal.length <= 100 
 s and goal consist of lowercase English letters. 
 

 Related Topics String String Matching 👍 3627 👎 177

*/
       
/*
 2024-08-04 18:23:59
*/

class RotateString {
      public static void main(String[] args) {
           Solution solution = new RotateString().new Solution();
      }
        
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
          //选择任意位置，将字符串切分为两个子字符串 s=LR ；
          //将 R 移动至 L 前面得到 goal=RL ；
          //此时，称 goal 为 s 的一个「旋转字符串」。
          //如下图所示，根据旋转字符串特点，若构造一个拼接字符串 goalgoal ，则有 goalgoal =RLRL=RsL ，
          // 即拼接字符串 goalgoal 中包含原字符串 s
          // 。因此，goal 为 s 的旋转字符串的「充要条件」为：
          //
          //1.字符串 s , goal 的长度相等；
          //2.拼接字符串 goal goal 中包含原字符串 s ；
          //

    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (goal + goal).contains(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}