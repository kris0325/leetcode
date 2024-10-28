import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given an integer array nums and an integer k, modify the array in the following
 * way:
 * <p>
 * <p>
 * choose an index i and replace nums[i] with -nums[i].
 * <p>
 * <p>
 * You should apply this process exactly k times. You may choose the same index i
 * multiple times.
 * <p>
 * Return the largest possible sum of the array after modifying it in this way.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [4,2,3], k = 1
 * Output: 5
 * Explanation: Choose index 1 and nums becomes [4,-2,3].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [3,-1,0,2], k = 3
 * Output: 6
 * Explanation: Choose indices (1, 2, 2) and nums becomes [3,1,0,2].
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [2,-3,-1,5,-4], k = 2
 * Output: 13
 * Explanation: Choose indices (1, 4) and nums becomes [2,3,-1,5,4].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁴
 * -100 <= nums[i] <= 100
 * 1 <= k <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Greedy Sorting 👍 1547 👎 114
 */
       
/*
 2024-07-19 17:41:45
1005.K次取反后最大化的数组和
 Maximize Sum Of Array After K Negations
Category	Difficulty	Likes	Dislikes
algorithms	Easy (51.05%)	1547	114
Tags
tree

Companies
Unknown
*/

class MaximizeSumOfArrayAfterKNegations {
    public static void main(String[] args) {
        Solution solution = new MaximizeSumOfArrayAfterKNegations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //greedy question： 局部最優：先反轉絕對值最大負數，那麼當前值達到最大；全局最優：整個數組和達到最大
        //注意：如果全部負數都變為正數後，k次數依然大於0，那麼問題變成：如何處理一個有序正整數數組，k次反轉後，得到最大值，
        //此時，又是一個貪心問題：局部最優：選最小數值的正數反轉，當前值達到最大；全局最優：整個數組和達到最大
        public int largestSumAfterKNegations(int[] nums, int k) {
            //第一步按絕對值對數組排序 倒序
//            nums = IntStream.of(nums)
//                    .boxed()
//                    .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
//                    .mapToInt(Integer::intValue).toArray();
            //防止整數減法溢出，可以使用 Integer.compare 方法，该方法不会溢出，因为它使用的是逻辑比较而不是算术比较。
            nums = IntStream.of(nums)
                    .boxed()
                    .sorted((o1, o2) -> Integer.compare(Math.abs(o2), Math.abs(o1)))
                    .mapToInt(Integer::intValue).toArray();


            for (int i = 0; i < nums.length; i++) {
                //第二部：將負數反轉為正數
                if (nums[i] < 0 && k > 0) {
                    nums[i] = -nums[i];
                    k--;
                }
            }
            //第三步：如果反轉次數k還沒用完，那麼取最小數值的正數進行反覆k次反轉
            if (k % 2 == 1) {
                //k %2 ==0 相當於k次反轉後還是原來數值，所以無須處理，k %2 ==1，奇數只需反轉一次即可
                nums[nums.length - 1] = -nums[nums.length - 1];
            }
            //求和
            return Arrays.stream(nums).sum();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}