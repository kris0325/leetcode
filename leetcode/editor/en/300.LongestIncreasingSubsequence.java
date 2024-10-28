import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly
 * increasing subsequence.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the
 * length is 4.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 2500
 * -10⁴ <= nums[i] <= 10⁴
 * <p>
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time
 * complexity?
 * <p>
 * Related Topics Array Binary Search Dynamic Programming 👍 20854 👎 442
 */
       
/*
 2024-07-24 01:25:53
 Longest Increasing Subsequence
Category	Difficulty	Likes	Dislikes
algorithms	Medium (55.30%)	20854	442
Tags
binary-search | dynamic-programming

Companies
microsoft
*/

class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestIncreasingSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question:
        public int lengthOfLIS(int[] nums) {
            if (nums.length <= 1) return nums.length;
            //1.定義dp數組：dp[i]表示i之前的包括i的以nums[i]結尾的最長遞增子序列
            //为什么一定表示 “以nums[i]结尾的最长递增子序” ，因为我们在 做 递增比较的时候，如果比较 nums[j] 和 nums[i] 的大小，
            // 那么两个递增子序列一定分别以nums[j]为结尾 和 nums[i]为结尾， 要不然这个比较就没有意义了，不是尾部元素的比较,那么如何算递增呢。
            int lengthOfLIS = 0;
            int[] dp = new int[nums.length];
            //3.初始化： 因為單個元素的最長遞增子序列的值為1，所以每個i，對應的最長遞增子序列的初始大小至少為1，
            Arrays.fill(dp, 1);
            //2.遞推公式：位置i的最長遞增子序列等於j從0到i-1各個位置的最長遞增子序列+1的最大值（選擇nums[i] 所以+1）
            //if(nums[i]>nums[j]){dp[i] = Math.max(dp[i], dp[j] +1);}
            //注意这里不是要dp[i] 与 dp[j] + 1进行比较，而是我们要取dp[j] + 1的最大值。
            //4.遍歷順序 從前往後
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                    //取長的子序列
                    lengthOfLIS = Math.max(lengthOfLIS, dp[i]);
                }
            }
            return lengthOfLIS;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}