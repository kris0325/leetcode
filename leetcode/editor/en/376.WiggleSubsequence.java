/**
 * A wiggle sequence is a sequence where the differences between successive
 * numbers strictly alternate between positive and negative. The first difference (if one
 * exists) may be either positive or negative. A sequence with one element and a
 * sequence with two non-equal elements are trivially wiggle sequences.
 * <p>
 * <p>
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6
 * , -3, 5, -7, 3) alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The
 * first is not because its first two differences are positive, and the second is
 * not because its last difference is zero.
 * <p>
 * <p>
 * A subsequence is obtained by deleting some elements (possibly zero) from the
 * original sequence, leaving the remaining elements in their original order.
 * <p>
 * Given an integer array nums, return the length of the longest wiggle
 * subsequence of nums.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5
 * , -7, 3).
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length.
 * One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * <p>
 * <p>
 * <p>
 * Follow up: Could you solve this in O(n) time?
 * <p>
 * Related Topics Array Dynamic Programming Greedy 👍 5107 👎 164
 */

/*
 2024-07-31 16:37:50
*/

class WiggleSubsequence {
    public static void main(String[] args) {
        Solution solution = new WiggleSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //时间复杂度：O(n)
        //空间复杂度：O(1)
//    376. 摆动序列
        public int wiggleMaxLength(int[] nums) {

            if (nums.length == 1) {
                return nums.length;
            }
            //长度至少有一个
            int result = 1;
            // 从第一个元素开始，所以predif = 0初始化为0
            int predif = 0;
            int curdif;
            for (int i = 1; i < nums.length; i++) {
                curdif = nums[i] - nums[i - 1];
                //前後出现2個峰值（前後出現波峰/波谷）
                //等于0的情况表示初始时的preDiff
                if (predif >= 0 && curdif < 0 || predif <= 0 && curdif > 0) {
                    result++;
                    // 注意这里，只在摆动变化的时候更新prediff
                    predif = curdif;
                }
            }
            return result;
        }
    }

    class Solution2 {
        //时间复杂度：O(n2)
        //空间复杂度：O(n)
//    376. 摆动序列
        //dp question：用动态规划的思想來做，
        //容易聯想到，當前數nums[i]可能以作為波峰nums[i]>nums[i-1], 也可能作為波谷nums[i]<nums[i-1]
        public int wiggleMaxLength(int[] nums) {
            //定義dp數組
            // dp[i][0],前i個數，考慮當前數nums[i]可能以作為波峰,的最長擺動序列
            // dp[i][1],前i個數，考慮當前數nums[i]可能以作為波谷,的最長擺動序列
            int[][] dp = new int[nums.length][2];

            //初始化
            dp[0][0] = 1;
            dp[0][1] = 1;
            //遍歷順序 從前往後
            for (int i = 1; i < nums.length; i++) {
                //自己可以成為波峰或者波谷
                dp[i][0] = dp[i][1] = 1;
                for (int j = 0; j < i; j++) {
                    //遞推公式
                    //出現前後波峰/波谷，擺動序列+1
                    //當前為波峰i
                    if (nums[i] > nums[j]) {
                        dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                    }
                    //當前為波谷i
                    if (nums[i] < nums[j]) {
                        dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                    }
                }
            }
            return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}