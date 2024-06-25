/*
 * @lc app=leetcode id=572 lang=java
 *
 * [572] Subtree of Another Tree
 *
 * https://leetcode.com/problems/subtree-of-another-tree/description/
 *
 * algorithms
 * Easy (47.83%)
 * Likes:    8164
 * Dislikes: 508
 * Total Accepted:    869K
 * Total Submissions: 1.8M
 * Testcase Example:  '[3,4,5,1,2]\n[4,1,2]'
 *
 * Given the roots of two binary trees root and subRoot, return true if there
 * is a subtree of root with the same structure and node values of subRoot and
 * false otherwise.
 * 
 * A subtree of a binary tree tree is a tree that consists of a node in tree
 * and all of this node's descendants. The tree tree could also be considered
 * as a subtree of itself.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the root tree is in the range [1, 2000].
 * The number of nodes in the subRoot tree is in the range [1, 1000].
 * -10^4 <= root.val <= 10^4
 * -10^4 <= subRoot.val <= 10^4
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

 class Solution{
        // solution1: recursive traverse root 
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if(null == root){
                return false;
            }
            if(isSameTree(root, subRoot)){
                return true;
            }
             // 正确处理递归返回值，使用 || 连接两个递归调用
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);   
        }

        public boolean isSameTree(TreeNode node, TreeNode subRoot){
            if(node == null && subRoot == null){
                return true;
            }
            if(node == null || subRoot == null || node.val != subRoot.val){
                return false;
            }
            // 递归比较两个子树是否相同
            return isSameTree(node.left, subRoot.left) && isSameTree(node.right, subRoot.right);
        }
 }

// class Solution2 {
//     //Solution2思路：1.level order traverse root, 
//     //2.assert each subTree is the same with subToot


//     // solution2:  levelorder traversal root 
//     public boolean isSubtree(TreeNode root, TreeNode subRoot) {
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(root);
//         while (!queue.isEmpty()) {
//             int length = queue.size();
//             for(int i = 0; i< length; i++){
//                 TreeNode node = queue.poll();
//                 if(isSameTree(node, subRoot)){
//                     return true;
//                 }
//                 if(null!= node.left){
//                     queue.offer(node.left);
//                 }
//                 if(null!= node.right){
//                     queue.offer(node.right);
//                 }
//             }
//         }    
//         return false;  
//     }


//     public boolean isSameTree(TreeNode node, TreeNode subRoot){
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(node);
//         queue.offer(subRoot);
//         while (!queue.isEmpty()) {
//             // int levelLength = queue.size();
//             // for(int i = 0; i< levelLength; i++){
//             // }
//             TreeNode left = queue.poll();
//             TreeNode right = queue.poll();

//             if(left == null  && right == null){
//                 continue;
//             }
//             if(left == null || right == null || left.val != right.val){
//                 return false;
//             }
//             queue.offer(left.left);
//             queue.offer(right.left);
//             queue.offer(left.right);
//             queue.offer(right.right);
//         }
//         return true;
//     }
// }
// @lc code=end

