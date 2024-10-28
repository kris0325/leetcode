import java.util.*;

/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the same or
 * another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work. You
 * just need to ensure that a binary tree can be serialized to a string and this
 * string can be deserialized to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes
 * a binary tree. You do not necessarily need to follow this format, so please be
 * creative and come up with different approaches yourself.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree is in the range [0, 10⁴].
 * -1000 <= Node.val <= 1000
 * <p>
 * <p>
 * Related Topics String Tree Depth-First Search Breadth-First Search Design
 * Binary Tree 👍 10218 👎 398
 */
       
/*
 2024-08-21 10:27:31
*/

class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        Solution solution = new SerializeAndDeserializeBinaryTree().new Solution();
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
        //preorder traversal
        //在 Java 和 C# 等语言中，字符串是不可变的（immutable）。这意味着每次对字符串进行修改（例如使用 + 运算符连接字符串）时，都会创建一个新的字符串对象。原有的字符串对象保持不变，新的字符串对象会在内存中分配新的空间。这种行为导致了频繁的内存分配和垃圾回收，影响性能。
        public String serialize1(TreeNode root) {
            if (root == null) {
                return "#";
            }
            return root.val + "," + serialize(root.left) + "," + serialize(root.right);
        }

        //性能優化版
        public String serialize(TreeNode root) {
            if (root == null) {
                return "#";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(root.val);
            sb.append(",").append(serialize(root.left));
            sb.append(",").append(serialize(root.right));
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return helper(queue);
        }

        private TreeNode helper(Queue<String> queue) {
            String s = queue.poll();
            if (Objects.equals(s, "#")) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(s));
            root.left = helper(queue);
            root.right = helper(queue);
            return root;
        }
    }

    public class Solution {
        //428.Serialize and Deserialize N-ary Tree
        class TreeNode {
            int val;
            List<TreeNode> children;

            public TreeNode(int val, List<TreeNode> children) {
                this.val = val;
                this.children = children;
            }
        }

        public String serialize(TreeNode root) {
            List<String> res = new ArrayList<>();
            dfs(root, res);
            return String.join(",", res);
        }

        private void dfs(TreeNode root, List<String> res) {
            if (root == null) {
                return;
            }
            //把當前節點值，與子節點數作為pair存入隊列：root.val, root.children.size pair對存入隊列
            res.add(String.valueOf(root.val));
            res.add(String.valueOf(root.children.size()));
            for (TreeNode child : root.children) {
                dfs(child, res);
            }
        }

        public TreeNode deserialize(String data) {
            if(data.isEmpty()) {return null;}
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return dfs(queue);
        }

        private TreeNode dfs(Queue<String> queue){
            //第一次poll為 節點val
            TreeNode root = new TreeNode(Integer.valueOf(queue.poll()), new ArrayList<>());
            //第一次poll為 節點children的數量
            int size = Integer.valueOf(queue.poll());
            for (int i = 0; i < size; i++) {
                root.children.add(dfs(queue));
            }
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}