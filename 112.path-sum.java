/*
 * @lc app=leetcode id=112 lang=java
 *
 * [112] Path Sum
 *
 * https://leetcode.com/problems/path-sum/description/
 *
 * algorithms
 * Easy (50.33%)
 * Likes:    9628
 * Dislikes: 1101
 * Total Accepted:    1.5M
 * Total Submissions: 2.9M
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,null,1]\n22'
 *
 * Given the root of a binary tree and an integer targetSum, return true if the
 * tree has a root-to-leaf path such that adding up all the values along the
 * path equals targetSum.
 * 
 * A leaf is a node with no children.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Explanation: There two root-to-leaf paths in the tree:
 * (1 --> 2): The sum is 3.
 * (1 --> 3): The sum is 4.
 * There is no root-to-leaf path with sum = 5.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [], targetSum = 0
 * Output: false
 * Explanation: Since the tree is empty, there are no root-to-leaf paths.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
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
class Solution{
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //Leaf node , current path is not we want , then return
        if(null == root){
            return false;
        }
        //assert child node is the need path 
        if(root.left == null && root.right == null && targetSum == root.val ){
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

}

class Solution1 {
    //solution1: recursive traverse root
    Integer pathVal =0;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(null == root){
            return false;
        }
        return backtracking(root,0,targetSum); 
    }

    public boolean backtracking(TreeNode node, int pathVal, int targetSum){
        //在递归调用中，通过逻辑或 (||) 操作符组合返回值的原因是，
        //逻辑或运算的特性使得只要任意一个操作数为 true，整个表达式的值就为 true。具体来说，
        //A || B 只有在 A 和 B 都为 false 时才返回 false，否则返回 true。
        
        /*
        *当递归调用返回 false 时，这仅意味着当前路径不满足条件。
        但由于递归检查了所有可能的路径，如果有任意一条路径满足条件，则最终结果会返回 true。
        通过逻辑或 (||) 操作符组合返回值，
        确保只要存在一条满足条件的路径，最终结果就会是 true
        */
        //terminate condition
        if(null == node){
            //这仅意味着当前路径不满足条件
            return false;
        }
        pathVal += node.val;
        if(node.left == null && node.right == null){
            if(pathVal == targetSum){
                return true;
            }
        }
        return backtracking(node.left,pathVal, targetSum) || backtracking(node.right,pathVal, targetSum);
    } 
}
// @lc code=end

