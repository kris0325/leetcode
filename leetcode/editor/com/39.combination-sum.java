/*
 * @lc app=leetcode id=39 lang=java
 *
 * [39] Combination Sum
 *
 * https://leetcode.com/problems/combination-sum/description/
 *
 * algorithms
 * Medium (70.32%)
 * Likes:    17899
 * Dislikes: 367
 * Total Accepted:    1.7M
 * Total Submissions: 2.4M
 * Testcase Example:  '[2,3,6,7]\n7'
 *
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen
 * numbers sum to target. You may return the combinations in any order.
 * 
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen
 * numbers is different.
 * 
 * The test cases are generated such that the number of unique combinations
 * that sum up to target is less than 150 combinations for the given input.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple
 * times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: candidates = [2], target = 1
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * All elements of candidates are distinct.
 * 1 <= target <= 40
 * 
 * 
 * 
 * solution1 
 * 深度优先搜索算法
 * 
 * 
 * 
 * solution2 
 * 1 先排序，剪枝
 * 2 再使用深度优先搜索算法
 */

// @lc code=start

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

class Solution1 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        int length = candidates.length;
        Deque<Integer> cur = new ArrayDeque<>();
        if(length==0){
            return combinations;
        }
        dfs(candidates,target,0,length,combinations,cur);
        return combinations;
    }


        public void dfs(int[] candidates, int target,  int begin, int length, List<List<Integer>> combinations, Deque<Integer> cur ){
            if(target <0){
                return;
            }
            if(target == 0){
               combinations.add(new ArrayList(cur)); 
               return;
            }
            for (int i = begin; i < length; i++){
                cur.addLast(candidates[i]);
                dfs(candidates,target-candidates[i], i, length,combinations,cur);
                cur.removeLast();
            }

        }
}



class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        int length = candidates.length;
        Arrays.sort(candidates); 
        Deque<Integer> cur = new ArrayDeque<>();
        if(length==0){
            return combinations;
        }
        dfs(candidates,target, 0, length,combinations,cur);
        return combinations;
    }


        public void dfs(int[] candidates, int target, int begin, int length, List<List<Integer>> combinations, Deque<Integer> cur ){
            if(target == 0){
               combinations.add(new ArrayList(cur)); 
               return;
            }
            for (int i = begin; i < length; i++){
                if(target < candidates[i]) {
                    break;
                }
                cur.addLast(candidates[i]);
                dfs(candidates,target-candidates[i], i, length,combinations,cur);
                cur.removeLast();
            }

        }
}


// @lc code=end

