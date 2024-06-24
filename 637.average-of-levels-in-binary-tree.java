/*
 * @lc app=leetcode id=637 lang=java
 *
 * [637] Average of Levels in Binary Tree
 *
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
 *
 * algorithms
 * Easy (72.46%)
 * Likes:    5242
 * Dislikes: 325
 * Total Accepted:    520.9K
 * Total Submissions: 715K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given the root of a binary tree, return the average value of the nodes on
 * each level in the form of an array. Answers within 10^-5 of the actual
 * answer will be accepted.
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5,
 * and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
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
    //思路：quque队列实现层序遍历(BFS)
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int leveLength = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            for(int i = 0; i< leveLength; i++){
                TreeNode node = queue.poll();
                curLevel.add(node.val);
                if(null != node.left){
                    queue.add(node.left);
                }
                if(null != node.right){
                    queue.add(node.right);
                }
            }  
            result.add(curLevel.stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0));
        }
        return result;
    }
}
// @lc code=end

