/*
 * @lc app=leetcode id=222 lang=java
 *
 * [222] Count Complete Tree Nodes
 *
 * https://leetcode.com/problems/count-complete-tree-nodes/description/
 *
 * algorithms
 * Easy (65.10%)
 * Likes:    8584
 * Dislikes: 511
 * Total Accepted:    744.3K
 * Total Submissions: 1.1M
 * Testcase Example:  '[1,2,3,4,5,6]'
 *
 * Given the root of a complete binary tree, return the number of the nodes in
 * the tree.
 * 
 * According to Wikipedia, every level, except possibly the last, is completely
 * filled in a complete binary tree, and all nodes in the last level are as far
 * left as possible. It can have between 1 and 2^h nodes inclusive at the last
 * level h.
 * 
 * Design an algorithm that runs in less than O(n) time complexity.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = []
 * Output: 0
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [1]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 5 * 10^4].
 * 0 <= Node.val <= 5 * 10^4
 * The tree is guaranteed to be complete.
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
    public int countNodes(TreeNode root) {
        //solution1: 
        //BSF: queue level order traverse 
        int count = 0;
        if(null == root){
            return count;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int currentLevelSize = queue.size();
            count += currentLevelSize;
            for(int i = 0; i < currentLevelSize; i++){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return count;
    }
}

class Solution {
      //solution2: 
      //recursive: 
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return 1+ countNodes(root.left) + countNodes(root.right);
    }


}

// @lc code=end

