/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
 *
 * https://leetcode.com/problems/balanced-binary-tree/description/
 *
 * algorithms
 * Easy (51.99%)
 * Likes:    10630
 * Dislikes: 684
 * Total Accepted:    1.5M
 * Total Submissions: 2.9M
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, determine if it is height-balanced.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = []
 * Output: true
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 5000].
 * -10^4 <= Node.val <= 10^4
 * 
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
class Solution {
    //思路：从叶子节点遍历到到根节点，判断每个节点的的左右子树的高度差小于等于1，则为平衡树
    //注意：数的高度和深度不一样
        //1.高度数是离叶子节点的距离，叶子节点的默认距离为1
        //2.深度是离更节点的距离，根节点的默认距离为1
    public boolean isBalanced(TreeNode root) {
        return checkHight(root) == -1? false : true;        
    }

    public int checkHight(TreeNode root){
        if(null == root){
            return 0;
        }
        int leftHight = checkHight(root.left);
        if(leftHight == -1){
            return -1;
        }
        int rightHight = checkHight(root.right);
        if(rightHight == -1){
            return -1;
        }
        if(Math.abs(leftHight -rightHight) > 1){
            return -1;
        }
        //叶子节点的高度默认为1
        return Math.max(leftHight, rightHight)+1;

    }
}
// @lc code=end

