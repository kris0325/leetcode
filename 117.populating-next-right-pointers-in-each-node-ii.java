/*
 * @lc app=leetcode id=117 lang=java
 *
 * [117] Populating Next Right Pointers in Each Node II
 *
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
 *
 * algorithms
 * Medium (52.46%)
 * Likes:    5820
 * Dislikes: 321
 * Total Accepted:    640.9K
 * Total Submissions: 1.2M
 * Testcase Example:  '[1,2,3,4,5,null,7]'
 *
 * Given a binary tree
 * 
 * 
 * struct Node {
 * ⁠ int val;
 * ⁠ Node *left;
 * ⁠ Node *right;
 * ⁠ Node *next;
 * }
 * 
 * 
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should
 * populate each next pointer to point to its next right node, just like in
 * Figure B. The serialized output is in level order as connected by the next
 * pointers, with '#' signifying the end of each level.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = []
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
 * 
 * 
 * 
 * Follow-up:
 * 
 * 
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not
 * count as extra space for this problem.
 * 
 * 
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        List<Integer> result = new ArrayList<>();
        if(null == root){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int leveLength = queue.size();
            // 1.get head node of each level
               Node curNode = queue.poll();
                if(null != curNode.left){
                    queue.add(curNode.left);
                }
                if(null != curNode.right){
                    queue.add(curNode.right);
                }
                //travesel each level from second node
                for(int i = 1; i< leveLength; i++ ){
                    Node nextNode = queue.poll();
                    if(null != nextNode.left){
                        queue.add(nextNode.left);
                    }
                    if(null != nextNode.right){
                        queue.add(nextNode.right);
                    }
                    curNode.next = nextNode;
                    //move curNode to the next node
                    curNode = nextNode;
                }
           // List<Node> currentNodes = new ArrayList<>();
            // for(int i = 0; i< leveLength; i++){
            //     Node node = queue.poll();
            //     node.next = 
            //     currentNodes.add(node);
            //     if(null != node.left){
            //         queue.add(node.left);
            //     }
            //     if(null != node.right){
            //         queue.add(node.right);
            //     }
            // }  
            // for(int i=0 ;i<leveLength-1; i++){
            //     currentNodes.get(i).next = currentNodes.get(i+1);
            // }  
        }
        return root;
}
}
// @lc code=end

