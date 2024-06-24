/*
 * @lc app=leetcode id=429 lang=java
 *
 * [429] N-ary Tree Level Order Traversal
 *
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/description/
 *
 * algorithms
 * Medium (70.84%)
 * Likes:    3607
 * Dislikes: 137
 * Total Accepted:    308.2K
 * Total Submissions: 434.9K
 * Testcase Example:  '[1,null,3,2,4,null,5,6]'
 *
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * 
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value (See examples).
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: root =
 * [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 * 
 * 
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    // 思路：queue实现测序遍历BFS
        List<List<Integer>> result = new ArrayList<>();
        public List<List<Integer>> levelOrder(Node root) {
            if(null == root) {
                return result;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                //收集当前level的节点元素
                List<Integer> list = new ArrayList<>();
                //记录当前节点数量
                int len = queue.size();
                //遍历当前level
                for(int i = 0; i < len;i++){
                    Node node = queue.poll();  
                    list.add(node.val);
                    for(int j=0 ; j<node.children.size();j++){
                        if(node.children.get(j) != null){
                            queue.offer(node.children.get(j));
                        }
                    }
                }
                result.add(list);
            }
            return result;
    }

    
}
// @lc code=end

