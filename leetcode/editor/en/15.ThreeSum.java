import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not
 * matter.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 3 <= nums.length <= 3000
 * -10⁵ <= nums[i] <= 10⁵
 * <p>
 * <p>
 * Related Topics Array Two Pointers Sorting 👍 31541 👎 2953
 */
       
/*
 2024-10-28 15:20:16
*/

class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
    }

}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 1.排序后
     * 2.two pointer
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //sort
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            //i 去重复
            if (i  > 0  && nums[i] == nums[i -1])
                continue;
            int sum = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == sum) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    //注意1.为了避免重复，仅在确认发现了一组解之后再移动left和right指针略过重复项。不要先略过重复项再判断是否解成立。
                    //left , right 去重复
                    //注意，当前left，right index已经更新后，那么与上一个元素相比，需要left-1,right + 1， 即为上一个元素
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] < sum) left++;
                else if (nums[left] + nums[right] > sum) right--;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}