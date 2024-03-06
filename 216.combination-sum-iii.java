/*
 * @lc app=leetcode id=216 lang=java
 *
 * [216] Combination Sum III
 *
 * https://leetcode.com/problems/combination-sum-iii/description/
 *
 * algorithms
 * Medium (69.07%)
 * Likes:    5799
 * Dislikes: 107
 * Total Accepted:    485.5K
 * Total Submissions: 700.5K
 * Testcase Example:  '3\n7'
 *
 * Find all valid combinations of k numbers that sum up to n such that the
 * following conditions are true:
 * 
 * 
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * 
 * 
 * Return a list of all possible valid combinations. The list must not contain
 * the same combination twice, and the combinations may be returned in any
 * order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 * 
 * Example 2:
 * 
 * 
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9], the smallest sum we can get is
 * 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= k <= 9
 * 1 <= n <= 60
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrack( n , k,1,0 );
        return result;
    }
    public void backTrack(Integer sumTarget, int k, int begin, int sum ){
        //剪枝
        if(sum > sumTarget){
            return;
        }

        if(path.size() == k && sum == sumTarget ){
            result.add(new ArrayList<>(path));
            return;
        }
        //剪枝 i <= 9 - (k -path.size()) +1
        for(int i = begin; i <= 9 - (k -path.size()) +1 ; i++){
            path.add(i);
            sum += i;
            backTrack(sumTarget,k, i+1, sum);
            path.removeLast();
            sum -= i;
            
        }
    }
}
// @lc code=end

