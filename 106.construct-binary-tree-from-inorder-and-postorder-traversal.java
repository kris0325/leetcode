/*
 * @lc app=leetcode id=106 lang=java
 *
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
 *
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 *
 * algorithms
 * Medium (62.77%)
 * Likes:    7967
 * Dislikes: 130
 * Total Accepted:    654.7K
 * Total Submissions: 1M
 * Testcase Example:  '[9,3,15,20,7]\n[9,15,7,20,3]'
 *
 * Given two integer arrays inorder and postorder where inorder is the inorder
 * traversal of a binary tree and postorder is the postorder traversal of the
 * same tree, construct and return the binary tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
    // recursive 
    //1.rootval == postorder[postorder.length-1] , constractor Root node
    //2.splite indorder into left array  and right array by rootval
    //3.splite postorder into left array and right array by indorder left array
    //4.recursive root.left =   buildTree( indorder left and postorder left), 
            //root.left =     buildTree( indorder right and postorder right)
    //5. return root
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //edge case 注意处理递归调用中的边界条件
        if(0 == inorder.length || postorder.length == 0){
            return null;
        }

        //1.rootval
        int rootVal = postorder[postorder.length-1];
        TreeNode root = new TreeNode(rootVal);
        if(inorder.length == 1){
            return root;
        }

        //2.splite indorder into left array  and right array by rootval
        int spliteIndex;
        for(spliteIndex = 0; spliteIndex < inorder.length; spliteIndex++){
            if(inorder[spliteIndex] == rootVal){
                break;
            }
        }
        int[] indorderLeft = IntStream.range(0, spliteIndex)
        .map(i -> inorder[i])
        .toArray();

        int[] indorderRight = IntStream.range(spliteIndex+1, inorder.length)
        .map(i -> inorder[i])
        .toArray();

        //3.splite postorder into left array and right array by indorder left array
        int[] postorderLeft = IntStream.range(0, spliteIndex)
        .map(i -> postorder[i])
        .toArray();
        int[] postorderRight = IntStream.range(spliteIndex, postorder.length-1)
        .map(i -> postorder[i])
        .toArray();
    
        //4.recursive root.left =   buildTree( indorder left and postorder left), 
            //root.left =     buildTree( indorder right and postorder right)
        root.left = buildTree(indorderLeft, postorderLeft);
        root.right = buildTree(indorderRight, postorderRight);

        //5. return root
        return root;
        
    }
}


class Solution {
    // solution2: 优化时间、空间复杂度，不需要new array, 直接使用数组下标index操作
    // recursive 
    //1.rootval == postorder[postorder.length-1] , constractor Root node
    //2.splite indorder into left array  and right array by rootval
    //3.splite postorder into left array and right array by indorder left array
    //4.recursive root.left =   buildTree( indorder left and postorder left), 
            //root.left =     buildTree( indorder right and postorder right)
    //5. return root
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length ==0 || postorder.length ==0){
            return null;
        }
        return buildTreeRecursive(inorder, 0, inorder.length
        , postorder, 0, postorder.length);
    }

    public TreeNode buildTreeRecursive(int[] inorder, int inorderBegin,int inorderEnd
    ,int[] postorder, int postorderBegin,int postorderEnd){
        if(inorderEnd - inorderBegin == 0){
            return null;
        }
        int rootVal = postorder[postorderEnd-1];
        TreeNode root = new TreeNode(rootVal);
        if(inorderEnd - inorderBegin == 1){
            return root;
        }

        int spliteIndex;
        for(spliteIndex =inorderBegin; spliteIndex <inorderEnd; spliteIndex++){
            if(rootVal == inorder[spliteIndex]){
                break;
            }
        }

        //split inorder 左开右闭
        int inorderLeftBegin = inorderBegin;
        int inorderLeftEnd = spliteIndex;
        //jump rootVal == inorder[spliteIndex]
        int inorderRightBegin = spliteIndex+1;
        int inorderRightEnd = inorderEnd;

        //split postorder 左开右闭
        int postorderLeftBegin = postorderBegin;
        //终止位置是 需要 postorderBegin 加上 中序区间的大小size，即加上左子树的节点个数
        int postorderLeftEnd = postorderBegin + (spliteIndex - inorderBegin);
        //不能用直接使用spliteIndex, 因为spliteIndex是inorder的索引下标，不能直接用于postorder，
        //必须使用左子树节点个数（二者左子树节点个数相同）
        // int postorderLeftEnd = spliteIndex;

        int postorderRightBegin = postorderLeftEnd;
        //jump rootVal
        int postorderRightEnd = postorderEnd-1;

        root.left = buildTreeRecursive(inorder, inorderLeftBegin, inorderLeftEnd
        ,postorder, postorderLeftBegin,postorderLeftEnd);
        
        root.right = buildTreeRecursive(inorder, inorderRightBegin, inorderRightEnd
        , postorder, postorderRightBegin, postorderRightEnd);
        return root;
    }
}

// @lc code=end

