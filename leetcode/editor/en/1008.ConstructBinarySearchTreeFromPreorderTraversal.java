/**
 * Given an array of integers preorder, which represents the preorder traversal of
 * a BST (i.e., binary search tree), construct the tree and return its root.
 * <p>
 * It is guaranteed that there is always possible to find a binary search tree
 * with the given requirements for the given test cases.
 * <p>
 * A binary search tree is a binary tree where for every node, any descendant of
 * Node.left has a value strictly less than Node.val, and any descendant of Node.
 * right has a value strictly greater than Node.val.
 * <p>
 * A preorder traversal of a binary tree displays the value of the node first,
 * then traverses Node.left, then traverses Node.right.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: preorder = [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: preorder = [1,3]
 * Output: [1,null,3]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= preorder.length <= 100
 * 1 <= preorder[i] <= 1000
 * All the values of preorder are unique.
 * <p>
 * <p>
 * Related Topics Array Stack Tree Binary Search Tree Monotonic Stack Binary Tree
 * 👍 6182 👎 83
 */
       
/*
 2024-08-22 01:16:19
*/

class ConstructBinarySearchTreeFromPreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinarySearchTreeFromPreorderTraversal().new Solution();
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
        //BST與preorder性質，子樹都是單調遞增
        int index = 0;

        public TreeNode bstFromPreorder(int[] preorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            return bstFromPreorderHelper(preorder, Integer.MAX_VALUE);
        }

        public TreeNode bstFromPreorderHelper(int[] preorder, Integer bound) {
            //終止條件
            if (index == preorder.length || preorder[index] > bound) {
                return null;
            }
            //前序遍歷順序訪問
            TreeNode root = new TreeNode(preorder[index++]);
            //子樹都是單調遞增的性質
            root.left = bstFromPreorderHelper(preorder, root.val);
            root.right = bstFromPreorderHelper(preorder, bound);
            return root;
        }
    }

    class Solution {
        //普通模板BST建樹
        int index = 0;
        int[] preorder;
        int N;
        public TreeNode bstFromPreorder(int[] preorder) {
            N = preorder.length;
            this.preorder = preorder;
            return bstFromPreorderHelper(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        public TreeNode bstFromPreorderHelper(Integer lower, Integer upper) {
            if (index == N) {
                return null;
            }
            int val = preorder[index];
            //val不在[lower,upper]區間，當前值不屬於當前子樹，遞歸返回上一層，
            if(val < lower || val > upper){
                return null;
            }
            //val在[lower,upper]區間
            index++;
            //按照preorder 遍歷順序 構建樹
            TreeNode root = new TreeNode(val);
            root.left = bstFromPreorderHelper(lower, val);
            root.right = bstFromPreorderHelper(val, upper);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}