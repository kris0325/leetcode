import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (
 * the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in
 * any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [0]
 * Output: [[],[0]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 * <p>
 * <p>
 * Related Topics Array Backtracking Bit Manipulation 👍 17391 👎 284
 */
       
/*
 2024-08-29 12:16:54
*/

class Subsets {
    public static void main(String[] args) {
        Solution solution = new Subsets().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

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
        //backtracking
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> subsets(int[] nums) {
            backtracking(new ArrayList<>(), nums,  0);
            return res;
        }

        public void backtracking(List<Integer> path, int[] nums,  int start) {
            //注意參數path引用，必須收集結果時需要new ArrayList<>(path)
            res.add(new ArrayList<>(path));
            for (int i = start; i < nums.length; i++) {
                path.add(nums[i]);
                backtracking(path, nums,  i + 1);
                path.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}