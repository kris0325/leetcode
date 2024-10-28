/**
 * Given the root of a binary search tree and the lowest and highest boundaries as
 * low and high, trim the tree so that all its elements lies in [low, high].
 * Trimming the tree should not change the relative structure of the elements that will
 * remain in the tree (i.e., any node's descendant should remain a descendant). It
 * can be proven that there is a unique answer.
 * <p>
 * Return the root of the trimmed binary search tree. Note that the root may
 * change depending on the given bounds.
 * <p>
 * Trim a Binary Search Tree
 * Category	Difficulty	Likes	Dislikes
 * algorithms	Medium (66.25%)	5821	258
 * Tags
 * tree
 * <p>
 * Companies
 * bloomberg
 * <p>
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.
 * <p>
 * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,0,2], low = 1, high = 2
 * Output: [1,null,2]
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 * Output: [3,2,null,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 104
 * The value of each node in the tree is unique.
 * root is guaranteed to be a valid binary search tree.
 * 0 <= low <= high <= 104
 */
       
/*
 2024-06-29 17:53:51
*/

class TrimABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new TrimABinarySearchTree().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
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
        //solution:
        //1. terminate condition :cur == null,  leaf node 's child is null, return null

        //every loop
        //2. cur.val is match [low, high], no need to handle, jump to step 5.
        //3. cur.cal < low,   root = trimBST(cur.right) , 删除当前节点，右子节点上移动（上移子节点相当于删除BSF的单前节点）
        //4. cur.val > high,  root = trimBST(cur.left) , 删除当前节点，左子节点上移

        //5 recursive  handle left  tree, handle right tree, cur.let = trimBST(cur.left),cur.right = trimBST(cur.right)
        //6. return root

        public TreeNode trimBST(TreeNode root, int low, int high) {
            // node is leaf
            if (root == null) return null;

            // current loop
            if (root.val < low) {
                //即删除当前节点，将当前节点的右节点上移动，赋值给上一层父节点
                TreeNode right = trimBST(root.right, low, high);
                return right;
            } else if (root.val > high) {
                //即删除当前节点，将当前节点的左节点上移动，赋值给上一层父节点
                TreeNode left = trimBST(root.left, low, high);
                return root;
            }
            // cur.val is match [low, high] no need to handle current
            // so go to handle left tree and right tree
            // left tree
            root.left = trimBST(root.left, low, high);
            // right tree
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}