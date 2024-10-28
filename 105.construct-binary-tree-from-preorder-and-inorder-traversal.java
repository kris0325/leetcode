/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
 *
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *
 * algorithms
 * Medium (63.84%)
 * Likes:    14871
 * Dislikes: 503
 * Total Accepted:    1.3M
 * Total Submissions: 2M
 * Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
 *
 * Given two integer arrays preorder and inorder where preorder is the preorder
 * traversal of a binary tree and inorder is the inorder traversal of the same
 * tree, construct and return the binary tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
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
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelp(preorder, 0, preorder.length
        , inorder, 0, inorder.length);
    }

    public TreeNode buildTreeHelp(int[] preorder, int preorderBegin, int preorderEnd
    , int[] inorder , int inorderBegin, int inorderEnd){
        //edge case
        if(preorderBegin - preorderEnd == 0){
            return null;
        }
        //1.rootVal
        int rootVal = preorder[preorderBegin];
        TreeNode root = new TreeNode(rootVal);
        //edge case
        if(preorder.length ==1){
            return root;
        }

        //2.get splitIndex by rootVal
        int splitIndex;
        for(splitIndex = inorderBegin; splitIndex< inorderEnd; splitIndex++){
            if(inorder[splitIndex] == rootVal){
                break;
            }
        }

        //3.split inorder to left and right
        int inorderLeftBegin = inorderBegin;
        int inorderLeftEnd = splitIndex;
        int inorderRightBegin = splitIndex+1;
        int inorderRightEnd = inorderEnd;

        //3.split preorder to left and right
        int preorderLeftBegin = preorderBegin+1;
        int preorderLeftEnd = preorderLeftBegin + (inorderLeftEnd - inorderLeftBegin);
        int preorderRightBegin = preorderLeftEnd;
        int preorderRightEnd = preorderEnd;

        //4. recursive traverse left and right 
        root.left = buildTreeHelp(preorder, preorderLeftBegin, preorderLeftEnd
        , inorder, inorderLeftBegin, inorderLeftEnd);
        root.right = buildTreeHelp(preorder, preorderRightBegin, preorderRightEnd
        , inorder, inorderRightBegin, inorderRightEnd);
        //5. return root
        return root;
    }
}
// @lc code=end

