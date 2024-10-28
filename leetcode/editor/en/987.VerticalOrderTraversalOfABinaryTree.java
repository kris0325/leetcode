import com.sun.source.tree.BreakTree;

import java.util.*;

/**
 * Given the root of a binary tree, calculate the vertical order traversal of the
 * binary tree.
 * <p>
 * For each node at position (row, col), its left and right children will be at
 * positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the
 * tree is at (0, 0).
 * <p>
 * The vertical order traversal of a binary tree is a list of top-to-bottom
 * orderings for each column index starting from the leftmost column and ending on the
 * rightmost column. There may be multiple nodes in the same row and same column. In
 * such a case, sort these nodes by their values.
 * <p>
 * Return the vertical order traversal of the binary tree.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 * 1 is at the top, so it comes first.
 * 5 and 6 are at the same position (2, 0), so we order them by their
 * value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location
 * and should be ordered by their values.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
 * <p>
 * <p>
 * Related Topics Hash Table Tree Depth-First Search Breadth-First Search Sorting
 * Binary Tree 👍 7655 👎 4334
 */
       
/*
 2024-08-20 20:21:18
 Vertical Order Traversal of a Binary Tree
Category	Difficulty	Likes	Dislikes
algorithms	Hard (47.43%)	7655	4334
Tags
array

Companies
Unknown
*/

class VerticalOrderTraversalOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new VerticalOrderTraversalOfABinaryTree().new Solution();
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
    class Solution {
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            //hashmap 將節點按列分組存儲
            Map<Integer, List<int[]>> colToNode = new HashMap<>();
            //nodeToPosition 存儲節點的列位置， colToNode 与nodeToPosition 通过列index关联
            Map<TreeNode, Integer> nodeToPosition = new HashMap<>();
            Queue<TreeNode> queue = new LinkedList<>();
            List<List<Integer>> res = new ArrayList<>();
            if (root != null) {
                queue.add(root);
            }
            //初始化根節點的列index
            nodeToPosition.put(root, 0);
            int minColIndex = 0;
            int depth = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    Integer colIndex = nodeToPosition.get(cur);
                    colToNode.computeIfAbsent(colIndex, x -> new ArrayList<>()).add(new int[]{depth, cur.val});
                    //節點入隊列，更新列index，並更新節點列位置
                    if (cur.left != null) {
                        queue.offer(cur.left);
                        nodeToPosition.put(cur.left, colIndex - 1);

                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                        nodeToPosition.put(cur.right, colIndex + 1);

                    }
                    minColIndex = Math.min(minColIndex, colIndex);
                }
                depth++;
            }
            while (colToNode.containsKey(minColIndex)) {
                List<int[]> sameColToNodes = colToNode.get(minColIndex);
                //同一層按val大小升序排序，否則不同層按depth層的深度升序排序
                Collections.sort(sameColToNodes, (node1, node2) -> {
                    if (node1[0] == node2[0]) return node1[1] - node2[1];
                    return node1[0] - node2[0];
                });
                List<Integer> vals = new ArrayList<>();
                for (int[] sameColToNode : sameColToNodes) {
                    vals.add(sameColToNode[1]);
                }
                res.add(vals);
                minColIndex++;
            }
            return res;
        }
    }

    /*
    * 同類型題目：314.verticalOrder 314.按垂直方向遍歷tree，重合節點從左到右遍歷
    * */

    class Solution314 {
        //BFS遍歷
        public List<List<Integer>> verticalOrder(BinaryTreeZigzagLevelOrderTraversal.TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            Queue<BinaryTreeZigzagLevelOrderTraversal.TreeNode> queue = new LinkedList<>();
            //將節點按列分組：每一列的所有節點值映射
            Map<Integer, List<Integer>> colToNodes = new HashMap<>();
            //node的position映射 即column的對應的index（這裡為方便標記，規定root column 為0，左--， 右++）
            Map<BinaryTreeZigzagLevelOrderTraversal.TreeNode, Integer> nodePosition = new HashMap<>();
            if (null != root) {
                queue.offer(root);
            }
            nodePosition.put(root, 0);
            //紀錄最左邊的列的index，便於left-right遍歷
            int minColIndex = 0;
            while (!queue.isEmpty()) {
                BinaryTreeZigzagLevelOrderTraversal.TreeNode cur = queue.poll();
                //找到cur 節點對應的column，並紀錄節點值
                Integer curColIndex = nodePosition.get(cur);
                colToNodes.computeIfAbsent(curColIndex, x -> new ArrayList<>()).add(cur.val);
                //紀錄下層子節點的列位置，定義根節點index為0，左邊-1，右邊+1
                if (cur.left != null) {
                    queue.offer(cur.left);
                    nodePosition.put(cur.left, curColIndex - 1);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    nodePosition.put(cur.right, curColIndex + 1);
                }
                //更新最左邊的列的index
                minColIndex = Math.min(minColIndex, curColIndex);
            }
            //很巧妙：因為key是 列對應的index 是連續的，使用minColIndex++，從左到右遍歷，避免了sort
            while (colToNodes.containsKey(minColIndex)) {
                res.add(colToNodes.get(minColIndex++));
            }
            return res;
        }
    }

    class Solution314_2 {
        //314.verticalOrder 314.按垂直方向遍歷tree，重合節點從左到右遍歷
        //DFS遍歷
        public List<List<Integer>> verticalOrder(BinaryTreeZigzagLevelOrderTraversal.TreeNode root) {
            //將節點按列分組：colToNode 是一个 Map<Integer, List<int[]>>，其中 key 是列索引，value 是一个包含节点深度和节点值的数组列表
            Map<Integer, List<int[]>> colToNode = new TreeMap<>();
            dfsHelper(root, 0, 0, colToNode);
            List<List<Integer>> res = new ArrayList<>();
            //遍歷每一列，按照depth排序，即從上到下verticalOrde遍歷

            // 遍歷colToNode 是按照key升序遍歷values嗎？ hashmap不保證順序，所以colToNode使用TreeMap ?
            for (List<int[]> nodes : colToNode.values()) {
                //按照depth升序排序,
                Collections.sort(nodes, (node1, node2) -> node1[0] - node2[0]);
                //收集當前列的node節點value
                List<Integer> curColTONodesValue = new LinkedList<>();
                for (int[] node : nodes) {
                    curColTONodesValue.add(node[1]);
                }
                res.add(curColTONodesValue);
            }
            return res;
        }

        public void dfsHelper(BinaryTreeZigzagLevelOrderTraversal.TreeNode root, int depth, int offset, Map<Integer, List<int[]>> colToNode) {
            if (null == root) {
                return;
            }
            if (!colToNode.containsKey(offset)) {
                colToNode.put(offset, new LinkedList<>());
            }
            //colToNode的節點list中int[]裡存儲節點的depth, node.val
            colToNode.get(offset).add(new int[]{depth, root.val});
            dfsHelper(root.left, depth + 1, offset - 1, colToNode);
            dfsHelper(root.right, depth + 1, offset + 1, colToNode);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}