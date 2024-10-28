/**
 * You are given an integer array nums. You are initially positioned at the
 * array's first index, and each element in the array represents your maximum jump length
 * at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump
 * length is 0, which makes it impossible to reach the last index.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁴
 * 0 <= nums[i] <= 10⁵
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Greedy 👍 19349 👎 1242
 */
       
/*
 2024-07-18 23:49:18

Jump Game
Category	Difficulty	Likes	Dislikes
algorithms	Medium (38.50%)	19349	1242
Tags
array | greedy

Companies
microsoft


*/

class JumpGame {
    public static void main(String[] args) {
        Solution solution = new JumpGame().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //greedy question: 貪心算法局部最優解：每次跳最遠距離（獲得局部最大覆蓋範圍），
                        // 全局最優解：最後得到整體最大覆蓋範圍，再判斷能否到達終點
        // i每次移動一個單位,每次跳最大距離nums[i]，即取局部最大覆蓋範圍即currentCover = i+ nums[i] ,
        // 同時更新全局的最大覆蓋範圍 maxCover = MAX(i+nums[i], maxCover)
        //i每次移動只能在maxCover範圍（因為更新後的maxCover是i能移動的範圍）
        //若遇到當前最大覆蓋範圍maxCover 大於等於最後一個元素的index時，表示就能跳到 return true， 否則false
        public boolean canJump(int[] nums) {
            int maxCover = 0;
            //注意i <= maxCove，是因為動態更新後的maxCover是i走到的最遠距離
            for (int i = 0; i <= maxCover; i++) {
                maxCover = Math.max(i + nums[i], maxCover);
                if (maxCover >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}