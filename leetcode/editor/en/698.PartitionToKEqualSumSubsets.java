import java.util.Arrays;

/**
 * Given an integer array nums and an integer k, return true if it is possible to
 * divide this array into k non-empty subsets whose sums are all equal.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3
 * ) with equal sums.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= k <= nums.length <= 16
 * 1 <= nums[i] <= 10⁴
 * The frequency of each element is in the range [1, 4].
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming Backtracking Bit Manipulation
 * Memoization Bitmask 👍 7194 👎 518
 */
       
/*
 2024-10-14 22:26:06
*/

class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        Solution solution = new PartitionToKEqualSumSubsets().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //backtracking
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 不能等分为k个子集
            if (sum % k != 0) return false;
            int target = sum / k;
            //對數組排序，優先從較大值進行組合，方便在回溯的過程中更高效地處理較大值
            //这样，我们可以从较大的数字开始放置，以便更早地发现不可能的组合。
            Arrays.sort(nums);
            //數組元素最大值超過目標和，則無法分割
            if (nums[nums.length - 1] > target) return false;

            //創建一個數組來追蹤每個子集的和
            int[] subsetSums = new int[k];

            //先將較大數嘗試放入子集，所以index從數組末尾開始嘗試
            return backtracking(nums, subsetSums, nums.length - 1, target);

        }

        public boolean backtracking(int[] nums, int[] subsetSums, int index, int target) {
            //所有數字已分配完，檢查subsetSums每個子集是否都滿足目標
            if (index < 0) {
                for (int sum : subsetSums) {
                    if (sum != target) {
                        return false;
                    }
                }
                return true;
            }
            //當前要處理的數字
            int currentNum = nums[index];
            //嘗試將當前數字放入每個子集
            for (int i = 0; i < subsetSums.length; i++) {
                //能放入當前子集
                if (subsetSums[i] + currentNum <= target) {
                    subsetSums[i] += currentNum;
                    //遞歸處理下一個數字
                    if (backtracking(nums, subsetSums, index - 1, target)) {
                        //如果成功，則返回true
                        /**
                         * return true 的含义是在完成所有分割任务后返回成功的信号。
                         * 具体地说，当 backtracking(nums, subsetSums, index - 1, target) 返回 true 时，
                         * 意味着在当前路径上递归地分配了所有的数字，并且达到了将 nums 分成 k 个满足条件的子集的目标。
                         * */
                        return true;
                    }
                    //回溯，從當前子集移除當前數字
                    subsetSums[i] -= currentNum;
                }

                /**
                 * 关键逻辑
                 * 假设我们已经处理了前面的一些数字，现在我们正尝试将当前数字放入一个子集中。如果我们发现某个子集的和为 0，这意味着在该子集中没有任何数字。这种情况下，存在以下逻辑：
                 *
                 * 子集为空的情况：
                 * 当我们尝试把一个数字放入一个子集时，如果当前子集的和为 0，这意味着在之前的过程中，我们没有向这个子集中放入任何数字。
                 * 在这种情况下，无论我们接下来尝试把数字放入这个空的子集中，仍然可能导致这个子集的和为 0。也就是说，假如在一个空子集中放入当前数字后，再尝试后续的数字也会发现，若后续的子集也同样为空，那么它们的组合将无法达到目标和。
                 * 提前退出
                 * 因此，考虑到以上的逻辑：
                 *
                 * 如果一个子集为空，我们就知道它无法有效地接受更多的数字组合来达到目标和。
                 * 由于排序的原因，如果当前子集无法成功，那么后续的子集也很可能会遇到同样的情况。
                 * */

                //如果當前子集為空，後續子集也必然為空，所以沒必要進行繼續嘗試
                if (subsetSums[i] == 0) {
                    break;// 如果当前子集的和为 0，说明后续的子集也不会有有效的组合了
                }
            }
            //沒有找到有效分配
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}