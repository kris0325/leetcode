import java.util.HashMap;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder
 * traversal of a binary tree and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * <p>
 * <p>
 * Related Topics Array Hash Table Divide and Conquer Tree Binary Tree 👍 15099 👎
 * 513
 */
       
/*
 2024-08-22 17:22:30
*/

class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
    class Solution1 {
        // TC :o(n2); 會出現 Compile Error: java.lang.StackOverflowError

        //先序遍歷數組的每一個節點都作為根節點，用來計算該節點在中序遍歷的index，從而計算左子樹的length = index-preStartIndex，
        //然後在中序數組中能 區分左子樹，右子樹，
        // 再按先序遍歷遞歸構建子樹

        //1. 遍歷preorder的每一個節點，以該節點作為根節點，
        // 2.取inorder中尋找中序遍歷的該根結節點的index，
        // 這樣可以將中序數組切分為：左字數+ 根節點 + 右子樹
        //3.在移動preorder到下一個節點，繼續遞歸先序遍歷左-中-右構建樹

        //定義全局遍歷，方便helper函數調用
        int[] preorder;
        int[] inorder;
        int N;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;
            N = inorder.length;
            return helper(0, 0, N - 1);
        }

        //preStartIndex：先序遍歷的開始index ，
        // inStartIndex：中序遍歷的開始index, 注意：左子樹的inStartIndex 始終以0開始
        // inEndIndex:中序遍歷的結束index， 注意：右子樹的inEndIndex 始終以開始N-1結尾
        private TreeNode helper(int preStartIndex, int inStartIndex, int inEndIndex) {
            if (inStartIndex > inEndIndex) {
                return null;
            }
            //每一輪取出前序遍歷數組的節點 作為根節點，然後去中序數組中選擇index，計算左子樹長度
            TreeNode root = new TreeNode(preorder[preStartIndex]);
            int rootIndexOfInOrder = 0;

            /*
            * for loop 選擇根節點，會導致StackOverflowError
            **/
            for (int i = inStartIndex; i < inEndIndex; i++) {
                if (root.val == inorder[i]) {
                    rootIndexOfInOrder = i;
                    break;
                }
            }
            int leftTreeLength = rootIndexOfInOrder - inStartIndex;

            //左子樹的preStartIndex：需要跳過根節點，所以每次遞歸preStartIndex+1
            //注意：左子樹的inStartIndex 左邊界始終以0開始,所以inStartIndex不用更新
            //左子樹的inEndIndex 右邊界就是根節點rootIndexOfInOrder-1
            root.left = helper(preStartIndex + 1, inStartIndex, rootIndexOfInOrder - 1);

            //右子樹的preStartIndex：需要跳過左子樹+根節點，所以每次遞歸preStartIndex+leftTreeLength+1
            //右子樹的inStartIndex 左邊界以rootIndexOfInOrder+1開始
            //右子樹的inStartIndex 右邊界始終以0開始,所以inStartIndex不用更新
            root.right = helper(preStartIndex + leftTreeLength + 1, rootIndexOfInOrder + 1, inEndIndex);
            return root;
        }
    }

    class Solution {
        //尋找根結點在 中序數組的index ，不使用for loop, 優化為使用hashmap ,
        // TC :o(n);
        //先序遍歷數組的每一個節點都作為根節點，用來計算該節點在中序遍歷的index，從而計算左子樹的length = index-preStartIndex，
        //然後在中序數組中能 區分左子樹，右子樹，
        // 再按先序遍歷遞歸構建子樹
        int[] preorder;
        int[] inorder;
        int N;
        HashMap<Integer,Integer> nodeIndexToInOrder = new HashMap<>();
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            for (int i = 0;i<inorder.length; i++){
                nodeIndexToInOrder.put(inorder[i],i);
            }
            this.preorder = preorder;
            this.inorder = inorder;
            N = inorder.length;
            return helper(0, 0, N - 1);
        }

        private TreeNode helper(int preStartIndex, int inStartIndex, int inEndIndex) {
            if (inStartIndex > inEndIndex) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[preStartIndex]);
            int rootIndexOfInOrder = 0;
            //優化計算根節點index o(1)
            rootIndexOfInOrder = nodeIndexToInOrder.get(root.val);
            int leftTreeLength = rootIndexOfInOrder - inStartIndex;
            root.left = helper(preStartIndex + 1, inStartIndex, rootIndexOfInOrder - 1);
            root.right = helper(preStartIndex + leftTreeLength + 1, rootIndexOfInOrder + 1, inEndIndex);
            return root;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}