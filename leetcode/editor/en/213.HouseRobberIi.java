/**
 * You are a professional robber planning to rob houses along a street. Each house
 * has a certain amount of money stashed. All houses at this place are arranged in
 * a circle. That means the first house is the neighbor of the last one. Meanwhile,
 * adjacent houses have a security system connected, and it will automatically
 * contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2)
 * , because they are adjacent houses.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [1,2,3]
 * Output: 3
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming 👍 9805 👎 151
 */
       
/*
 2024-07-12 16:35:45

 House Robber II
Category	Difficulty	Likes	Dislikes
algorithms	Medium (41.96%)	9805	151
Tags
dynamic-programming

Companies
microsoft
*/

class HouseRobberIi {
    public static void main(String[] args) {
        Solution solution = new HouseRobberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //DP: consider circle nums, nums[0], nums[length-1] are adjacent, so we can divide nums into two line new nums such as nums1: nums[0] -num[length-2] , nums2: nums[1] -num[length-1],
        // and get maximum = Math.max( rob(nums1), rob(nums2))
        //将打家劫舍robII  转化为 打家劫舍rob   即：環 -> 拆分為相連數組
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            //start to rob from the first house nums[0], so not consider to rob the last house nums[length-1]
            int robFromFirst = robLinear(nums, 0, nums.length - 2);
            // start to rob from the second house num[1], so consider to rob the last house nums[length-1]
            int robFromSecond = robLinear(nums, 1, nums.length - 1);
            return Math.max(robFromFirst, robFromSecond);
        }

        public int robLinear(int[] nums, int start, int end) {
            if (start == end) {
                //只有一家house
                return nums[start];
            }
            int[] dp = new int[nums.length];
            dp[start] = nums[start];
            dp[start+1] = Math.max(nums[start], nums[start + 1]);
            for (int i = start + 2; i <= end; i++) {
                //遞推公式：dp[i]的值為：偷house[i] ｜ 不偷house[i]， 取二者最大值
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[end];
        }

        private  int robLinear2(int[] houses, int start, int end) {
            if (start == end) {
                return houses[start];
            }
            int n = end - start + 1;
            int[] dp = new int[n];
            //注意处理初始化值，与dp[n]数组
            dp[0] = houses[start];
            dp[1] = Math.max(houses[start], houses[start + 1]);
            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + houses[start + i]);
            }
            return dp[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}