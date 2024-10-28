/**
 * Given an integer array nums, return true if you can partition the array into
 * two subsets such that the sum of the elements in both subsets is equal or false
 * otherwise.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming 👍 12200 👎 247
 */
       
/*
 2024-07-04 15:49:59

Partition Equal Subset Sum
Category	Difficulty	Likes	Dislikes
algorithms	Medium (46.27%)	12200	247
Tags
dynamic-programming

Companies
ebay

Given an integer array nums, return true if you can partition the array into two subsets
such that the sum of the elements in both subsets is equal or false otherwise.

Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
*/

class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new PartitionEqualSubsetSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //01dp: 套用01背包 model
        //第i个元素（物品） 重量为nums[i] 价值也为nums[i]
        //背包重量为 target = sum/2
        //题目转化为价值 dp[j] = max(dp[j], dp[i- nums[i]] + nums[i])
        public boolean canPartition(int[] nums) {
            int sum = 0;
            int n = nums.length;

            for (int num : nums) {
                sum += num;
            }
            //sum为奇数无法拆分
            if(sum %2 != 0){
                return false;
            }
            int bagWeight = sum / 2;
            int targetValue = sum / 2;
            int []dp = new int[bagWeight+1];
            dp[0] = 0;
            for(int i = 0; i < n; i++){
                for(int j = bagWeight; j >= nums[i]; j--){
                    //背包容量为bagWeight，装物品i, 物品i的重量是nums[i], 价值也是nums[i]
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);

                }
                //剪枝：每次完成内层loop， 立即检查当前i个元素就是否已满足条件 已寻找到可分割的sunset， i无需再遍历计算dp了
                if(dp[bagWeight] == targetValue){
                    return true;
                }
            }
            return dp[bagWeight] == targetValue;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}