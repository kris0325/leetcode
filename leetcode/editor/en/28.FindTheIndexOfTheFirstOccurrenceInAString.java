  /**
Given two strings needle and haystack, return the index of the first occurrence 
of needle in haystack, or -1 if needle is not part of haystack. 

 
 Example 1: 

 
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
 

 Example 2: 

 
Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.
 

 
 Constraints: 

 
 1 <= haystack.length, needle.length <= 10⁴ 
 haystack and needle consist of only lowercase English characters. 
 

 Related Topics Two Pointers String String Matching 👍 6159 👎 450

*/
       
/*
 2024-10-27 23:00:52
*/

class FindTheIndexOfTheFirstOccurrenceInAString {
      public static void main(String[] args) {
           Solution solution = new FindTheIndexOfTheFirstOccurrenceInAString().new Solution();
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
    public int strStr(String haystack, String needle) {
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}