/**
 * You are given the root node of a binary search tree (BST) and a value to insert
 * into the tree. Return the root node of the BST after the insertion. It is
 * guaranteed that the new value does not exist in the original BST.
 * <p>
 * Notice that there may exist multiple valid ways for the insertion, as long as
 * the tree remains a BST after insertion. You can return any of them.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 * Explanation: Another accepted tree is:
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [40,20,60,10,30,50,70], val = 25
 * Output: [40,20,60,10,30,50,70,null,null,25]
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * Output: [4,2,7,1,3,5]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree will be in the range [0, 10⁴].
 * -10⁸ <= Node.val <= 10⁸
 * All the values Node.val are unique.
 * -10⁸ <= val <= 10⁸
 * It's guaranteed that val does not exist in the original BST.
 * <p>
 * <p>
 * Related Topics Tree Binary Search Tree Binary Tree 👍 5798 👎 173
 */


class InsertIntoABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new InsertIntoABinarySearchTree().new Solution();
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
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution1 {
        //solution：recursive
        public TreeNode insertIntoBST(TreeNode root, int val) {
            // node == null, find the right place to insert,
            // assign new TreeNode(val) to node( parent's left or parent's left}
            if (root == null) {
                return new TreeNode(val);
            }
            //assign new TreeNode(val) to  parent's left
            if (root.val > val) {
                root.left = insertIntoBST(root.left, val);
            }

            //assign new TreeNode(val) to parent's right
            if (root.val < val) {
                root.right = insertIntoBST(root.right, val);
            }
            return root;
        }
    }

    class Solution {
        //solution：iterative with two pointer (parent , current)
        // parent point
        TreeNode parent;
        // current point
        TreeNode current;
        public TreeNode insertIntoBST(TreeNode root, int val) {
            parent = root;
            current = root;
            while (current != null) {
                if (current.val > val) {
                    parent = current;
                    current = current.left;
                } else {
                    parent = current;
                    current = current.right;
                }
            }
            //when current == null, find the place to insert new node
            if(parent.val > val){
                parent.left= new TreeNode(val);
            } else {
                parent.right= new TreeNode(val);
            }
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}