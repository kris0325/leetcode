/*
 * @lc app=leetcode id=46 lang=java
 *
 * [46] Permutations
 *
 * https://leetcode.com/problems/permutations/description/
 *
 * algorithms
 * Medium (77.71%)
 * Likes:    18602
 * Dislikes: 313
 * Total Accepted:    2M
 * Total Submissions: 2.5M
 * Testcase Example:  '[1,2,3]'
 *
 * Given an array nums of distinct integers, return all the possible
 * permutations. You can return the answer in any order.
 * 
 * 
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        int len = nums.length;
        boolean [] used = new boolean[len];
        backTrack(nums, path, used);
        return result;
    }

    public void backTrack(int[] nums,
     List<Integer> path,boolean [] used ){
        if(nums.length == path.size()){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0 ; i < nums.length; i++){
            //to ensure distinct integer
            if(!used[i]){
                path.add(nums[i]);
                used[i] = true;
                backTrack(nums, path, used);
                used[i] = false;
                path.removeLast();
            }
        }
    }
}



// class Solution {
//     List<List<Integer>> result = new ArrayList<>();
//     public List<List<Integer>> permute(int[] nums) {
//         List<Integer> path = new ArrayList<>();

//         backTrack(nums, path);
//         return result;
//     }

//     public void backTrack(int[] nums, List<Integer> path){
//         if(nums.length == path.size()){
//             result.add(new ArrayList<>(path));
//             return;
//         }
//         for(int i = 0 ; i < nums.length; i++){
//             //to ensure distinct integer
//             if(!path.contains(nums[i])){
//                 path.add(nums[i]);
//                 backTrack(nums, path);
//                 path.removeLast();
//             }
//         }
//     }
// }


// class Main {
//     public static void main(String[] args) {
//         // Create a new Solution instance
//         Solution solution = new Solution();
//         // Create a test case
//         int[] nums = {1, 2, 3};
//         // Get the answer
//         boolean answer = solution.permute(nums);
//         // Print the answer
//         System.out.println(answer);
//     }
// }

// @lc code=end

