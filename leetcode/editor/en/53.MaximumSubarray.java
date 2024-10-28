/**
 * Given an integer array nums, find the subarray with the largest sum, and return
 * its sum.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * <p>
 * <p>
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another
 * solution using the divide and conquer approach, which is more subtle.
 * <p>
 * Related Topics Array Divide and Conquer Dynamic Programming 👍 33917 👎 1440
 */
       
/*
 2024-07-02 22:03:46
 Maximum Subarray
Category	Difficulty	Likes	Dislikes
algorithms	Medium (50.76%)	33917	1440
Tags
array | divide-and-conquer | dynamic-programming

Companies
bloomberg | linkedin | microsoft
*/

class MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
        System.out.println(solution.maxSubArray(new int[]{-2, -1, -3}));;
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //solution1 : Greedy Algorithm
        public int maxSubArray(int[] nums) {
            int result = Integer.MIN_VALUE;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                count += nums[i];
                //取累计区间的的最大值，相当于不断更新最大子序列的终止位置
                result = Math.max(count, result);
                if (count <= 0) {
                    //因为负数拉低子序列的和，所以重置最大子序列的初始位置
                    count = 0;
                }
            }
            return result;
        }
    }

    class Solution {
        //solution1 : DP Algorithm
        public int maxSubArray(int[] nums) {
            int maxSubArraySum = Integer.MIN_VALUE;
            //定義dp數組 dp[i]表示以nums[i]結尾的數組的最大子數組之和
            int [] dp = new int[nums.length];
            //初始化 根據題意子數組的定義，只有首元素時，num[0]即為子數組，所以
            dp[0] = nums[0];
            maxSubArraySum = dp[0];
            //遍歷順序 當前狀態依賴上一個狀態 所以從前往後
            for(int i = 1; i < nums.length; i++){
                //遞推公式： dp[i]的狀態只有2種情況可推出，取二者較大值：
                // 1.dp[i-1] + nums[i]: nuns[i]加入前面數組組成子數組，即dp[i-1] >= 0
                // 2.或者nums[i]: 從頭開始計算, ，即dp[i-1] < 0, 和為負數時，會拉低加上下一個元素nums[i]組成新子數組的和，所以從頭開始計算
                dp[i] = Math.max(nums[i] + dp[i-1], nums[i]);
                //局部出現新的最值時，更新maxSubArraySum
                maxSubArraySum = Math.max(maxSubArraySum, dp[i]);
            }
            return maxSubArraySum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}