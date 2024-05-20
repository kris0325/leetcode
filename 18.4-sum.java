/*
 * @lc app=leetcode id=18 lang=java
 *
 * [18] 4Sum
 *
 * https://leetcode.com/problems/4sum/description/
 *
 * algorithms
 * Medium (36.24%)
 * Likes:    11135
 * Dislikes: 1367
 * Total Accepted:    951.6K
 * Total Submissions: 2.6M
 * Testcase Example:  '[1,0,-1,0,-2,2]\n0'
 *
 * Given an array nums of n integers, return an array of all the unique
 * quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * 
 * 
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 
 * 
 * You may return the answer in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i< nums.length; i++){
            //剪枝
            if(nums[i]>target && nums[i]>=0 ) {
                break;
            }
            //nums[i]去重
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            for(int j = i+1; j < nums.length; j++){
                //剪枝
               if(nums[i] + nums[j]>target && nums[j]>=0 ) {
                break;
               }
               //nums[i]去重
               if(j>i+1 && nums[j]==nums[j-1]){
                continue;
              }
                int left = j+1;
                int right = nums.length-1;
                while (left < right) {
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    int longTarget = target;
                    //在Java中，int类型的最大值可以通过Integer.MAX_VALUE常量来表示，具体数值为2147483647，即$2^{31}-1，
                    // 所以 nums[i]+nums[j]+nums[left]+nums[right]不会溢出
                    // Long sum = Long.valueOf(nums[i]+nums[j]+nums[left]+nums[right]) ;
                    // Long longTarget = Long.valueOf(target) ;
                    if (sum < target) {
                        left++;
                    }
                    else if (sum > target) {
                        right--;
                    }
                    else {
                        //收集答案
                        result.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        //对nums[left]去重 
                        while(right > left && nums[left] == nums[left+1]){
                            left++;
                        }
                        //nums[right]去重
                        while (right > left && nums[right] == nums[right-1]) {
                            right--;
                        }
                        //双子针同时收缩
                        right--;
                        left++;
                    } 
                }
            }
        }
        return result;
    }
}
// @lc code=end

