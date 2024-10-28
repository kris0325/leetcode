import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of distinct integers nums and a target integer target, return
 * the number of possible combinations that add up to target.
 * <p>
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [9], target = 3
 * Output: 0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * All the elements of nums are unique.
 * 1 <= target <= 1000
 * <p>
 * <p>
 * <p>
 * Follow up: What if negative numbers are allowed in the given array? How does
 * it change the problem? What limitation we need to add to the question to allow
 * negative numbers?
 * <p>
 * Related Topics Array Dynamic Programming 👍 7380 👎 663
 */
       
/*
 2024-07-18 16:05:24

 Combination Sum IV
Category	Difficulty	Likes	Dislikes
algorithms	Medium (54.14%)	7380	663
Tags
dynamic-programming

Companies
facebook | google | snapchat
*/

class CombinationSumIv {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIv().new Solution();
        System.out.println(solution.combinationSum4(new int[]{1, 2, 3, 4}, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // dp问题：完全01背包问题，有顺序那么求的是排列的总数
        public int combinationSum4(int[] nums, int target) {
            // 定义dp[j]数组，表示装满容量为j的背包有多少种方法
            int[] dp = new int[target + 1];
            List<List<List<Integer>>> combinations = new ArrayList<>();
            for (int i = 0; i <= target; i++) {
                combinations.add(new ArrayList<>());
            }

            // 初始化
            dp[0] = 1;
            combinations.get(0).add(new ArrayList<>());

            // 遍历顺序 排列问题（本題要求區分nums[i]元素的排列順序） 所以需要先遍历背包 后遍历物品
            for (int j = 1; j <= target; j++) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] <= j) {
                        dp[j] += dp[j - nums[i]];
                        for (List<Integer> comb : combinations.get(j - nums[i])) {
                            List<Integer> newComb = new ArrayList<>(comb);
                            newComb.add(nums[i]);
                            combinations.get(j).add(newComb);
                        }
                    }
                }
            }

            // 遍历顺序 組合问题（如果本題不區分nums[i]元素的排列順序） 那麼需要先遍历物品 后遍历背包
//            for (int i = 0; i < nums.length; i++) {
//                for (int j = 1; j <= target; j++) {
//                    if (nums[i] <= j) {
//                        dp[j] += dp[j - nums[i]];
//                        for (List<Integer> comb : combinations.get(j - nums[i])) {
//                            List<Integer> newComb = new ArrayList<>(comb);
//                            newComb.add(nums[i]);
//                            combinations.get(j).add(newComb);
//                        }
//                    }
//                }
//            }

            // 打印所有组合
            System.out.println("The possible combination ways are:");
            for (List<Integer> comb : combinations.get(target)) {
                System.out.println(comb);
            }

            return dp[target];
        }
    }

    class Solution2 {
        //dp問題：完全01背包問題，有順序那麼求的是排列的總數
        public int combinationSum4(int[] nums, int target) {
            //定義dp[j]數組，表示裝滿容量為j的背包有多少中方法
            int[] dp = new int[target + 1];
            //初始化
            dp[0] = 1;
            //遍歷順序 排列問題 所以需要先遍歷背包 後遍歷物品
            //先遍歷背包： 可重複使用nums[i],是完全背包，所以從前往後遍歷
            for (int j = 1; j <= target; j++) {
                //後遍歷物品
                for (int i = 0; i < nums.length; i++) {
                    //物品重量小於等於背包重量時，才可放入背包，此時執行遞推公式才有意義
                    if (nums[i] <= j) {
                        //遞推公式：dp[j]考慮物品nums[i] 可以由不考慮nums[i] dp[j - nums[i]]推導出來, 求所有裝滿背包的所有方法，則累加
                        dp[j] += dp[j - nums[i]];
                        System.out.printf("容量j:%d, 考慮物品i:%d，dp[j]:%d, nums[i]:%d\n", j, i, dp[j], nums[i]);
                    }
                }
            }
            return dp[target];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}