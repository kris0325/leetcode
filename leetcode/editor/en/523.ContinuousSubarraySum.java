import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if nums has a good
 * subarray or false otherwise.
 * <p>
 * A good subarray is a subarray where:
 * <p>
 * <p>
 * its length is at least two, and
 * the sum of the elements of the subarray is a multiple of k.
 * <p>
 * <p>
 * Note that:
 * <p>
 * <p>
 * A subarray is a contiguous part of the array.
 * An integer x is a multiple of k if there exists an integer n such that x = n *
 * k. 0 is always a multiple of k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to
 * 6.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose
 * elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * 0 <= nums[i] <= 10⁹
 * 0 <= sum(nums[i]) <= 2³¹ - 1
 * 1 <= k <= 2³¹ - 1
 * <p>
 * <p>
 * Related Topics Array Hash Table Math Prefix Sum 👍 6331 👎 663
 */
       
/*
 2024-10-02 15:48:57
*/

class ContinuousSubarraySum {
    public static void main(String[] args) {
        Solution solution = new ContinuousSubarraySum().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //prfixsum + 2sum
        public boolean checkSubarraySum(int[] nums, int k) {
            //key:mod, value:index
            Map<Integer, Integer> map = new HashMap<>();
            //初始化：由于空的前缀的元素和为 0，因此index为-1，余数为0，因此在哈希表中存入键值对 (0,−1)
            //本质上这样就考虑了前缀和本身被 k 整除的情况。
            //注意：当map表示为； key:余数值 value：距离时，需要初始化为 map.put(0, -1);
           //          表示为： key:余数值  value：频率次数时，需要初始化为 map.put(0, 1);
            map.put(0, -1);
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                int mod = sum % k;
                if (map.containsKey(mod)) {
                    if (i - map.get(mod) > 1) {
                        return true;
                    }
                } else {
                    map.put(mod, i);
                }
            }
            return false;
        }
    }
    /**
     *  ## 為什麼 `map.put(0, -1)` 很重要？
     *
     * 1. **處理整個數組和被 k 整除的情況**:
     *    - 如果從索引 0 到某個位置 i 的和恰好被 k 整除，`mod` 將等於 0。
     *    - 此時，`map.containsKey(mod)` 為真，我們可以檢查子數組長度。
     *
     * 2. **正確計算子數組長度**:
     *    - 當找到相同的餘數時，子數組的長度計算為 `i - map.get(mod)`。
     *    - 通過將 0 的索引設為 -1，確保了即使和被 k 整除的子數組從索引 0 開始，長度計算也是正確的。
     *
     * 3. **避免漏掉從開始位置的子數組**:
     *    - 如果不加這行，可能會錯過從數組開始就符合條件的子數組。
     *
     * 總之，`map.put(0, -1);` 這行代碼是這個解法的一個巧妙之處，它確保了算法能夠正確處理所有可能的情況，包括從數組開始的子數組。
     * */
//leetcode submit region end(Prohibit modification and deletion)

}