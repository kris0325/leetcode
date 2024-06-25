/*
 * @lc app=leetcode id=257 lang=java
 *
 * [257] Binary Tree Paths
 *
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 * algorithms
 * Easy (63.60%)
 * Likes:    6537
 * Dislikes: 292
 * Total Accepted:    737.8K
 * Total Submissions: 1.2M
 * Testcase Example:  '[1,2,3,null,5]'
 *
 * Given the root of a binary tree, return all root-to-leaf paths in any
 * order.
 * 
 * A leaf is a node with no children.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1]
 * Output: ["1"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 100].
 * -100 <= Node.val <= 100
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

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
    //思路：按照前序遍历的顺序，全部路径使用backtracking
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        // if(null == root){
        //     return result;
        // }
        binaryTreePathsBacktracking(root, result, path);
        return result;
    }

    public void binaryTreePathsBacktracking(TreeNode node
    ,List<String> result, List<String> path){
        //前序遍历 root node 根节点 
        path.add(String.valueOf(node.val));

        // leaf node
        StringBuilder sb = new StringBuilder();
        if(node.left == null && node.right == null){
            for(int i =0; i< path.size()-1;i++){
                sb.append(path.get(i)).append("->");
            }
            //join the last one
            sb.append(path.get(path.size()-1));
            //collect path to result
            result.add(sb.toString());
            //back to up level
            return;
        }
        //left
        if(node.left != null){
            binaryTreePathsBacktracking(node.left,result, path);
            //backtracking
            path.removeLast();
        }
        //right
        if(node.right != null){
            binaryTreePathsBacktracking(node.right, result, path);
            //backtracking
            path.removeLast();
        }

    }
}
// @lc code=end

