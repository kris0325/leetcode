/*
 * @lc app=leetcode id=617 lang=java
 *
 * [617] Merge Two Binary Trees
 *
 * https://leetcode.com/problems/merge-two-binary-trees/description/
 *
 * algorithms
 * Easy (78.92%)
 * Likes:    8757
 * Dislikes: 302
 * Total Accepted:    775.6K
 * Total Submissions: 981.9K
 * Testcase Example:  '[1,3,2,5]\n[2,1,3,null,4,null,7]'
 *
 * You are given two binary trees root1 and root2.
 * 
 * Imagine that when you put one of them to cover the other, some nodes of the
 * two trees are overlapped while the others are not. You need to merge the two
 * trees into a new binary tree. The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node. Otherwise, the
 * NOT null node will be used as the node of the new tree.
 * 
 * Return the merged tree.
 * 
 * Note: The merging process must start from the root nodes of both trees.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * Output: [3,4,5,5,4,null,7]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root1 = [1], root2 = [1,2]
 * Output: [2,2]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in both trees is in the range [0, 2000].
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
class Solution1 {
    //思路: recursive 
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode node = new TreeNode(0);
        return mergeTreesRecursive(node, root1, root2);
    }

    public TreeNode mergeTreesRecursive(TreeNode node,TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){
            return null;
        }
        if(node == null){
            node = new TreeNode(0);
        }
        if(root1 != null && root2 != null){
            node.val = root1.val + root2.val;
        }
        if(root1 != null && root2 == null){
            node.val = root1.val;
        }
        if(root1 == null && root2 != null){
            node.val = root2.val;
        }
        node.left = mergeTreesRecursive(node.left, null != root1? root1.left :null
        ,null !=root2? root2.left: null);
        node.right = mergeTreesRecursive(node.right, null != root1? root1.right:null
        , null !=root2? root2.right: null);
        return node;
    }
}

class Solution {
    //思路: 简化版的recursive 
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        TreeNode node = new TreeNode(0);
        node.val = root1.val + root2.val;
        node.left = mergeTrees(root1.left,root2.left);
        node.right = mergeTrees(root1.right, root2.right);
        return node;
    }
}
// @lc code=end

