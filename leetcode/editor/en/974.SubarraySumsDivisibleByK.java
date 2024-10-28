import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of non-empty
 * subarrays that have a sum divisible by k.
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [5], k = 9
 * Output: 0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 3 * 10⁴
 * -10⁴ <= nums[i] <= 10⁴
 * 2 <= k <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Hash Table Prefix Sum 👍 7258 👎 320
 */
       
/*
 2024-10-01 21:38:47
*/

class SubarraySumsDivisibleByK {
    public static void main(String[] args) {
        Solution solution = new SubarraySumsDivisibleByK().new Solution();
        solution.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //prefix sum + 同余定理

        /**
         * 每个连续子数组的和 sum(i,j) 就可以写成 P[j]−P[i−1]（其中 0<i<j）的形式。
         * 此时，判断子数组的和能否被 k 整除就等价于判断 (P[j]−P[i−1])modk==0，
         * 根据 同余定理，只要 P[j]modk==P[i−1]modk,  分配定律 =>(P[j]−P[i−1])modk==0，就可以保证上面的等式成立。
         * 所以转化为 只需出现i,j 使得p[i], p[j]都能整除k
         */
        public int subarraysDivByK(int[] nums, int k) {
            int prefixSum = 0;
            int count = 0;
            //key: mod, value:frequency
            Map<Integer, Integer> map = new HashMap<>();
            //初始化，存在一种case，prefixSum[-1] = 0，即index=-1的前缀和为0，
            // 这样才可以求当个元素与整个数组为子数组的情况
            /**
             *              *  【xxxxx】 = prefixSum[i]- prefixSum[-1]
             *              * -1 0 j i
             *              上面【0 ,i】为满足条件的subarray
             *              * 可理解为只有初始化 prefixSum[-1]=0,即存在一次这样的情况，便可得到这样的subarray,
             *              * 否则不初始化prefixSum[-1]=0 ，map.put(0, 1)，就会丢掉这样的subarray
             * */
            //初始化：记录 record[0]=1，这样就考虑了前缀和本身被 k 整除的情况。
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];
                //edge case：處理負數, 在Java中当prefixSum为负数时，取模结果为负数，(prefixSum % k + k) % k，可以確保所有餘數都落在0到k-1的範圍內,
               /**
                * 在不同编程语言中，负数取模的处理方式有所不同。以Java为例：
                *
                * 在Java中，如果被除数是负数，sum % k的结果也可能是负数。例如，-1 % 3 的结果是 -1，而不是我们希望的 2。
                *
                * 数学上-1，2 对3取模都是等价的，都为2，因为（-1+n*3）%3 = 2,
                * 如果不处理负数的，
                * -1 % 3  ： -1
                * 2 % 3  ：2
                *这样 -1，2分别对3 取模会得到错误的结果
                * */
                int modNum = (prefixSum % k + k) % k;
                /**
                 * 同餘定理
                 * 關鍵在於理解同餘的概念。如果兩個數除以 k 的餘數相同，那麼這兩個數的差就能被 k 整除。
                 * 所以：，我們不僅要統計餘數為 0 的情況，還要統計所有出現過的餘數。每次遇到相同的餘數，就意味著找到了一個新的可被 k 整除的子數組。
                 * */
                int frequency = map.getOrDefault(modNum, 0);
                count += frequency;
                map.put(modNum, frequency + 1);
            }
            return count;
        }
    }

    class Solution2 {
        //prefix sum
        //tc:o(n2) Time Limit Exceeded
        public int subarraysDivByK(int[] nums, int k) {
            int count = 0;
            int[] prefixSum = new int[nums.length + 1];
            prefixSum[0] = 0;
            //前綴和prefixSum[i]，是不包含nums[i]的，即下標index【0，i-1】
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            //移动窗口，计算符subArray
            for (int left = 0; left < nums.length; left++) {
                for (int right = left + 1; right <= nums.length; right++) {
                    if ((prefixSum[right] - prefixSum[left]) % k == 0) {
                        count++;
                    }
                }
            }
            return count;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}