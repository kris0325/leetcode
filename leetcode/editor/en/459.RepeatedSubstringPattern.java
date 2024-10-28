  /**
Given a string s, check if it can be constructed by taking a substring of it 
and appending multiple copies of the substring together. 

 
 Example 1: 

 
Input: s = "abab"
Output: true
Explanation: It is the substring "ab" twice.
 

 Example 2: 

 
Input: s = "aba"
Output: false
 

 Example 3: 

 
Input: s = "abcabcabcabc"
Output: true
Explanation: It is the substring "abc" four times or the substring "abcabc" 
twice.
 

 
 Constraints: 

 
 1 <= s.length <= 10⁴ 
 s consists of lowercase English letters. 
 

 Related Topics String String Matching 👍 6472 👎 530

*/
       
/*
 2024-10-27 23:02:29
*/

class RepeatedSubstringPattern {
      public static void main(String[] args) {
           Solution solution = new RepeatedSubstringPattern().new Solution();
      }
      
      public class TreeNode {
        int val; TreeNode left; TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
       
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}