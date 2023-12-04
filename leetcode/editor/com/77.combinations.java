/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 *
 * https://leetcode.com/problems/combinations/description/
 *
 * algorithms
 * Medium (69.56%)
 * Likes:    7886
 * Dislikes: 206
 * Total Accepted:    822.7K
 * Total Submissions: 1.2M
 * Testcase Example:  '4\n2'
 *
 * Given two integers n and k, return all possible combinations of k numbers
 * chosen from the range [1, n].
 * 
 * You may return the answer in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered
 * to be the same combination.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 20
 * 1 <= k <= n
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    public List<List<Integer>> combine(int n, int k) { 
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if( k <= 0 || k > n){
            return combinations;
        }
        dfs(n, k, 1, cur, combinations);
        return combinations;
    }

    public void dfs(int n, int k, int begin,
      List<Integer> cur, List<List<Integer>> combinations){
        if(cur.size() == k){
            combinations.add(new ArrayList<>(cur));
            return;
        }
        for(int i = begin; i <= n; i++){
            cur.add(i);
            dfs(n, k, i+1, cur, combinations);
            cur.remove(cur.size() - 1);
        }
    }
}

class Solution {
    public List<List<Integer>> combine(int n, int k) { 
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if( k <= 0 || k > n){
            return combinations;
        }
        dfs(n, k, 1, cur, combinations);
        return combinations;
    }

    public void dfs(int n, int k, int begin,
      List<Integer> cur, List<List<Integer>> combinations){
        if(cur.size() == k){
            combinations.add(new ArrayList<>(cur));
            return;
        }
        //剪枝條件，搜索的begin边界需要满足 剩余的元素需要足够多，也就是 k - cur.size() <= n-i +1，否者進行剪枝，
        for(int i = begin; i <= n - (k - cur.size()) +1; i++){
            cur.add(i);
            dfs(n, k, i+1, cur, combinations);
            cur.remove(cur.size() - 1);
        }
    }
}
// @lc code=end

