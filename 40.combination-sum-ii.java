/*
 * @lc app=leetcode id=40 lang=java
 *
 * [40] Combination Sum II
 *
 * https://leetcode.com/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (53.88%)
 * Likes:    9859
 * Dislikes: 261
 * Total Accepted:    861.2K
 * Total Submissions: 1.6M
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * Given a collection of candidate numbers (candidates) and a target number
 * (target), find all unique combinations in candidates where the candidate
 * numbers sum to target.
 * 
 * Each number in candidates may only be used once in the combination.
 * 
 * Note: The solution set must not contain duplicate combinations.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output: 
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output: 
 * [
 * [1,2,2],
 * [5]
 * ]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 * 
 * 
 * 
 * 
 * solution:
 * 
 *   * @param candidates 候选数组
     * @param begin      从候选数组的 begin 位置开始搜索
     * @param target     表示剩余，这个值一开始等于 target，基于题目中说明的"所有数字（包括目标数）都是正整数"这个条件
     * @param cur       从根结点到叶子结点的路径
     * @param combination 最终的所有组合

参考链接：https://leetcode.cn/problems/combination-sum-ii/solutions/14753/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
 * 
 * 深度优先搜素
 * 注意去重
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combination = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if(candidates.length == 0){
            return combination;
        }
        Arrays.sort(candidates);
        dfs(candidates, target, 0, combination, cur);
        return combination;
    }

    public void dfs(int[] candidates, int target, int begin,List<List<Integer>> combination, List<Integer> cur){
        if(target==0){
            combination.add(new ArrayList<>(cur));
            return;
        }
        for(int i = begin; i< candidates.length;i++){
          //大剪枝，减去 target比candidates[i]小的
          if(target<candidates[i]){
            return;
          }
          //小剪枝，排序后，同一层，相同元素，会产生重复组合，所以进行跳过去重
          if(i>begin && candidates[i] == candidates[i-1] ){
            continue;
          }
          cur.add(candidates[i]);
          //注意元素不能重复使用，所以下一层搜索begin从i+1开始搜索
          dfs(candidates,target - candidates[i],i+1,combination,cur);
          cur.remove(cur.size() -1);
        }
    }
}
// @lc code=end

