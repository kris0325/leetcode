import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s, remove duplicate letters so that every letter appears once
 * and only once. You must make sure your result is the smallest in lexicographical
 * order among all possible results.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "bcabc"
 * Output: "abc"
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 10⁴
 * s consists of lowercase English letters.
 * <p>
 * <p>
 * <p>
 * Note: This question is the same as 1081: https://leetcode.com/problems/
 * smallest-subsequence-of-distinct-characters/
 * <p>
 * Related Topics String Stack Greedy Monotonic Stack 👍 8715 👎 637
 */
       
/*
 2024-09-24 16:57:38
*/

class RemoveDuplicateLetters {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicateLetters().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicateLetters(String s) {
            int[] last = new int[128];
            Set<Character> visited = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                last[s.charAt(i)] = i;
            }
            //從棧底到棧頂，維護為字典（從小到大的）順序
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (visited.contains(c)) {
                    continue;
                }
                //棧不為空，且當前元素小於棧頂元素，並且棧頂元素後面還有會出現時，彈出棧頂元素，並清楚訪問紀錄
                while (!stack.isEmpty() && c < stack.peek() && i < last[stack.peek()]) {
                    visited.remove(stack.pop());
                }
                //當前元素入棧
                stack.push(c);
                visited.add(c);
            }
            StringBuilder sb = new StringBuilder();
            for (char c : stack) {
                sb.append(c);
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}