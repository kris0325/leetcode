/*
 * @lc app=leetcode id=27 lang=java
 *
 * [27] Remove Element
 *
 * https://leetcode.com/problems/remove-element/description/
 *
 * algorithms
 * Easy (56.70%)
 * Likes:    2333
 * Dislikes: 3370
 * Total Accepted:    2.9M
 * Total Submissions: 5.1M
 * Testcase Example:  '[3,2,2,3]\n3'
 *
 * Given an integer array nums and an integer val, remove all occurrences of
 * val in nums in-place. The order of the elements may be changed. Then return
 * the number of elements in nums which are not equal to val.
 * 
 * Consider the number of elements in nums which are not equal to val be k, to
 * get accepted, you need to do the following things:
 * 
 * 
 * Change the array nums such that the first k elements of nums contain the
 * elements which are not equal to val. The remaining elements of nums are not
 * important as well as the size of nums.
 * Return k.
 * 
 * 
 * Custom Judge:
 * 
 * The judge will test your solution with the following code:
 * 
 * 
 * int[] nums = [...]; // Input array
 * int val = ...; // Value to remove
 * int[] expectedNums = [...]; // The expected answer with correct length.
 * ⁠                           // It is sorted with no values equaling val.
 * 
 * int k = removeElement(nums, val); // Calls your implementation
 * 
 * assert k == expectedNums.length;
 * sort(nums, 0, k); // Sort the first k elements of nums
 * for (int i = 0; i < actualLength; i++) {
 * ⁠   assert nums[i] == expectedNums[i];
 * }
 * 
 * 
 * If all assertions pass, then your solution will be accepted.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 * Explanation: Your function should return k = 2, with the first two elements
 * of nums being 2.
 * It does not matter what you leave beyond the returned k (hence they are
 * underscores).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements
 * of nums containing 0, 0, 1, 3, and 4.
 * Note that the five elements can be returned in any order.
 * It does not matter what you leave beyond the returned k (hence they are
 * underscores).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 * 
 * 
 */

 /**
  * 思路: 双指针法
        solution1：相向指针
         1.初始化左右双指针分别指向首尾，
         2.判断左指针指向值是否==value，相等则将右指针值赋值给左指针，右指针左移一步，
         3.继续2.
         4.直到不相等，右移动左指针，
         5.继续判断2./4.,直到左右指针相遇， 右指针下标+1即为所求


        solution2：快慢指针
        初始化快慢指针为数组的头元素，for循环遍历数组
        1.num[fast] != val,则nums[slow++] = nums[fast++]，等效于同时移动快慢指针；
        2.num[fast] == val,则fast++，等效于快指针走一步，慢指针不走， 
        相单于用后一个元素覆盖当前值为val的元素，因为下一次赋值是用下一个元素赋值给当前元素，从而达到删除元素的效果
        return slow

        
  *
  *
  */
// @lc code=start
class Solution1 {
    public int removeElement(int[] nums, int val) {
        // if(nums.length == 0){
        //     return 0;
        // }
        // if (nums.length == 1) {
        //     return nums[0] == val ? 0 : 1; 
        // }
         int left = 0;
         int right = nums.length -1;
         while (left <= right) {
            if(val == nums[left]){
                nums[left] = nums[right--];
            }else {
                //兼容了nums.length == 0 或者1的情况
                left++;
            }
         }
         return left;
        
    }
}


class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;
        while (fast< nums.length) {
            if(nums[fast] != val){
                //不相等同时向后移动， 相等slow暂停一步，fast先走一，
                // 实际上等效于用后一个元素覆盖于val相等的元素，即删除该元素，slow在计数
                nums[slow++] = nums[fast++];
            } else {
                fast++;
            }     
        }
        
        return slow;
}
}
// @lc code=end

