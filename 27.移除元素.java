/*
 * @lc app=leetcode.cn id=27 lang=java
 *
 * [27] 移除元素
 *
 * https://leetcode.cn/problems/remove-element/description/
 *
 * algorithms
 * Easy (59.34%)
 * Likes:    2181
 * Dislikes: 0
 * Total Accepted:    1.4M
 * Total Submissions: 2.4M
 * Testcase Example:  '[3,2,2,3]\n3'
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 
 * 
 * 
 * 说明:
 * 
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 
 * 你可以想象内部操作如下:
 * 
 * 
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * 
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而
 * nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,3,0,4]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0,
 * 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 * 
 * 思路：in-palce原地移出数组元素，
 * 方法1.考虑使用相向双指针
 * 方法2.考虑使用快慢双指针
 */

// @lc code=start
class Solution11 {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right =nums.length -1;
        int tmp = 0;
        while (left <= right) {
            if(nums[left] == val){
                tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}

class Solution12 {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right =nums.length -1;
        // int tmp = 0;
        while (left <= right) {
            if(nums[left] == val){
                // tmp = nums[left];
                //直接用右边的值覆盖左边的值
                nums[left] = nums[right];
                // nums[right] = tmp;
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}

class Solution {
    public int removeElement(int[] nums, int val) {
        //快指针: 寻找新数组的元素，新数组就是不含有目标元素的数组，所以一直先右移动，遇到相同元素时，继续走；
        //       遇到不同元素时， 交换快慢指针对应数组的元素，从而实现将新元素向左移动
        //慢指针: 用于收集新元素，指向更新 新数组下标的位置，所以遇到相同元素时，不移动；       
        //       遇到不同元素时右移一步
        int slowPointer = 0;
        for(int fastPointer = 0; fastPointer< nums.length; fastPointer++){
            if(nums[fastPointer] != val){
                nums[slowPointer] = nums[fastPointer];
                slowPointer++;
            }
        }  
        return slowPointer;
    }
}
// @lc code=end

