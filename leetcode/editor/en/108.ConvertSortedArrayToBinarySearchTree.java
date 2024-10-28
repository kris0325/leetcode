import java.util.Arrays;

/**
 * Convert Sorted Array to Binary Search Tree
 * Category	Difficulty	Likes	Dislikes
 * algorithms	Easy (71.65%)	10921	556
 * Tags
 * tree | depth-first-search
 * <p>
 * Companies
 * airbnb
 * <p>
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁴
 * -10⁴ <= nums[i] <= 10⁴
 * nums is sorted in a strictly increasing order.
 * <p>
 * <p>
 * Related Topics Array Divide and Conquer Tree Binary Search Tree Binary Tree 👍
 * 10921 👎 556
 */
       
/*
 2024-06-29 22:06:36
*/

class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new ConvertSortedArrayToBinarySearchTree().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        // 二分法，获取有序数组中间节点为根节点，以中间节点作为切割点，然后再递归左数组，右数组
        public TreeNode sortedArrayToBST(int[] nums) {
           return sortedArrayToBST(nums, 0, nums.length - 1);
        }
        public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
            if (left > right) return null;
            int mid = left + (right - left) / 2;
            TreeNode current = new TreeNode(nums[mid]);
            current.left = sortedArrayToBST(nums, left, mid - 1);
            current.right = sortedArrayToBST(nums, mid + 1, right);
            return current;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}