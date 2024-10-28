/**
 * You are given an integer array nums and an integer target.
 * <p>
 * You want to build an expression out of nums by adding one of the symbols '+'
 * and '-' before each integer in nums and then concatenate all the integers.
 * <p>
 * <p>
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1
 * and concatenate them to build the expression "+2-1".
 * <p>
 * <p>
 * Return the number of different expressions that you can build, which evaluates
 * to target.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be
 * target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1], target = 1
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Backtracking 👍 10847 👎 359
 */

/*
 2024-07-11 11:48:49

 Target Sum
Category	Difficulty	Likes	Dislikes
algorithms	Medium (46.76%)	10847	359
Tags
dynamic-programming | depth-first-search

Companies
facebook | google
*/

class TargetSum {
    public static void main(String[] args) {
        Solution solution = new TargetSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp: 01背包問題
        //如何轉為01背包問題？
        //可以假設將nums[i] 拆分為2個數組，即添加+的數組add， 和添加-的數組minus， 加法總和為add, 減法總和為minus 那麼可得到公式：
        // add + minus = sum (sum為nums元素總和)
        // add - minus = target
        //那麼可以推導出 add = (sum + target)/2 , 就轉化為問題：求拆分一個數組add滿足add = (sum + target)/2的方案右多少種
        // 便可轉化為求裝滿容量為add的01背包，有多少種方法 dp[j], j為背包容量
        //注意：由於計算add 為向下取整，那麼 (sum + target) %2 ==1，此時沒有方案
        //那麼遞推數組 dp[j] += dp[j - nums[i]], 注意和求背包裝最大價值物品問題不一樣，這裡是求多少種方案，所以是累加
        //初始化為dp[0] =1 , ( 因為如果dp[0] = 0，那麼dp[j]都會為0)

        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            //無法分配，沒有方案
            if ((sum + target) % 2 == 1) {
                return 0;
            }
            //無法分配，沒有方案
            if (Math.abs(target) > sum) {
                return 0;
            }
            int addBagSize = (sum + target) / 2;
            int[] dp = new int[addBagSize + 1];
            //如果初始化dp[0] = 0 會發現dp[j]全為0
            dp[0] = 1;
            for (int i = 0; i < nums.length; i++) {
                for (int j = addBagSize; j >= nums[i]; j--) {
                    //dp[j - nums[i]] 表示 容量為j的背包，去掉重量nums[i]的物品，
                    dp[j] += dp[j - nums[i]];
                }
            }
            return dp[addBagSize];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}