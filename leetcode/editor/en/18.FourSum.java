import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, return an array of all the unique
 * quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * <p>
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * <p>
 * <p>
 * You may return the answer in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 200
 * -10⁹ <= nums[i] <= 10⁹
 * -10⁹ <= target <= 10⁹
 * <p>
 * <p>
 * Related Topics Array Two Pointers Sorting 👍 11562 👎 1419
 */
       
/*
 2024-10-28 15:52:01
*/

class FourSum {
    public static void main(String[] args) {
        Solution solution = new FourSum().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1.3sum的基础上再多加一层循環
         * 2.注意剪枝
         *
         * @param nums
         * @param target
         * @return
         */
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                //剪枝
                if (nums[i] > target && nums[i] > 0) {
                    break;
                }
                //nums[i]去重複
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                for (int j = i + 1; j < nums.length; j++) {
                    //剪枝
                    if (nums[j] >= 0 && nums[i] + nums[j] > target) {
                        break;
                    }
                    // nums[j]去重複
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    int left = j + 1, right = nums.length - 1;
                    while (left < right) {
                        int sum = nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum == target) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            left++;
                            right--;
                            //nums[left]，nums[right] 去重複
                            while (left < right && nums[left] == nums[left -1]) left++;
                            while (left < right && nums[right] == nums[right + 1]) right--;
                        } else if (sum < target) left++;
                        else right--;
                    }
                }
            }
            return res;
        }
    }

    // @lc code=start


    class Solution2 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                //剪枝
                if (nums[i] > target && nums[i] >= 0) {
                    break;
                }
                //nums[i]去重
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                for (int j = i + 1; j < nums.length; j++) {
                    //剪枝
                    if (nums[i] + nums[j] > target && nums[j] >= 0) {
                        break;
                    }
                    //nums[i]去重
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    int left = j + 1;
                    int right = nums.length - 1;
                    while (left < right) {
                        int sum = nums[i] + nums[j] + nums[left] + nums[right];
                        int longTarget = target;
                        //在Java中，int类型的最大值可以通过Integer.MAX_VALUE常量来表示，具体数值为2147483647，即$2^{31}-1，
                        // 所以 nums[i]+nums[j]+nums[left]+nums[right]不会溢出
                        // Long sum = Long.valueOf(nums[i]+nums[j]+nums[left]+nums[right]) ;
                        // Long longTarget = Long.valueOf(target) ;
                        if (sum < longTarget) {
                            left++;
                        } else if (sum > longTarget) {
                            right--;
                        } else {
                            //收集答案
                            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            //对nums[left]去重
                            while (right > left && nums[left] == nums[left + 1]) {
                                left++;
                            }
                            //nums[right]去重
                            while (right > left && nums[right] == nums[right - 1]) {
                                right--;
                            }
                            //双指针同时收缩
                            right--;
                            left++;
                        }
                    }
                }
            }
            return result;
        }
    }
// @lc code=end


//leetcode submit region end(Prohibit modification and deletion)

}