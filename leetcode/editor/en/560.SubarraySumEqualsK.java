import java.util.HashMap;

/**
 * Given an array of integers nums and an integer k, return the total number of
 * subarrays whose sum equals to k.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * <p>
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 2 * 10⁴
 * -1000 <= nums[i] <= 1000
 * -10⁷ <= k <= 10⁷
 * <p>
 * <p>
 * Related Topics Array Hash Table Prefix Sum 👍 21955 👎 682
 */
       
/*
 2024-09-30 22:47:03
*/

class SubarraySumEqualsK {
    public static void main(String[] args) {
        Solution solution = new SubarraySumEqualsK().new Solution();
        int[] nums = {1, 1, 1};
        solution.subarraySum(nums, 2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //prefix sum
        //tc:o(n)
        //参考2sum, 即求newPreSum - oldPreSum = target(即k)

        //注意：初始化
        // 本质上这样就考虑了前缀和本身满足条件的情况。
        //即：当map表示为； key:余数值 value：距离时，需要初始化为 map.put(0, -1);
        //          表示为： key:余数值  value：频率次数时，需要初始化为 map.put(0, 1);
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            int sum = 0;
            //key: prefix sum, value;frequency
            //统计并寻找target = (sum - k) 出现的次数
            HashMap<Integer, Integer> map = new HashMap<>();
            /**
             * 初始化
             * 我們在尋找 sum - k 是否存在於 map 中。
             * 當 sum == k 時，我們實際上在尋找 0 是否存在於 map 中。
             * 通過初始化 map.put(0, 1)，我們確保了這種情況能被正確計算
             *
             *  【xxxxx】 = prefixSum[i]- prefixSum[-1]
             * -1 0 j i
             * 也可理解为prefixSum[-1]=0,即存在一次这样的情况，那麼便可得到这样的subarray
             * 否则不初始化prefixSum[-1]=0 ，map.put(0, 1)，就会丢掉这样的subarray
             * */
            //初始化：记录 record[0]=1，这样就考虑了前缀和本身为k 的情况。
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (map.containsKey(sum - k)) {
                    count += map.get(sum - k);
                }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }

        //注意⚠️：
        /**
         * 這是一個很好的問題。`map.put(0, 1);` 這行代碼確實是這個解法中的一個關鍵點。讓我來解釋為什麼需要這行代碼：
         *
         * ## 為什麼需要 map.put(0, 1)
         *
         * 1. **處理整個數組的和等於 k 的情況**
         *    - 如果整個數組的和恰好等於 k，這種情況也應該被計入。
         *    - 沒有 `map.put(0, 1)`，我們將錯過這種情況。
         *
         * 2. **處理前綴和本身等於 k 的情況**
         *    - 當我們遇到一個前綴和恰好等於 k 的子數組時，我們需要將其計入結果。
         *    - `map.put(0, 1)` 確保了我們可以正確處理這種情況。
         *
         * 3. **數學原理**
         *    - 我們在尋找 `sum - k` 是否存在於 map 中。
         *    - 當 `sum == k` 時，我們實際上在尋找 `0` 是否存在於 map 中。
         *    - 通過初始化 `map.put(0, 1)`，我們確保了這種情況能被正確計算。
         *
         * ## 舉例說明
         *
         * 假設有以下數組：`[1, 2, 3]`，目標 k = 3
         *
         * 1. 當 i = 0 時，sum = 1
         *    - 檢查 map 中是否存在 1 - 3 = -2，不存在
         *    - 將 (1, 1) 加入 map
         *
         * 2. 當 i = 1 時，sum = 3
         *    - 檢查 map 中是否存在 3 - 3 = 0，存在！計數加 1
         *    - 將 (3, 1) 加入 map
         *
         * 3. 當 i = 2 時，sum = 6
         *    - 檢查 map 中是否存在 6 - 3 = 3，存在！計數再加 1
         *    - 將 (6, 1) 加入 map
         *
         * 最終結果為 2，正確計算了 [1, 2] 和  這兩個子數組。
         *
         * 如果沒有 `map.put(0, 1)`，我們將錯過 [1, 2] 這個子數組，因為在 i = 1 時，我們找不到 0 在 map 中。
         *
         * ## 結論
         *
         * `map.put(0, 1)` 這行代碼看似簡單，但實際上處理了很多邊界情況，使得算法能夠正確處理所有可能的子數組和。它是這個前綴和解法的一個重要組成部分。
         * */
    }

    class Solution {
        //prefix sum + sliding window
        //tc:o(n2)
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            //构造前缀和  o(n)
            int[] prefixSum = new int[nums.length + 1];
            prefixSum[0] = 0;
            //前缀和模板2: prefixSum[i]不包含当前元素nums[i]，即i前面的元素的和
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
            //平移窗口 o(n2)
            for (int left = 0; left < nums.length; left++) {
                for (int right = left + 1; right <= nums.length; right++) {
                    if (prefixSum[right] - prefixSum[left] == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

    class Solution3 {
        //prefix sum + sliding window
        //tc:o(n2)
        //与solution使2相比，用不同的模版求，前缀和，注意处理逻辑不一样
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            //构造前缀和  o(n)
            int[] prefixSum = new int[nums.length];
            prefixSum[0] = nums[0];
            //前缀和模板1: prefixSum[i] 包含当前元素nums[i]，的元素的和
            for (int i = 1; i < nums.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }
            //平移窗口 o(n2)
            for (int left = 0; left < nums.length; left++) {
                for (int right = left; right < nums.length; right++) {
                    //当left=0时，直接使用prefixSum[right]与k比较
                    if (left == 0) {
                        if (prefixSum[right] == k) {
                            count++;
                        }
                    } else if (prefixSum[right] - prefixSum[left - 1] == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
    /**
     * prefixSum[i] 表示从数组起始到索引 i 处元素的累加和。因此：
     *
     * prefixSum[right] 表示从 nums[0] 到 nums[right] 的累加和。
     * prefixSum[left] 表示从 nums[0] 到 nums[left] 的累加和。
     * 为了计算区间 [left, right] 之间的子数组和，应该是从 prefixSum[right] 中减去前一个位置的累加和，即 prefixSum[left - 1]。这个过程可以理解为：
     *
     * prefixSum[right] 包含了从 nums[0] 到 nums[right] 的和。
     * prefixSum[left - 1] 包含了从 nums[0] 到 nums[left - 1] 的和。
     * 当我们减去 prefixSum[left - 1] 时，得到的结果就是从 nums[left] 到 nums[right] 的子数组和。
     * 因此：
     *
     * prefixSum[right] - prefixSum[left - 1] 才能正确表示子数组 [left, right] 的和。
     * 而如果用 prefixSum[right] - prefixSum[left]，则表示的是从 nums[left + 1] 到 nums[right] 的和，忽略了 nums[left] 这个元素。
     * 在特殊情况下，当 left == 0 时，没有 left - 1 这个索引，因此需要单独处理：prefixSum[right] 就是从 nums[0] 到 nums[right] 的和。
     *
     * 总结：为了确保子数组 [left, right] 包含 nums[left] 元素，需要使用 prefixSum[right] - prefixSum[left - 1]。
     * */
//leetcode submit region end(Prohibit modification and deletion)

}