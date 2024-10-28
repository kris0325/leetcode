/**
 * You are a professional robber planning to rob houses along a street. Each house
 * has a certain amount of money stashed, the only constraint stopping you from
 * robbing each of them is that adjacent houses have security systems connected and
 * it will automatically contact the police if two adjacent houses were broken into
 * on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (
 * money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming 👍 21014 👎 422
 */
       
/*
 2024-07-12 15:20:03
*/

class HouseRobber {
    public static void main(String[] args) {
        Solution solution = new HouseRobber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            return stealStore(nums);
        }

        public int stealStore(int[] stores) {
            if (stores == null || stores.length == 0) {
                return 0;
            }
            if (stores.length == 1) {
                return stores[0];
            }
            //定義dp數組：表示rob前i家house獲得最大金錢值
            int[] dp = new int[stores.length];
            //初始化
            dp[0] = stores[0];
            dp[1] = Math.max(dp[0], stores[1]);
            //遍歷順序：根據遞推公式，所以從前往後
            for (int i = 2; i < stores.length; i++) {
                //遞推公式：dp[i]的值為：不偷stores[i] ｜ 偷stores[i]， 取二者最大值
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + stores[i]);
                System.out.printf("i:%s,  dp[i]:%s ", i, dp[i]);
            }
            return dp[stores.length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}