import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in
 * any order.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * <p>
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * <p>
 * <p>
 * Related Topics Array Backtracking Bit Manipulation 👍 9832 👎 324
 */
       
/*
 2024-08-29 20:02:02
*/

class SubsetsIi {
    public static void main(String[] args) {
        Solution solution = new SubsetsIi().new Solution();
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
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            //排序
            Arrays.sort(nums);
            backtracking(new ArrayList<>(), nums, 0);
            return res;
        }

        public void backtracking(List<Integer> path, int[] nums, int start) {
            res.add(new ArrayList<>(path));
            for (int i = start; i < nums.length; i++) {
                //注意條件：i != start 表示的是橫行遍歷，
                //保證相同元素只按順序加一次，不會重複
                if (i != start && nums[i] == nums[i - 1]) {
                    continue;
                }
                path.add(nums[i]);
                //進入每一層backtracking， 縱向遍歷“i” == start （start 從  i + 1開始）
                backtracking(path, nums, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}