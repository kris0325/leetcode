/*
 * @lc app=leetcode.cn id=977 lang=java
 *
 * [977] 有序数组的平方
 *
 * https://leetcode.cn/problems/squares-of-a-sorted-array/description/
 *
 * algorithms
 * Easy (67.88%)
 * Likes:    973
 * Dislikes: 0
 * Total Accepted:    653.4K
 * Total Submissions: 962.3K
 * Testcase Example:  '[-4,-1,0,3,10]'
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 10^4
 * -10^4 
 * nums 已按 非递减顺序 排序
 * 
 * 
 * 
 * 
 * 进阶：
 * 
 * 
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 * 
 * 思路：
 * 相像双指针，
 * 1.对原数组元素进行平方，同时新建一个数组用于存放结果值
 * 2.左指针与右指针对应的元素比较大小，将较大值放入新数组的末尾，
 *   （即每次将比较后较大值放入新数组的末尾并移动新数组下标）
 * 3.同时将较大值对应的指针向中间方向移动，直到到两个指针相遇
 */

// @lc code=start
class Solution {
    public int[] sortedSquares(int[] nums) {
        int [] reslut = new int[nums.length];
        int left = 0;
        int right = nums.length -1;
        int index = nums.length -1;
        //square
        for(int i =0; i<= right;i++ ){
            nums[i] = nums[i] * nums[i];
        }
        //左右相向双指针，比较对应元素大小
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                reslut[index--] =nums[right];
                right--;
              
            } else if(nums[left] > nums[right]){
                reslut[index--] =nums[left];
                left++;
            }
        }
        return reslut;
    }
}
// @lc code=end

