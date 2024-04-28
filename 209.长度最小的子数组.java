/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 *
 * https://leetcode.cn/problems/minimum-size-subarray-sum/description/
 *
 * algorithms
 * Medium (46.41%)
 * Likes:    2088
 * Dislikes: 0
 * Total Accepted:    727.9K
 * Total Submissions: 1.6M
 * Testcase Example:  '7\n[2,3,1,2,4,3]'
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr]
 * ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 
 * 
 * 
 * 
 * 进阶：
 * 
 * 
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 * 
 * 
 * 
 * 思路：滑动窗口
 * 快慢指针移动形成滑动窗口，进行判断窗口值之和是否满足条件，再通过快慢指针移动推动滑动窗口
 */

// @lc code=start
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int reslut = Integer.MAX_VALUE;
        //滑动窗口数组值之和
        int sum = 0;
        //左边慢指针即滑动窗口起始位置
        int slow = 0;
        //滑动窗口的长度
        int subLength =0;
        //右边快指针即滑动窗口结束位置
        for(int fast = 0; fast < nums.length;fast++){
            sum += nums[fast];
            //当每次循环判断滑动窗口的截断出的数组值之和sum满足条件时， 进行比较并更新滑动窗口长度 subLength，
            //然后下一次循环，先更新sum，后更新向右移动慢指针，
            while (sum >= target) {
                subLength = fast -slow + 1;
                reslut = Math.min(reslut, subLength );
                // sum -= nums[slow++];
                //sum -= nums[slow++];注意顺序，即：
                sum -= nums[slow];
                slow ++;
            }
        }
        return reslut == Integer.MAX_VALUE? 0 : reslut;
    }
}

// @lc code=end

