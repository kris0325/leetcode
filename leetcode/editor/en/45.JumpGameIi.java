/**
 * You are given a 0-indexed array of integers nums of length n. You are initially
 * positioned at nums[0].
 * <p>
 * Each element nums[i] represents the maximum length of a forward jump from
 * index i. In other words, if you are at nums[i], you can jump to any nums[i + j]
 * where:
 * 0 <= j <= nums[i] and
 * i + j < n
 * <p>
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are
 * generated such that you can reach nums[n - 1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1
 * step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10⁴
 * 0 <= nums[i] <= 1000
 * It's guaranteed that you can reach nums[n - 1].
 * <p>
 * Related Topics Array Dynamic Programming Greedy 👍 14561 👎 577
 */
       
/*
 2024-07-19 16:22:06
 Jump Game II
Category	Difficulty	Likes	Dislikes
algorithms	Medium (40.44%)	14561	577
Tags
array | greedy

Companies
Unknown
*/

class JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new JumpGameIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //greedy question:  局部最優解：每個位置考慮跳最遠距離，花費最小步數，如果最遠距離大於最大覆蓋範圍，則更新得到新的最大覆蓋範圍 nextCover；
        // 全局最優解：在[0,maxCover]範圍內移動i, 每次局部都取最優解（跳最遠距離，花費最小步數）, 注意：需要記住當前i是否走到了currentCover，只有當i走到時，minSteps才需要累加1,並更新currentCover
        // 那麼變得到全局最優解（花費最小步數）實現到達數組末尾
        public int jump(int[] nums) {
            if(nums.length==1) {return 0;}
            int minSteps = 0;
            int nextCover = 0;
            //紀錄當前i能走到的最大覆蓋範圍
            int currentCover = 0;
            for (int i = 0; i < nums.length; i++) {
                //更新下一步能到達的最遠覆蓋面積
                nextCover = Math.max(nextCover, i + nums[i]);
                //當前i走到了當前能走的最遠距離currentCover，只有走到時 minSteps才累加1
                if (i == currentCover) {
                    //需要走下一步
                    minSteps++;
                    //更新當前能走的最遠距離currentCover
                    currentCover = nextCover;
                    //nextCover能首次覆蓋到數組末尾時，即得到最小步數
                    if (nextCover >= nums.length - 1) {
                        break;
                    }
                }
            }
            return minSteps;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}