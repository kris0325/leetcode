import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given an integer array nums, return all the different possible non-decreasing
 * subsequences of the given array with at least two elements. You may return the
 * answer in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [4,6,7,7]
 * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [4,4,3,2,1]
 * Output: [[4,4]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 * <p>
 * <p>
 * Related Topics Array Hash Table Backtracking Bit Manipulation 👍 3632 👎 229
 */
       
/*
 2024-07-04 20:25:43
 Non-decreasing Subsequences
Category	Difficulty	Likes	Dislikes
algorithms	Medium (60.67%)	3632	229
Tags
depth-first-search

Companies
yahoo

Given an integer array nums, return all the different possible non-decreasing subsequences
 of the given array with at least two elements. You may return the answer in any order.



Example 1:

Input: nums = [4,6,7,7]
Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
Example 2:

Input: nums = [4,4,3,2,1]
Output: [[4,4]]


Constraints:

1 <= nums.length <= 15
-100 <= nums[i] <= 100
*/

class NonDecreasingSubsequences {
    public static void main(String[] args) {
        Solution solution = new NonDecreasingSubsequences().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //backtracking :
        //
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            backtrack(nums, 0);
            return result;
        }

        public void backtrack(int[] nums, int start) {
            if (path.size() > 1) {
                result.add(new ArrayList<>(path));
            }
            //本题其实类似求子集问题，也是要遍历树形结构找每一个节点，并不是只收集树形中根节点到叶子节点完整路径，而是满足条件且只要超过2个节点就属于需要收集的字集
            // 所以和回溯算法：求子集问题！ (opens new window)一样，可以不加终止条件，因为startIndex每次都会加1，所以并不会无限递归。
//            if (start == nums.length) {
//                return;
//            }
            HashSet<Integer> usedSet = new HashSet<>();
            for (int i = start; i < nums.length; i++) {
                //不满足递增 ｜｜ 或者当前层loop中已使用的值，需要去重，    则跳过该元素
//                if (!path.isEmpty() && nums[i] > nums[i-1] || usedSet.contains(nums[i])) {
                if (!path.isEmpty() && path.getLast() > nums[i] || usedSet.contains(nums[i])) {
                        continue;
                }
                path.add(nums[i]);
                usedSet.add(nums[i]);
                backtrack(nums, i + 1);
                path.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}