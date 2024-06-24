/*
 * @lc app=leetcode id=515 lang=java
 *
 * [515] Find Largest Value in Each Tree Row
 *
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
 *
 * algorithms
 * Medium (65.67%)
 * Likes:    3599
 * Dislikes: 113
 * Total Accepted:    346.3K
 * Total Submissions: 527.2K
 * Testcase Example:  '[1,3,2,5,3,null,9]'
 *
 * Given the root of a binary tree, return an array of the largest value in
 * each row of the tree (0-indexed).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,3,2,5,3,null,9]
 * Output: [1,3,9]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,3]
 * Output: [1,3]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree will be in the range [0, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Comparator;
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
    //思路：queue實現BFS
    public List<Integer> largestValues(TreeNode root) {
          List<Integer> result = new ArrayList<>();
            if(null == root) {
                return result;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                //收集当前level的节点元素
                List<Integer> curlist = new ArrayList<>();
                //记录当前节点数量
                int len = queue.size();
                //遍历当前level
                for(int i = 0; i < len;i++){
                    TreeNode node = queue.poll();  
                    curlist.add(node.val);
                    if(node.left != null){
                        queue.offer(node.left);
                    }
                    if(node.right != null){
                        queue.offer(node.right);
                    }
                }
                result.add(curlist.stream()
                .max(Comparator.naturalOrder()).get());
            }
            return result;
        }
}
// @lc code=end

