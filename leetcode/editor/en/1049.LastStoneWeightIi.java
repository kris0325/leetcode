/**
 * You are given an array of integers stones where stones[i] is the weight of the
 * iᵗʰ stone.
 * <p>
 * We are playing a game with the stones. On each turn, we choose any two stones
 * and smash them together. Suppose the stones have weights x and y with x <= y.
 * The result of this smash is:
 * <p>
 * <p>
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has
 * new weight y - x.
 * <p>
 * <p>
 * At the end of the game, there is at most one stone left.
 * <p>
 * Return the smallest possible weight of the left stone. If there are no stones
 * left, return 0.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0, so the array converts to [1], then that's the
 * optimal value.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: stones = [31,26,33,21,40]
 * Output: 5
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming 👍 3100 👎 118
 */
       
/*
 2024-07-09 20:34:47

 Last Stone Weight II
Category	Difficulty	Likes	Dislikes
algorithms	Medium (55.26%)	3100	118
Tags
array | greedy

Companies
Unknown
*/

class LastStoneWeightIi {
    public static void main(String[] args) {
        Solution solution = new LastStoneWeightIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp: 与416.分割等和子集同类型题目，同属于01背包
        //思路：尽可能分成2组等重量石头，相同石头重量会撞碎，剩下部分即为最后的重量
        public int lastStoneWeightII(int[] stones) {
            int sum = 0;
            for (int i = 0; i < stones.length; i++) {
                sum += stones[i];
            }
            int target = sum / 2;
            //dp[i]容量为i的背包能装下最大价值的物品（石头），物品重量为stone[i],物品价值value[i]也为stone[i]
            int[] dp = new int[target + 1];
            //遍历物品
            for (int i = 0; i < stones.length; i++) {
                //遍历背包
                for (int j = target; j >= stones[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                }
            }
            //dp[target]即为容量为target的背包能装下的最大价值的石头
            //将石头分成2堆，一堆石头的总重量是dp[target]，另一堆就是sum - dp[target]
            //因为在计算target时，target = sum / 2向下取整，所以sum - dp[target] 一定大于等于dp[target]
            //那么相撞后，剩下最小石头的重量就是(sum -dp[target]) - dp[target]
            return (sum - dp[target]) - dp[target];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}