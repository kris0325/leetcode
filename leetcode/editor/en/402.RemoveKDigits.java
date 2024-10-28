import java.util.Stack;

/**
 * Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219
 * which is the smallest.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output
 * must not contain leading zeroes.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing
 * which is 0.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= k <= num.length <= 10⁵
 * num consists of only digits.
 * num does not have any leading zeros except for the zero itself.
 * <p>
 * <p>
 * Related Topics String Stack Greedy Monotonic Stack 👍 9598 👎 496
 */
       
/*
 2024-09-25 11:22:20
*/

class RemoveKDigits {
    public static void main(String[] args) {
        Solution solution = new RemoveKDigits().new Solution();
        solution.removeKdigits("1432219", 3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //monotonic stack
        public String removeKdigits(String num, int k) {
            Stack<Character> stack = new Stack<>();
            //維護棧頂到棧頂是從小到大的單調棧+贪心
            //stack.peek() > (int) num.charAt(i)，棧頂元素較大時，彈出
            for (int i = 0; i < num.length(); i++) {
                while (!stack.isEmpty() && k > 0 && (int) stack.peek() > (int) num.charAt(i)) {
                    stack.pop();
                    k--;
                }
                stack.push(num.charAt(i));
            }
            StringBuilder sb = new StringBuilder();
            for (Character i : stack) {
                sb.append(i);
            }

            while (!sb.isEmpty() && sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
            }

            while (k > 0 && !sb.isEmpty()) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            if (sb.isEmpty()) {
                return "0";
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}