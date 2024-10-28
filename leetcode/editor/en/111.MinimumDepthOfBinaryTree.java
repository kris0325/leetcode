import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root
 * node down to the nearest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree is in the range [0, 10⁵].
 * -1000 <= Node.val <= 1000
 * <p>
 * <p>
 * Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 7312
 * 👎 1312
 */
       
/*
 2024-08-17 17:18:12
 Minimum Depth of Binary Tree
Category	Difficulty	Likes	Dislikes
algorithms	Easy (47.84%)	7261	1309

*/

class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MinimumDepthOfBinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

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
    class Solution1 {
        //BFS Lever order traversal + queue
        public int minDepth(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            if (root == null) return 0;
            queue.offer(root);
            int depth = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    //第一次找到Leaf node，即找到minDepth
                    if (cur.left == null && cur.right == null) {
                        return depth;
                    }
                    //按照層序遍歷，從左-右的順序將節點入隊列
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }
                depth++;
            }
            return depth;
        }
    }

    class Solution {
        //BFS recessive
        int minDepth = 0;
        int leftMinDepth = 0;
        int rightMinDepth = 0;

        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            if (root.left != null) {
                leftMinDepth = minDepth(root.left);
            }
            if (root.right != null) {
                rightMinDepth = minDepth(root.right);
            }
            return Math.min(leftMinDepth, rightMinDepth) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}