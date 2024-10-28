import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such
 * that every key of the original BST is changed to the original key plus the sum
 * of all keys greater than the original key in BST.
 * <p>
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 * <p>
 * <p>
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's
 * key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [0,null,1]
 * Output: [1,null,1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree is in the range [0, 10⁴].
 * -10⁴ <= Node.val <= 10⁴
 * All the values in the tree are unique.
 * root is guaranteed to be a valid binary search tree.
 * <p>
 * <p>
 * <p>
 * Note: This question is the same as 1038: https://leetcode.com/problems/binary-
 * search-tree-to-greater-sum-tree/
 * <p>
 * Related Topics Tree Depth-First Search Binary Search Tree Binary Tree 👍 5228 ?
 * ? 175
 */
       
/*
Convert BST to Greater Tree
Category	Difficulty	Likes	Dislikes
algorithms	Medium (68.98%)	5228	175
Tags
tree

Companies
amazon

 2024-06-29 23:22:42
*/

class ConvertBstToGreaterTree {
    public static void main(String[] args) {
        Solution solution = new ConvertBstToGreaterTree().new Solution();
        // 创建节点
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);

        // 连接节点
        node3.left = node2;
        node3.right = node4;
        node2.left = node1;

        // 根节点
        TreeNode root = node3;
        solution.convertBST(root);
        System.out.println(root);
    }

    public static class TreeNode {
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

    class Solution0 {
        //二叉搜索树，换一个角度来看,就是一个有序数组[2, 5, 13]，求从后到前的累加数组，也就是[20, 18, 13]，是不是感觉这就简单了。
        //反中序遍历二叉搜索树即可
        //递归实现
        int sum = 0;

        public TreeNode convertBST(TreeNode root) {
            traversal(root);
            return root;
        }

        public void traversal(TreeNode root) {
            if (root == null) {
                return;
            }
            traversal(root.right);
            sum += root.val;
            root.val = sum;
            traversal(root.left);
        }
    }

    class Solution {
        //DFS iteraion統一迭代法
        public TreeNode convertBST(TreeNode root) {
            int pre = 0;
            Stack<TreeNode> stack = new Stack<>();
            if(root == null) //edge case check
                return null;

            stack.add(root);

            while(!stack.isEmpty()){
                TreeNode curr = stack.peek();
                //curr != null的狀況，只負責存node到stack中
                if(curr != null){
                    stack.pop();
                    if(curr.left != null)       //左
                        stack.add(curr.left);
                    stack.add(curr);            //中
                    stack.add(null);
                    if(curr.right != null)      //右
                        stack.add(curr.right);
                }else{
                    //curr == null的狀況，只負責做單層邏輯
                    stack.pop();
                    TreeNode temp = stack.pop();
                    temp.val += pre;
                    pre = temp.val;
                }
            }
            return root;
        }
    }



    class Solution1 {
        //1.inorder traversal BST to get BST list
        //2.traversal list to get hashmap<key, sum(all orther keys)>key2sum
        //3.inorder traversal BST and key2sum to convertBST
        public TreeNode convertBST(TreeNode root) {
            if (root == null) {
                return null;
            }
            LinkedList<Integer> list = new LinkedList<>();
            traversal(root, list);
            HashMap<Integer, Integer> key2sum = new HashMap<>();
            for (int i = 0; i < list.size() - 1; i++) {
                key2sum.put(list.get(i), list.subList(i + 1, list.size()).stream().mapToInt(Integer::intValue).sum());
            }
            traversal(root, key2sum);
            return root;
        }

        public void traversal(TreeNode root, HashMap<Integer, Integer> key2sum) {
            if (root == null) {
                return;
            }
            traversal(root.left, key2sum);
            root.val = root.val + key2sum.getOrDefault(root.val, 0);
            traversal(root.right, key2sum);
        }

        public void traversal(TreeNode root, LinkedList<Integer> list) {
            if (root == null) return;
            traversal(root.left, list);
            list.add(root.val);
            traversal(root.right, list);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}