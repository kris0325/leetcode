/**
 * Given an array of integers nums and an integer k. A continuous subarray is
 * called nice if there are k odd numbers on it.
 * <p>
 * Return the number of nice sub-arrays.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There are no odd numbers in the array.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * <p>
 * <p>
 * Related Topics Array Hash Table Math Sliding Window 👍 4570 👎 116
 */
       
/*
 2024-08-09 00:31:44
*/

class CountNumberOfNiceSubarrays {
    public static void main(String[] args) {
        Solution solution = new CountNumberOfNiceSubarrays().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //sliding window
        //直接求exact (k)比較複雜，
        //exact (k) 可轉化為exact (k) = most (k) - most (k-1)
        public int numberOfSubarrays(int[] nums, int k) {
            ////exact 包含k個奇數 = 最多包含k個奇數 - 最多包含k-1個奇數
            return mostKOfnumberOfSubarrays(nums, k) - mostKOfnumberOfSubarrays(nums, k - 1);
        }

        //求most (k)  最多包含k個奇數
        public int mostKOfnumberOfSubarrays(int[] nums, int k) {
            int left = 0;
            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                //step1.進：入窗口奇數-1;偶數-0（不影響）
                k -= nums[i] % 2;
                //step2.出：   當放入窗口的奇數超過k個，需要shrink left,減少窗口內的奇數
                while (k < 0) {
                    //奇數+1;偶數+0（不影響）
                    k += nums[left] % 2;
                    left++;
                }
                // k == 0,窗口中刚好有k個元素時，
                //求最多k個元素子數組，即有k,k-1,k-2...2,1，0個元素
                //                //為避免重複計算，考慮所有子數組都以右邊界i結尾，
                //                // 那麼從left到i，每次移動一步可以形成一個新子數組，可以移動i-left步，再包含left，再+1，
                //                // 所有總共子數組為i-left+1
                //滑動窗口 extend迭代過程中 i右移動，所以累加，即為mostK(k)個子數組
                //step3.計算：滑動窗口中包含k個odd元素，開始統計當前滑動窗口的mostK(k)子數組，
                // 為避免重複計算，可以固定子數組右邊界，即以i為右邊界來計算所有子數組，
                result += i - left + 1;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}