import java.util.Arrays;

/**
 * Given an integer array nums and an integer k, split nums into k non-empty
 * subarrays such that the largest sum of any subarray is minimized.
 * <p>
 * Return the minimized largest sum of the split.
 * <p>
 * A subarray is a contiguous part of the array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum
 * among the two subarrays is only 18.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among
 * the two subarrays is only 9.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10⁶
 * 1 <= k <= min(50, nums.length)
 * <p>
 * <p>
 * Related Topics Array Binary Search Dynamic Programming Greedy Prefix Sum 👍 994
 * 0 👎 227
 */
       
/*
 2024-09-14 16:20:21
*/

class SplitArrayLargestSum {
    public static void main(String[] args) {
        Solution solution = new SplitArrayLargestSum().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //二分搜索+貪心算法
        public int splitArray(int[] nums, int k) {
            int left = Arrays.stream(nums).max().getAsInt();
            int right = Arrays.stream(nums).sum();
            while (left < right) {
                //mid為所求最小的最大子數組和的 試探值
                int mid = left + (right - left) / 2;
                if (validHelper(nums, mid, k)) {
                    //貪心算法
                    //validHelper切割有效，則不斷更新右邊界，在左邊搜素，即讓試探值mid更小，再次搜索
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            //left=right 跳出while循環, 則找到切割方案，且使得mid最小
            return left;
        }

        public boolean validHelper(int[] nums, int mid, int k) {
            int sum = 0;
            //切割後子數組的個數，切割0次，子數組為自己，所以count初始化值為1
            int count = 1;
            for (int i = 0; i < nums.length; i++) {
                //考慮sum+nums[i] > mid與試探值 mid比較，判斷是否需要切割，即無須切個，將nums[i]繼續到當前子數組，或者切割，並將num[i]切入到後面子數組，
                //1.sum+nums[i] > mid，則切一次，且nums[i]到後面的子數組
                if (sum + nums[i] > mid) {
                    //sum+nums[i]大於次輪的試探值mid,則需要開始切割，將nums[i]到後面的子數組
                    count++;
                    //nums[i]作為新子數組的第一個元素，繼續for loop
                    sum = nums[i];
                } else {
                    //2.sum+nums[i] <= mid, 則num[i]無須切，繼續加到當前子數組sum中
                    sum += nums[i];
                }
            }
            //for loop走完後，即切割n次後，判斷子數組個數是否count<=m
            //true，則可以繼續通過二分法 減小試探值mid，繼續進行validHelper搜索切割方案
            //false，同理，則需要繼續通過二分法 加大試探值mid繼續進行validHelper搜索切割方案
            return count <= k;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}