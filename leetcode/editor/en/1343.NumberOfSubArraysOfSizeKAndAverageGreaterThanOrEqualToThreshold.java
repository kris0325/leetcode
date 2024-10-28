import java.util.List;

/**
 * Given an array of integers arr and two integers k and threshold, return the
 * number of sub-arrays of size k and average greater than or equal to threshold.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * Output: 3
 * Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6
 * respectively. All other sub-arrays of size 3 have averages less than 4 (the
 * threshold).
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * Output: 6
 * Explanation: The first 6 sub-arrays of size 3 have averages greater than 5.
 * Note that averages are not integers.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= arr.length <= 10⁵
 * 1 <= arr[i] <= 10⁴
 * 1 <= k <= arr.length
 * 0 <= threshold <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Sliding Window 👍 1609 👎 106
 */
       
/*
 2024-09-04 15:09:19
*/

class NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {
    public static void main(String[] args) {
        Solution solution = new NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold().new Solution();
        int[] arr = {2, 2, 2, 2, 5, 5, 5, 8};
        System.out.printf("numOfSubarrays:" + solution.numOfSubarrays(arr, 3, 4));
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
        //sliding window
        public int numOfSubarrays(int[] arr, int k, int threshold) {
            int sum = 0;
            int num = 0;
            int target = threshold*k;
            //初始化size为k的窗口
            for (int i = 0; i < k; i++) {
                sum += arr[i];
            }
            if (sum >= target) {
                num++;
            }
            //滑动窗口每次向右移动一步，增加右边元素，减去左边元素
            for (int fast = k; fast < arr.length; fast++) {
                sum+=arr[fast] - arr[fast-k];
                if(sum >= target){
                    num++;
                }
            }
            return num;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}