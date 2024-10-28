/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 *
 * https://leetcode.com/problems/subsets/description/
 *
 * algorithms
 * Medium (76.47%)
 * Likes:    16199
 * Dislikes: 243
 * Total Accepted:    1.7M
 * Total Submissions: 2.2M
 * Testcase Example:  '[1,2,3]'
 *
 * Given an integer array nums of unique elements, return all possible subsets
 * (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in
 * any order.
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 *
 * Example 2:
 *
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 *
 * Constraints:
 *
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 *
 *
 * 78.子集问题
 * solution：
 * 问题拆分成 从n个元素的数组中选者k个元素的素全组合, 再收集所有组合即可
 */

// @lc code=start

import java.util.ArrayList;

class Solution {
    //backtracking: subset子集问题：
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums, 0);
        return result;
    }

    public void backtracking(int nums[], int startIndex) {
        result.add(new Arraylist<>(path));
        /*
可以不需要加终止条件，因为startIndex >= nums.size()，本层for循环本来也结束了。
单层搜索逻辑
求取子集问题，不需要任何剪枝！因为子集就是要遍历整棵树。
*/
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }
}

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        for (int k = 0; k <= nums.length; k++) {
            dfs(nums, 0, k, new ArrayList<>(), subsets);
        }
        return subsets;
    }

    public void dfs(int[] nums, int begin, int k, List<Integer> cur
            , List<List<Integer>> subsets) {
        if (k == cur.size()) {
            subsets.add(new ArrayList<>(cur));
            return;
        }
        //剪枝优化，由i < n 优化为
        // 剩余的元素与下标i(begin)的关系必须满足 k - cur.size() < n -i +1
        //下标是从begin是从0开始，所以计算上面<不等式右边需要+1
        for (int i = begin; i < nums.length - (k - cur.size() - 1); i++) {
            cur.add(nums[i]);
            dfs(nums, i + 1, k, cur, subsets);
            cur.remove(cur.size() - 1);
        }
    }
}
// @lc code=end

