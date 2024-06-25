/*
 * @lc app=leetcode id=404 lang=java
 *
 * [404] Sum of Left Leaves
 *
 * https://leetcode.com/problems/sum-of-left-leaves/description/
 *
 * algorithms
 * Easy (60.56%)
 * Likes:    5464
 * Dislikes: 309
 * Total Accepted:    626.8K
 * Total Submissions: 1M
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given the root of a binary tree, return the sum of all left leaves.
 * 
 * A leaf is a node with no children. A left leaf is a leaf that is the left
 * child of another node.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 24
 * Explanation: There are two left leaves in the binary tree, with values 9 and
 * 15 respectively.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 1000].
 * -1000 <= Node.val <= 1000
 * 
 * 
 */

// @lc code=start

import java.util.Stack;



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
// class Solution1 {
//     //思路：1.traversal 
//     public int sumOfLeftLeaves(TreeNode root) {
//         if(root == null){
//             return 0;
//         }
//         int leftVal = 0 ;
//         if(root.left != null && root.left.left == null && root.left.right == null){
//             leftVal = root.left.val;
//         }
//         int leftTreeLeftVal = sumOfLeftLeaves(root.left);
//         int rightTreeLeftVal = sumOfLeftLeaves(root.right);
//         return leftVal + leftTreeLeftVal + rightTreeLeftVal;
//     }
// }

class Solution {
    //思路：2.iterative  
    public int sumOfLeftLeaves(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int leftLeafVal = 0;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(null == node){
                leftLeafVal +=0;  
            }
            if(null != node.left && null == node.left.left && null == node.left.right){
                leftLeafVal += node.left.val; 
            }
            stack.push(node.left);
            stack.push(node.right);
        }
        return leftLeafVal;
    }
}
// @lc code=end

