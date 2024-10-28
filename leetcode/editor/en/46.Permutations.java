import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * <p>
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 * <p>
 * <p>
 * Related Topics Array Backtracking 👍 18993 👎 327
 */
       
/*
 2024-07-04 22:34:04

 Permutations
Category	Difficulty	Likes	Dislikes
algorithms	Medium (78.29%)	18993	327
Tags
backtracking

Companies
linkedin | microsoft

Given an array nums of distinct integers, return all the possible permutations.
 You can return the answer in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]

Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
*/

class Permutations {
    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
        int[] nums = {1, 2, 3};
        System.out.println(solution.permute(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        //backtracking：permutation 排列问题
        //时间复杂度：o(n!), 空间复杂度：o(n)
        List<List<Integer>> permutations = new ArrayList<>();
        public List<List<Integer>> permute(int[] nums) {
            backtracking(new ArrayList<>(), nums);
            return permutations;
        }

        public void backtracking(List<Integer> path, int[] nums) {
            if (path.size() == nums.length) {
                permutations.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if(path.contains(nums[i])){
                    continue;
                }
                path.add(nums[i]);
                backtracking(path, nums);
                path.remove(path.size() - 1);
            }
        }
    }


    class Solution0 {
        //backtracking：permutation 排列问题
        //时间复杂度：o(n!), 空间复杂度：o(n)
        //優化取重複： boolean[] usedNums
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            boolean[] usedNums = new boolean[nums.length];
            backtracking(nums, usedNums);
            return permutations;
        }

        //排列的单层loop: 每次都从0遍历到nums.length, 只需判断单前元素是否已经使用过，所以不需要组合问题中的startIndex参数
        public void backtracking(int[] nums, boolean[] usedNums) {
            if (path.size() == nums.length) {
                permutations.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                //单前元素已使用过
                if (usedNums[i]) {
                    continue;
                }
                usedNums[i] = true;
                path.add(nums[i]);
                backtracking(nums, usedNums);
                //回溯
                path.removeLast();
                usedNums[i] = false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}