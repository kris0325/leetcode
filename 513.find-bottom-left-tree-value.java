/*
 * @lc app=leetcode id=513 lang=java
 *
 * [513] Find Bottom Left Tree Value
 *
 * https://leetcode.com/problems/find-bottom-left-tree-value/description/
 *
 * algorithms
 * Medium (71.12%)
 * Likes:    3777
 * Dislikes: 294
 * Total Accepted:    361.7K
 * Total Submissions: 508K
 * Testcase Example:  '[2,1,3]'
 *
 * Given the root of a binary tree, return the leftmost value in the last row
 * of the tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [2,1,3]
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,3,4,null,5,6,null,null,7]
 * Output: 7
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
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
class Solution1 {
    //solution1: 1.level order traversal to get List<List<Integer>> levelLists, 
     //2. get lastLevel list
     //3. and then get the first one which is the bottom and leftmost
    public int findBottomLeftValue(TreeNode root) {
        List<List<Integer>> levelLists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevelList = new ArrayList<>();
            for(int i =0; i< levelSize; i++){
                TreeNode node = queue.poll();
                currentLevelList.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            levelLists.add(currentLevelList);     
        }
        return levelLists.get(levelLists.size()-1).get(0);
    }
}

class Solution {
    //solution1: 1. backtracking recursice 
    // which is the bottom and leftmost
    Integer maxDepth = Integer.MIN_VALUE;
    Integer leftmostVal = 0;
    public int findBottomLeftValue(TreeNode root) {
        backtracking(root, 0);
        return leftmostVal;
    }

    public void backtracking(TreeNode node, Integer currentDepth){
        //确定终止条件
        if(node.left == null && node.right == null){
            if(currentDepth > maxDepth){
                maxDepth = currentDepth;
                leftmostVal = node.val;
            }
            return;
        }
        //隐式回溯: currentDepth +1
        if(null != node.left){
                backtracking(node.left, currentDepth +1);
        }
        //相当于回溯
        // if(null != node.left){
        //     currentDepth++;
        //     backtracking(node.left, currentDepth);
        //     currentDepth--;
        // }

        //隐式回溯currentDepth +1
        if(null != node.right){
                backtracking(node.right, currentDepth +1);
        }
    }
}


// @lc code=end
