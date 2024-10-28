import java.util.Arrays;

/**
 * Given an unsorted array of integers nums, return the length of the longest
 * continuous increasing subsequence (i.e. subarray). The subsequence must be strictly
 * increasing.
 * <p>
 * A continuous increasing subsequence is defined by two indices l and r (l < r)
 * such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l
 * <= i < r, nums[i] < nums[i + 1].
 * <p>
 * Example 1:
 * Input: nums = [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5] with
 * length 3.
 * Even though [1,3,5,7] is an increasing subsequence, it is not continuous as
 * elements 5 and 7 are separated by element
 * 4.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2] with length 1.
 * Note that it must be strictly
 * increasing.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10⁴
 * -10⁹ <= nums[i] <= 10⁹
 * <p>
 * <p>
 * Related Topics Array 👍 2342 👎 181
 */

/*
 2024-07-24 16:34:19
 Longest Continuous Increasing Subsequence
Category	Difficulty	Likes	Dislikes
algorithms	Easy (50.19%)	2342	181
Tags
array

Companies
facebook
*/

class LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestContinuousIncreasingSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //快慢雙指針：nums[fast]滿足條件移動快指針fast++, 更新longestLength,
                   // 否則移動慢指針slow = fast,重新統計 subLength
        public int findLengthOfLCIS(int[] nums) {
            int longestLength = 1;
            int subLength = 1;
            int slow = 0;
            for (int fast = 1; fast < nums.length; fast++) {
                subLength = fast - slow + 1;
                if (nums[fast] > nums[fast - 1]) {
                    longestLength = Math.max(longestLength, subLength);
                } else {
                    slow = fast;
                }
            }
            return longestLength;
        }
    }

    class Solution2 {
        //dp question：
        public int findLengthOfLCIS(int[] nums) {
            //定義dp[i]數組  包含以i結尾的最長連續遞增序列的長度
            int[] dp = new int[nums.length];
            int longestLength = 1;
            //初始化
            Arrays.fill(dp, 1);
            //遞推公式：
            for (int i = 1; i < nums.length; i++) {
                //連續紀錄
                if(nums[i-1]<nums[i]){
                    dp[i] = dp[i-1]+1;
                }
                //注意不滿足遞增條件時，dp[i]的長度會從 2=dp[i-1] +1 = 1+1 重置，
                // 所以此時需要更新當前長度dp[i]與最大長度longestLength，比較求較大值
                longestLength = Math.max(longestLength, dp[i]);
            }
            return longestLength;
        }
    }

    class Solution {
        //greedy question：局部最優：遇到nums[i-1]<nums[i] currentLength++; 否則重置currentLength = 1；同時更新longestLength = Max(currentLength，longestLength), 全局最優：取longestLength
        public int findLengthOfLCIS(int[] nums) {
            int currentLength = 1;
            int longestLength = 1;
            for (int i = 1; i < nums.length; i++) {
                if(nums[i-1]<nums[i]){
                    currentLength++;
                } else {
                    currentLength =1;
                }
                //保持更新longestLength為最大值
                longestLength = Math.max(longestLength, currentLength);
            }
            return longestLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}