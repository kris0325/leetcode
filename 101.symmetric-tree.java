/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
 *
 * https://leetcode.com/problems/symmetric-tree/description/
 *
 * algorithms
 * Easy (56.45%)
 * Likes:    15216
 * Dislikes: 371
 * Total Accepted:    2M
 * Total Submissions: 3.6M
 * Testcase Example:  '[1,2,2,3,4,4,3]'
 *
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 * 
 * 
 * 
 * Follow up: Could you solve it both recursively and iteratively?
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

 /* 同类型题
 100. Same Tree
 ** https://leetcode.com/problems/same-tree/description/
 * 572.Subtree of Another Tree
 *  * https://leetcode.com/problems/subtree-of-another-tree/description/

 * */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // BFS : use queue to level traverse tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            // left and right are  both null
            if(left == null && right == null){
                continue;
            }
            // one of nodes is null || both nodes are not null but the val is not eagqul
            if(left == null || right == null
            || left.val != right.val){
                return false;
            }
            // offer the next pair of nodes to queue
            // and nodes order is Symmetric push to queue
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
}
// @lc code=end

