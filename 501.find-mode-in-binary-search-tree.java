/*
 * @lc app=leetcode id=501 lang=java
 *
 * [501] Find Mode in Binary Search Tree
 *
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 *
 * algorithms
 * Easy (56.06%)
 * Likes:    3878
 * Dislikes: 782
 * Total Accepted:    310.1K
 * Total Submissions: 551.2K
 * Testcase Example:  '[1,null,2,2]'
 *
 * Given the root of a binary search tree (BST) with duplicates, return all the
 * mode(s) (i.e., the most frequently occurred element) in it.
 * 
 * If the tree has more than one mode, return them in any order.
 * 
 * Assume a BST is defined as follows:
 * 
 * 
 * The left subtree of a node contains only nodes with keys less than or equal
 * to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or
 * equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,null,2,2]
 * Output: [2]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [0]
 * Output: [0]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^5 <= Node.val <= 10^5
 * 
 * 
 * 
 * Follow up: Could you do that without using any extra space? (Assume that the
 * implicit stack space incurred due to recursion does not count).
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //1. tree to Hashmap <node.val, frequent> hashmap
    //2. sort hashmap by frequent, get max 
    //3.traverse hashmap to collect node.vals by max
    HashMap<Integer, Integer> val2frequent = new HashMap<>();
    public int[] findMode(TreeNode root) {
 
        List<Integer> result = new ArrayList<>();
        inorderTraverse(root);
        
        List<Integer>frequentList = new ArrayList<>();
        for(Integer frequent :val2frequent.values()){
            frequentList.add(frequent);
        }
        Integer max = frequentList.stream().max(Integer::compareTo).get();
        for(Map.Entry<Integer, Integer> entry: val2frequent.entrySet()){
            if(entry.getValue() == max){
                result.add(entry.getKey());
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray(); 
    }

    public void inorderTraverse(TreeNode root){
        if(null == root){
            return;
        }
        inorderTraverse(root.left);
        val2frequent.put(root.val, val2frequent.getOrDefault(root.val,0)+1);
        inorderTraverse(root.right);
    }
}

class Solution {
    // inorder traverse tree, and count frequent
    TreeNode pre = null;
    int maxfrequent = 0;
    int currentfrequent = 0;
    List<Integer> result = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        inorder(root);
        return result.stream().mapToInt(Integer::intValue).toArray(); 
    }

    public void inorder(TreeNode root){
        if(null == root){
            return;
        }
        inorder(root.left);

        //第一个节点
        if(pre == null ){
            currentfrequent = 1;
        } else if(pre.val == root.val) {
            //与前一个节点值相同
            currentfrequent++;
        } else {
            //与前一个节点值不相同的新节点
            currentfrequent = 1;
        } 
        if(currentfrequent > maxfrequent){    
            //更新
            result.clear();
            result.add(root.val);
            maxfrequent = currentfrequent;
        } else if(currentfrequent == maxfrequent){
            //添加
            result.add(root.val);
        }
        //更新pre节点
        pre = root;
        inorder(root.right);
    }
}
// @lc code=end

