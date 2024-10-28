/*
 * @lc app=leetcode id=530 lang=java
 *
 * [530] Minimum Absolute Difference in BST
 *
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
 *
 * algorithms
 * Easy (58.37%)
 * Likes:    4367
 * Dislikes: 223
 * Total Accepted:    390K
 * Total Submissions: 667.8K
 * Testcase Example:  '[4,2,6,1,3]'
 *
 * Given the root of a Binary Search Tree (BST), return the minimum absolute
 * difference between the values of any two different nodes in the tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [2, 10^4].
 * 0 <= Node.val <= 10^5
 * 
 * 
 * 
 * Note: This question is the same as 783:
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// class Solution {
//     //bug:这个solution是求根节点与相邻左，右节点之间的距离，
// 这不是题目要求的任意节点，因为最小差值有可能不是根节点与其子节点（不一定相邻）
//     int minDif = Integer.MAX_VALUE;
//     public int getMinimumDifference(TreeNode root) {
//         if(null == root){
//             return minDif;
//         }
//         if(root.left != null){
//             minDif = Math.min(minDif , Math.abs(root.val - root.left.val));
//         }
//         if(root.right != null){
//             minDif = Math.min(minDif , Math.abs(root.right.val - root.val));
//         }
//         return Math.min(minDif, Math.min(getMinimumDifference(root.left), getMinimumDifference(root.right)));
//     }
// }

class Solution {
    //Solution1.中序遍历求任意两个相邻节点之间的距离
    //Solution2. 也可以将BST中序遍历后转换为有序数组后，遍历数组再求前后两个元素的最小差值
    int minDif = Integer.MAX_VALUE;
    int preNodeVal = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDif;
    }

    public void inorder(TreeNode root){
        if(root == null){
            return;
        }
        inorder(root.left);
        minDif = Math.min(minDif,Math.abs(root.val -preNodeVal));
        preNodeVal = root.val;
        inorder(root.right);
    }
}

// @lc code=end

