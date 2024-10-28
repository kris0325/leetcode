import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of good
 * subarrays of nums.
 * <p>
 * A good array is an array where the number of different integers in that array
 * is exactly k.
 * <p>
 * <p>
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * <p>
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [
 * 1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1
 * ,3], [1,3,4].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 2 * 10⁴
 * 1 <= nums[i], k <= nums.length
 * <p>
 * <p>
 * Related Topics Array Hash Table Sliding Window Counting 👍 6053 👎 97
 */
       
/*
 2024-08-09 23:38:03
*/



class SubarraysWithKDifferentIntegers {
    public static void main(String[] args) {
        Solution solution = new SubarraysWithKDifferentIntegers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //sliding window
        //直接求exact (k)比較複雜，
        //exact (k) 可轉化為exact (k) = most (k) - most (k-1)
        //tc:o(n)

        public int subarraysWithKDistinct(int[] nums, int k) {
            return mostKSubarraysWithKDistinct(nums, k) - mostKSubarraysWithKDistinct(nums, k - 1);
        }

        public int mostKSubarraysWithKDistinct(int[] nums, int k) {
            //map標記當前滑動窗口map<元素，次數>
            Map<Integer, Integer> map = new HashMap<>();
            int left = 0;
            int result = 0;
            //有效的元素數，即不重複元素數
            int distinctCharCount = 0;
            for (int i = 0; i < nums.length; i++) {
                //1.進
                if (map.getOrDefault(nums[i], 0) == 0) {
                    distinctCharCount++;
                }
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
                //2.出 shrink
                while (distinctCharCount > k) {
                    map.put(nums[left], map.get(nums[left]) - 1);
                    if (map.get(nums[left]) == 0) {
                        distinctCharCount--;
                        map.remove(nums[left]);
                    }
                    left++;
                }
                //3.計算：當前窗口 滿足條件：包含k個不同元素
                //most (k)： 包含k,k-1...2,1,0個
                //求最多k個元素子數組，即有k,k-1,k-2...2,1，0個元素
                //                //為避免重複計算，考慮所有子數組都以右邊界i結尾，
                //                // 那麼從left到i，每次移動一步可以形成一個新子數組，可以移動i-left步，再包含初始位置left，所以再+1，
                //                // 所有總共子數組為i-left+1
                //滑動窗口 extend迭代過程中 i右移動，所以累加，即為mostK(k)個子數組
                result += i - left + 1;
            }
            return result;
        }
    }

    class Solution {
        //exactly(K) = atMost(K) - atMost(K-1)
        public int subarraysWithKDistinct(int[] nums, int K) {
            return atMostK(nums, K) - atMostK(nums, K - 1);
        }

        private int atMostK(int[] A, int K) {
            int left = 0, res = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < A.length; i++) {
                //发一个新的不在window里面的数字，还需要不同的k数字-1
                if (map.getOrDefault(A[i], 0) == 0) K--;
                map.put(A[i], map.getOrDefault(A[i], 0) + 1);
                while (K < 0) {
                    map.put(A[left], map.get(A[left]) - 1);
                    if (map.get(A[left]) == 0) K++;
                    left++;
                }
                res += i - left + 1;//有k, k-1, k-2...3, 2, 1 个unique integers
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}