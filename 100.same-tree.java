/*
 * @lc app=leetcode id=100 lang=java
 *
 * [100] Same Tree
 *
 * https://leetcode.com/problems/same-tree/description/
 *
 * algorithms
 * Easy (62.04%)
 * Likes:    11472
 * Dislikes: 239
 * Total Accepted:    2.2M
 * Total Submissions: 3.5M
 * Testcase Example:  '[1,2,3]\n[1,2,3]'
 *
 * Given the roots of two binary trees p and q, write a function to check if
 * they are the same or not.
 * 
 * Two binary trees are considered the same if they are structurally identical,
 * and the nodes have the same value.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in both trees is in the range [0, 100].
 * -10^4 <= Node.val <= 10^4
 * 
 * 
 */

// @lc code=start

import java.util.LinkedList;
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
    //思路：每一层进行 模拟pair node的比较顺序
    Queue<TreeNode> queue = new LinkedList<>();

    public boolean isSameTree(TreeNode p, TreeNode q) {
        //nodes are both null
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            //leaf node
            if(left == null && right == null){
                continue;
            }
            if(left == null || right == null
             || left.val != right.val){
                return false;
            }
            //pairs push to queue
            queue.offer(left.left);
            queue.offer(right.left);
            queue.offer(left.right);
            queue.offer(right.right);
        } 
        return true;
    }
}
// @lc code=end

