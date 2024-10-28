import java.util.HashMap;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You
 * may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * <p>
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 10⁴
 * -10⁹ <= nums[i] <= 10⁹
 * <p>
 * <p>
 * <p>
 * Follow-up: Could you solve the problem in linear time and in
 * O(1) space?
 * <p>
 * Related Topics Array Hash Table Divide and Conquer Sorting Counting 👍 19560 👎
 * 653
 */
       
/*
 2024-09-21 21:56:36
*/

class MajorityElement {
    public static void main(String[] args) {
        Solution solution = new MajorityElement().new Solution();
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
    class Solution1 {
        //hashmap
        public int majorityElement(int[] nums) {
            int res = Integer.MAX_VALUE;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            for (int num : map.keySet()) {
                if (map.get(num) > nums.length / 2) {
                    res = num;
                }
            }
            return res;
        }
    }

    class Solution2 {
        //divide and conquer
        //tc:nlogn
        public int majorityElement(int[] nums) {
            return divide(nums, 0, nums.length - 1);
        }

        public int divide(int[] nums, int left, int right) {
            if (left == right) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;

            //左右遞歸
            int leftResult = divide(nums, left, mid);
            int rightResult = divide(nums, mid + 1, right);

            //合併結果
            if (leftResult == rightResult) {
                return leftResult;
            }
            int leftCount = conquer(nums, left, right, leftResult);
            int rightCount = conquer(nums, left, right, rightResult);
            return leftCount > rightCount ? leftResult : rightResult;
        }

        public int conquer(int[] nums, int left, int right, int target) {
            int count = 0;
            for (int i = left; i <= right; i++) {
                if (nums[i] == target) {
                    count++;
                }
            }
            return count;
        }
    }

    class Solution {
        //投票法
        //tc:o(n)
        public int majorityElement(int[] nums) {
            int count = 0;
            int candidate = 0;
            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ?
                        1 : -1;
            }
            return candidate;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}