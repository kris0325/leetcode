/*
 * @lc app=leetcode id=98 lang=java
 *
 * [98] Validate Binary Search Tree
 *
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 *
 * algorithms
 * Medium (32.91%)
 * Likes:    16737
 * Dislikes: 1368
 * Total Accepted:    2.4M
 * Total Submissions: 7.2M
 * Testcase Example:  '[2,1,3]'
 *
 * Given the root of a binary tree, determine if it is a valid binary search
 * tree (BST).
 * 
 * A valid BST is defined as follows:
 * 
 * 
 * The left subtree of a node contains only nodes with keys less than the
 * node's key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [2,1,3]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is
 * 4.
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
    // 1.recursice 根据BTS,需要遍历每一颗子树，并比较当前节点值的上下界
    //（左，右边，不仅仅和单前根节点比较，还要和上一个根节点比较，而起左边，右边需要分开处理）
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelp(root, null, null);

    }

    public boolean isValidBSTHelp(TreeNode node, TreeNode lower,TreeNode upper){
        //leaf node
        if(node == null){
            return true;
        }
        //判断node是否满足 lower.val <node < upper.val
        // 取反面条件，满足时，则return false
        if(lower != null && node.val <= lower.val){
                return false;            
        }
        if(upper != null && node.val >= upper.val){
                return false;
        }
        //为了判断反面例子
        // 左边节点，更新上界upper = node
        // 右边节点，则更新下界lower = node
        //递归调用左右子树时，上界upper，下界lower会交替更新，
        //当递归到树的第三层，都有左右子树树，lower, node都会被更新
        //注意：因为要求所有子树都满足条件，所以左子树，右子树遍历结果的&&
        return isValidBSTHelp(node.left, lower, node) && isValidBSTHelp(node.right, node, upper);
    }
} 


class Solution {
    // 1.recursice根据BTS特性，中序遍历树，同时用数组收集节点值,将二叉树转换为数组，只需判断数组是单调递增序列即可
    public boolean isValidBST(TreeNode root) {
        List<Integer> nodeValList = new LinkedList();

        getNodeValList(root, nodeValList);
        for(int i = 0; i< nodeValList.size()-1; i++){
            if(nodeValList.get(i)>=nodeValList.get(i+1)){
                return false;
            }
        }
        return true;
    }

    public void getNodeValList(TreeNode node, List<Integer> nodeValList){
        if(node == null){
            return;
        }
        getNodeValList(node.left, nodeValList);
        nodeValList.add(node.val);
        getNodeValList(node.right, nodeValList);
    }
}


class Solution0 {
    //recursice
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        } 
        int rootVal = root.val;
        return isValidBSTHelp(rootVal,root.left, true) && isValidBSTHelp(rootVal,root.right, false) ;
    }

    public boolean isValidBSTHelp(int upRootVal, TreeNode curRoot, boolean isLeftTree ){
       //terminate condition
        if(curRoot == null){
            return true;
        } 
        //left tree
        if(isLeftTree){
            // 1.curRoot.val
            if(curRoot.val>= upRootVal){
                return false;
            }
            //2.curRoot.left
            if(curRoot.left !=null){
                if(curRoot.left.val >= curRoot.val){
                    return false;
                }
            }
            //3.curRoot.right
            if(curRoot.right != null){
                if(curRoot.right.val <= curRoot.val || curRoot.right.val>= upRootVal){
                    return false;
                }
            }
        }
        //right tree
        if(!isLeftTree){
            //1.curRoot.val
          if(curRoot.val<=upRootVal){
            return false;
          }
          // 2.curRoot.left
          if(curRoot.left != null){
                if(curRoot.left.val >= curRoot.val || curRoot.left.val <= upRootVal){
                    return false;
                }
            } 
          //3.curRoot.right
          if(curRoot.right != null){
                if(curRoot.right.val <= curRoot.val){
                    return false;
                }
            } 
        }
        //TODO  动态更新upRootVal 当前节点的上上一个upRootVal，也就是前2层根节点的值
        // bug: isValidBSTHelp这里因为参数没有前2层节点的信息，所以upRootVal没法动态更新，
        // 修复方法见solution
        return isValidBSTHelp(upRootVal, curRoot.left, isLeftTree) && isValidBSTHelp(upRootVal, curRoot.right,isLeftTree);
    }
}   
// @lc code=end

