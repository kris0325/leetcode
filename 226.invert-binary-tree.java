/*
 * @lc app=leetcode id=226 lang=java
 *
 * [226] Invert Binary Tree
 *
 * https://leetcode.com/problems/invert-binary-tree/description/
 *
 * algorithms
 * Easy (76.92%)
 * Likes:    13900
 * Dislikes: 224
 * Total Accepted:    2.1M
 * Total Submissions: 2.7M
 * Testcase Example:  '[4,2,7,1,3,6,9]'
 *
 * Given the root of a binary tree, invert the tree, and return its root.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [2,1,3]
 * Output: [2,3,1]
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
    public TreeNode invertTree(TreeNode root) {
        //思路：BFS :use queue to level traverse tree 
      // exchane left child and right child
      if(null == root){
          return root;
      }
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);
      while (!queue.isEmpty()) {
        int curLevelLength = queue.size();
        for(int i=0; i< curLevelLength;i++){
          TreeNode node = queue.poll();
          TreeNode tmpNode = node.left;
          node.left = node.right;
          node.right = tmpNode;
          if(null != node.left){
              queue.offer(node.left);
          }
          if(null != node.right){
              queue.offer(node.right);
          }
         }
      }
      return root; 
  }
}

class Solution {
     //思路：DFS :use queue to level traverse tree 
    // exchane left child and right child
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
    


        
// @lc code=end

