/*
 * @lc app=leetcode id=199 lang=java
 *
 * [199] Binary Tree Right Side View
 *
 * https://leetcode.com/problems/binary-tree-right-side-view/description/
 *
 * algorithms
 * Medium (63.04%)
 * Likes:    11901
 * Dislikes: 944
 * Total Accepted:    1.4M
 * Total Submissions: 2.1M
 * Testcase Example:  '[1,2,3,null,5,null,4]'
 *
 * Given the root of a binary tree, imagine yourself standing on the right side
 * of it, return the values of the nodes you can see ordered from top to
 * bottom.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,null,3]
 * Output: [1,3]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = []
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    //思路：queue 实现层序遍历
         // 使用leveLength = queue.size() 控制每层的遍历，
         //同时将当前层的每个节点的子节点入队列
         // 遍历到末尾时，收集右视图的节点
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(null == root){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int leveLength = queue.size();
            for(int i = 0; i< leveLength; i++){
                TreeNode node = queue.poll();
                if(i == leveLength -1){
                    result.add(node.val);
                }
                if(null != node.left){
                    queue.add(node.left);
                }
                if(null != node.right){
                    queue.add(node.right);
                }

            }     
        }
        return result;  
    }
}
// @lc code=end

