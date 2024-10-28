/*
 * @lc app=leetcode id=113 lang=java
 *
 * [113] Path Sum II
 *
 * https://leetcode.com/problems/path-sum-ii/description/
 *
 * algorithms
 * Medium (58.45%)
 * Likes:    7918
 * Dislikes: 154
 * Total Accepted:    869.9K
 * Total Submissions: 1.5M
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * Given the root of a binary tree and an integer targetSum, return all
 * root-to-leaf paths where the sum of the node values in the path equals
 * targetSum. Each path should be returned as a list of the node values, not
 * node references.
 * 
 * A root-to-leaf path is a path starting from the root and ending at any leaf
 * node. A leaf is a node with no children.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [1,2], targetSum = 0
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
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
// public static void main(String[]args){
//     Solution solution = new Solution();
//     TreeNode root = new TreeNode(5);
//     root.left = new TreeNode(4);
//     root.right = new TreeNode(8);
//     root.left.left = new TreeNode(11);
//     root.left.left.left = new TreeNode(7);
//     root.left.left.right = new TreeNode(2);
//     root.right.left = new TreeNode(13);
//     root.right.right = new TreeNode(4);
//     root.right.right.left = new TreeNode(5);
//     root.right.right.right = new TreeNode(1);
//     List<List<Integer>> pathList = solution.pathSum(root, 22);
//     System.out.println(pathList);

// }

class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> pathList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if(null == root){
            return pathList;
        }
        backtracking(root, targetSum, pathList, path);
        return pathList;
    }

    public void backtracking(TreeNode node
    , int targetSum, List<List<Integer>> pathList
    , List<Integer> path){

        //添加当前节点
        path.add(node.val);
        // leaf node
        if(node.left == null && node.right == null){
           if(path.stream()
                   .mapToInt(Integer::intValue)
                   .sum() == targetSum){
                    //path是应用对象，所以不能直接pathList.add(path)，需要拷贝后再add
                       pathList.add(new ArrayList<>(path));
              }
         //回溯
          path.remove(path.size()-1); 
          //返回上一层调用
          return;
        }

        /**
         * 遍历左右2个节点
        * for(int i =0； i<2; i++ )
        * 遍历完后，再回溯
       */
        //遍历left
        if(null != node.left){
            backtracking(node.left, targetSum, pathList, path);
        }
        //遍历right
        if(null != node.right){
            backtracking(node.right, targetSum, pathList, path);
        }

        //在处理完当前节点的左右子节点后进行回溯
        path.remove(path.size()-1);
    }
}
// @lc code=end

