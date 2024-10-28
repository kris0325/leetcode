import java.util.HashMap;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder
 * traversal of a binary tree and postorder is the postorder traversal of the same
 * tree, construct and return the binary tree.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 * <p>
 * <p>
 * Related Topics Array Hash Table Divide and Conquer Tree Binary Tree 👍 8060 👎
 * 132
 */
       
/*
 2024-08-23 16:52:56
*/

class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        //1.取postorder數組末尾節點為根節點
        //2.找到在inorder數組根節點index，計算左子樹長度
        //3.得到左子樹，右子樹，遞歸遍歷
        int[] inorder;
        int[] postorder;
        int N;
        HashMap<Integer, Integer> rootIndexToInorder;

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            this.inorder = inorder;
            this.postorder = postorder;
            N = postorder.length;
            rootIndexToInorder = new HashMap<>();
            for (int i = 0; i < N; i++) {
                rootIndexToInorder.put(inorder[i], i);
            }
            return helpler(0, N - 1, 0, N - 1);

        }

        private TreeNode helpler(int inorderStarIndex, int inorderEndIndex, int postorderStarIndex, int postorderEndIndex) {
            if (inorderStarIndex > inorderEndIndex) {
                return null;
            }
            //找到中序遍歷的根節點index
            int rootIndexInorder = rootIndexToInorder.get(postorder[postorderEndIndex]);
            TreeNode root = new TreeNode(inorder[rootIndexInorder]);
            //左子樹長度
            int leftLengh = rootIndexInorder - inorderStarIndex;
            //右子樹長度

//            root.left = helpler(inorderStarIndex, rootIndexInorder - 1,
//                    postorderStarIndex, postorderStarIndex + leftLengh-1);
//            root.right = helpler(rootIndexInorder + 1, inorderEndIndex,
//                    postorderStarIndex + leftLengh, postorderEndIndex - 1);

            int righLengh = inorderEndIndex - rootIndexInorder;
            root.left = helpler(inorderStarIndex, rootIndexInorder - 1, postorderStarIndex,
                    postorderEndIndex - righLengh - 1);
            root.right = helpler(rootIndexInorder + 1, inorderEndIndex,
                    postorderEndIndex - righLengh, postorderEndIndex - 1);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}