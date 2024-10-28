import java.util.Map;

/**
 * Given a binary array nums, return the maximum length of a contiguous subarray
 * with an equal number of 0 and 1.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0
 * and 1.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal
 * number of 0 and 1.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * nums[i] is either 0 or 1.
 * <p>
 * <p>
 * Related Topics Array Hash Table Prefix Sum 👍 8043 👎 391
 */
       
/*
 2024-10-03 14:37:57
*/

class ContiguousArray {
    public static void main(String[] args) {
        Solution solution = new ContiguousArray().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //prefix sum
        public int findMaxLength(int[] nums) {
            int maxLen = 0;
            int prefixSum = 0;
            //key:prefixSum, value : index
            Map<Integer, Integer> map = new HashMap<>();
            //初始化，考慮當前前綴和滿足條件
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                // 為方便統計，將0全部翻轉為-1，因為1+（-1）=0，那麼前綴和為0可表示0，1數目相等
                //key可能值為： 1,-1,0， 參考two sum 方法，找到相同前綴和，之前差夠成的子數組即滿足條件
                if (nums[i] == 0) {
                    nums[i] = -1;
                }
                prefixSum += nums[i];
                if (map.containsKey(prefixSum)) {
                    maxLen = Math.max(maxLen, i - map.get(prefixSum));
                } else {
                    map.put(prefixSum, i);
                }
            }
            return maxLen;
        }

        class SolutionRangeAddtion{

            /**
             * 差分数组的定义： 差分数组 diff[i] 表示原数组中 nums[i] 与 nums[i-1] 的差值，即 diff[i] = nums[i] - nums[i-1]。
             * 通过差分数组，原数组中的每个元素可以被递推计算：nums[i] = nums[i-1] + diff[i]。
             * */

            /**
             * 差分数组是解决该问题的关键思想。其核心思想是通过记录区间起点和终点的增减变化，
             * 进而高效地完成区间范围内的批量更新。
             *
             * 解法：
             * 1.差分数组的定义： 差分数组 diff[i] 表示原数组中 nums[i] 与 nums[i-1] 的差值，
             * 即 diff[i] = nums[i] - nums[i-1]。通过差分数组，原数组中的每个元素可以被递推计算：nums[i] = nums[i-1] + diff[i]。
             *
             * 2.更新操作： 对于每个更新操作 (i, j, k)，意味着将区间 [i, j] 中的所有元素增加 k。
             * 我们可以通过以下操作在差分数组中体现这一更新：
             *
             * diff[i] += k 表示从 i 开始的元素都需要增加 k。
             * diff[j+1] -= k 表示从 j+1 开始的元素恢复为原值，因此在 j+1 处减去 k,以便在后续累加时抵消。
             * 3.重建数组： 当所有的更新操作处理完后，我们通过遍历差分数组，
             * 递推地计算原数组的每个元素，最终得到修改后的数组。
             * **/

            //时间复杂度为 O(n + m)，其中 n 是数组长度，m 是更新操作的次数。
            //这种方法特别适合处理大量的区间更新操作，能够显著提高效率。
            public int[] getModifiedArray(int length, int[][]updates) {
                //差分数组：存储差分结果
                int[] res = new int[length];
                for (int[] update : updates){
                    int start = update[0];
                    int end = update[1];
                    int val = update[2];
                    res[start] += val;
                    if(end+1 < length){
                        // 如果结束位置的下一个索引在数组范围内，则在该位置减少 val，以便在后续累加时抵消
                        res[end+1] -=val;
                    }
                }
                //利用差分数组构建更新后的数组,
                // 或理解为 通过求差分数组前缀和 来构建 更新后的数组
                //reconstructing the result
                //After processing all the updates in the updates array,
                // the code iterates through the diff array to reconstruct the updated nums array.
                for (int i = 1; i< length;i++ ){
                    res[i] += res[i-1];
                }
                return res;
            }
        }




    }
//leetcode submit region end(Prohibit modification and deletion)

}