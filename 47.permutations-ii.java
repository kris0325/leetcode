/*
 * @lc app=leetcode id=47 lang=java
 *
 * [47] Permutations II
 *
 * https://leetcode.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (58.51%)
 * Likes:    8373
 * Dislikes: 139
 * Total Accepted:    893.1K
 * Total Submissions: 1.5M
 * Testcase Example:  '[1,1,2]'
 
 * Given a collection of numbers, nums, that might contain duplicates, return
 * all possible unique permutations in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * ⁠[1,2,1],
 * ⁠[2,1,1]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> path = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);
        boolean [] used = new boolean[len];
        backTrack(nums, path, used);
        return result;
    }

    public void backTrack(int[] nums, List<Integer> path,boolean [] used ){
        if(nums.length == path.size()){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0 ; i < nums.length; i++){
            if(!used[i] && (i==0 || nums[i] != nums[i-1] || used[i-1])){
                path.add(nums[i]);
                used[i] = true;
                backTrack(nums, path, used);
                used[i] = false;
                path.removeLast();
            }
        }
    }
}


// class Solution2 {
//     List<List<Integer>> result = new ArrayList<>();
//     public List<List<Integer>> permuteUnique(int[] nums) {
//         List<Integer> path = new ArrayList<>();
//         int len = nums.length;
//         boolean [] used = new boolean[len];
//         backTrack(nums, path, used);
//         List<List<Integer>> deduped = result.stream()
//                 .distinct()
//                 .toList();
//         return deduped;
//     }

//     public void backTrack(int[] nums,
//      List<Integer> path,boolean [] used ){
//         if(nums.length == path.size()){
//             result.add(new ArrayList<>(path));
//             return;
//         }

//         for(int i = 0 ; i < nums.length; i++){
//             if(!used[i]){
//                 path.add(nums[i]);
//                 used[i] = true;
//                 backTrack(nums, path, used);
//                 used[i] = false;
//                 path.removeLast();
//             }
//         }
//     }
// }

// @lc code=end

