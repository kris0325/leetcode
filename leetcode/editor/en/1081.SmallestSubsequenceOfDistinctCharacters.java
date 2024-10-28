import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s, return the lexicographically smallest subsequence of s that
 * contains all the distinct characters of s exactly once.
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
 * //distinct 按字典序排序，最小的subsequence，即总体的字典序最小
 * Output: "acdb"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 * <p>
 * <p>
 * <p>
 * Note: This question is the same as 316:
 * https://leetcode.com/problems/remove-duplicate-letters/
 * <p>
 * Related Topics String Stack Greedy Monotonic Stack 👍 2617 👎 195
 */
       
/*
 2024-09-24 21:33:10
*/

class SmallestSubsequenceOfDistinctCharacters {
    public static void main(String[] args) {
        Solution solution = new SmallestSubsequenceOfDistinctCharacters().new Solution();

        String s = "bcabc";
        solution.smallestSubsequence(s);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //monotonic stack 貪心+單調棧
        public String smallestSubsequence(String s) {
            //store char order by lexicographically
            Stack<Character> stack = new Stack<>();
            Set<Character> visited = new HashSet<>();
            //存儲當前字符在數組中的最後一個index
            /**
             * 當我們使用 s.charAt(i) 作為數組索引時，Java 會自動將 char 類型轉換為 int 類型。
             * 這種轉換是隱式的，不需要顯式轉換。
             * ASCII 值範圍：
             * ASCII 字符的值範圍是 0 到 127。
             * */
            int[] lastIndex = new int[128];
            for (int i = 0; i < s.length(); i++) {
                lastIndex[s.charAt(i)] = i;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (visited.contains(c)) {
                    continue;
                }
                //維護單調棧，即棧底到棧頂元素按照 lexicographically從小到大排序，
                //當前元素小於棧頂元素，且棧頂元素不是最後一個重複的，則彈出棧頂元素
                /**
                 * 貪心+單調棧
                 * 1.当前栈不为空
                 * 2.当前字符的字典序小于栈顶的字典序，因为要让总体的字典序最小，因此我们希望字典序越小的字符越早出现，反之越晚出现
                 * 3.栈顶元素在后续还会出现則刪掉棧頂元素，（不然删掉就没了），
                 * 4.否則入棧當前元素
                 * */
                while (!stack.isEmpty() && stack.peek() >= c && i < lastIndex[stack.peek()]) {
                    visited.remove(stack.pop());
                }
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