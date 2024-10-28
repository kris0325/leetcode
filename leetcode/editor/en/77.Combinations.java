import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers
 * chosen from the range [1, n].
 * <p>
 * You may return the answer in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to
 * be the same combination.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= n
 * <p>
 * <p>
 * Related Topics Backtracking 👍 8195 👎 223
 */
       
/*
 2024-07-02 17:51:44
*/

class Combinations {
    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
        System.out.printf("res"+solution.combine(4,2));
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
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            backtracking(new ArrayList<>(), k, n, 1);
            return res;

        }

        public void backtracking(List<Integer> path, int k, int n, int start) {
            if (k == path.size()) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = start; i <= n; i++) {
                path.add(i);
                backtracking(path, k, n, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}