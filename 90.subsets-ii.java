/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 *
 * https://leetcode.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (56.68%)
 * Likes:    9237
 * Dislikes: 265
 * Total Accepted:    819.4K
 * Total Submissions: 1.4M
 * Testcase Example:  '[1,2,2]'
 *
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets (the power set).
 * 
 * The solution set must not contain duplicate subsets. Return the solution in
 * any order.
 * 
 * 
 * Example 1:
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * 
 * 
 * 90.子集ii
 * Solution1 思路：使用回溯算法, 对unique数组的全排列的变种
 *      问题拆分成 从n个元素的数组中选者k个元素的素全组合
 *      , 再收集所有子组合时去重即可
 *
 * > https://leetcode.cn/problems/subsets-ii/solutions/1/90-zi-ji-iiche-di-li-jie-zi-ji-wen-ti-ru-djmf/
 * Solution2 思路：这道题目和78.子集区别就是集合里有重复元素了，而且求取的子集要去重。
 *                1 注意去重需要先对集合排序
 *                2 画出树形图，从图中可以看出，同一树层上重复取2 就要过滤掉，
 *                 同一树枝上就可以重复取2，因为同一树枝上元素的集合才是唯一子集！
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// Solution1 for循环中遍历中排序，时间复杂度太高 
class Solution1 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        for(int k = 0; k<= nums.length; k++){
            dfs(nums,0,k,new ArrayList<>(),subsets);
        }    
        return subsets;    
    }

    public void dfs(int[] nums, int begin, int k, List<Integer> cur
    , List<List<Integer>> subsets ){
        if(k == cur.size()){
            //donnot collect dep subset
            if(isContainTargeList(subsets, new ArrayList<>(cur))){
                return;
            }
            subsets.add(new ArrayList<>(cur));
            return;
        }
        for(int i =begin; i <nums.length - (k - cur.size()-1); i++){
            cur.add(nums[i]);
            dfs(nums, i+1, k, cur, subsets);
            cur.remove(cur.size()-1);
        }
    }

    public boolean isContainTargeList(List<List<Integer>> subsets, List<Integer> targeList ){
        //因为是组合，不考虑元素顺序，所有需要遍历subsets,判断是否是否包含与targeList相同的组合
        for(int i = 0; i< subsets.size();i++){
            Collections.sort(subsets.get(i));
            Collections.sort(targeList);
            if(subsets.get(i).equals(targeList)){
                return true;
            }
        }
        return false;
        
    }
}

class Solution {
    //剪枝：树的同一层，相同元素需要去重，直接跳过
    List<List<Integer>> subsets = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(nums,0);
        return subsets;
    }

    public void dfs(int[] nums, int begin){ 
        subsets.add(new ArrayList<>(path));

        for(int i = begin; i< nums.length; i++ ){
            //树的同一层，相同元素需要去重，直接跳过
            if(i > begin && nums[i] == nums[i-1]){
                continue;
            }
            path.add(nums[i]);
            dfs(nums, i+1);
            path.removeLast();
        }
    }

}


// @lc code=end

