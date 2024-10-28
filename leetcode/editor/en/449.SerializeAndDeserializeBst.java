import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a
 * network connection link to be reconstructed later in the same or another computer
 * environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary search tree. There
 * is no restriction on how your serialization/deserialization algorithm should work.
 * You need to ensure that a binary search tree can be serialized to a string,
 * and this string can be deserialized to the original tree structure.
 * <p>
 * The encoded string should be as compact as possible.
 * <p>
 * <p>
 * Example 1:
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 * <p>
 * Example 2:
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree is in the range [0, 10⁴].
 * 0 <= Node.val <= 10⁴
 * The input tree is guaranteed to be a binary search tree.
 * <p>
 * <p>
 * Related Topics String Tree Depth-First Search Breadth-First Search Design
 * Binary Search Tree Binary Tree 👍 3490 👎 171
 */
       
/*
 2024-08-21 17:24:06
*/

class SerializeAndDeserializeBst {
    public static void main(String[] args) {
//        Solution solution = new SerializeAndDeserializeBst().new Solution();
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
     * TreeNode(int x) { val = x; }
     * }
     */
    public class Codec {
        // Encodes a tree to a single string.
        // The encoded string should be as compact as possible. 題目要求encoded緊湊，即不能存null節點
        //preorder的方式遍歷字符串
        public String serialize(TreeNode root) {
            if (root == null) return "";
            StringBuilder sb = new StringBuilder();
            sb.append(root.val);
            if (root.left != null) sb.append(",").append(serialize(root.left));
            if (root.right != null) sb.append(",").append(serialize(root.right));
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        //利用BST特地deserialize
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return deserializeHelper(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);

        }

        private TreeNode deserializeHelper(Queue<String> queue, int left, int right) {
            if (queue.isEmpty()) return null;
            // 先查看队列头部的值 这里必须使用queue.peek()，不能用queue.poll()，
            //因為比如當前在遍歷root.left左子樹，當queue.peek()不滿足左字樹值的區間（即下一個值不屬於左子樹），因此不能立刻移除，
            // 而是需要return null 退出當前左子樹的遞歸，然後進入右子樹的遞歸，作為下一輪遍歷右子樹的節點，
            Integer val = Integer.valueOf(queue.peek());
            if (val < left || val > right) return null;
            // 从队列中移除当前值
            queue.poll();
            TreeNode root = new TreeNode(val);
            root.left = deserializeHelper(queue, left, val);
            root.right = deserializeHelper(queue, val, right);
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
//leetcode submit region end(Prohibit modification and deletion)

}