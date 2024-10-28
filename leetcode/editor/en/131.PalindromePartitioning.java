import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome. Return all possible palindrome partitioning of s.
 * <p>
 * <p>
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * <p>
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 * <p>
 * <p>
 * Related Topics String Dynamic Programming Backtracking 👍 12815 👎 493
 */
       
/*
 2024-07-02 19:20:24
*/

class PalindromePartitioning {
    public static void main(String[] args) {
        Solution solution = new PalindromePartitioning().new Solution();
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //回溯
        List<List<String>> res = new ArrayList<>();
//                Deque<String> list = new LinkedList<>();
        List<String> list = new LinkedList<>();

        public List<List<String>> partition(String s) {
            backtracking(s, 0);
            return res;
        }

        public void backtracking(String s, int startIndex) {
            //终止条件：startIndex切割线遍历到字符串末尾，证明找到了一组分割方案
            if (startIndex >= s.length()) {
                res.add(new ArrayList<>(list));
            }
            //横向遍历s
            for (int i = startIndex; i < s.length(); i++) {
                if (isPalindrome(s, startIndex, i)) {
                    //第一次切割的子串a / 或者aa，或者aab
                    String sub = s.substring(startIndex, i + 1);
                    list.addLast(sub);
                } else {
                    //第一次切割 子串不是回文，则跳过单前方案，进入下一轮横向切割
                    continue;
                }
                //纵向遍历s, 即第二次。。。n次知道切割线到达startIndex == s.length()， 切割 a / 或者b
                backtracking(s, i + 1);
                //回溯 不断跳到上一层，直至清空deque， 开始下一次横向递归切割
                list.removeLast();
            }
        }

        public boolean isPalindrome(String s, int start, int end) {
            for (int i = start, j = end; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}