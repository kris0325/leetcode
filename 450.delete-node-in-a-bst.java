/*
 * @lc app=leetcode id=450 lang=java
 *
 * [450] Delete Node in a BST
 *
 * https://leetcode.com/problems/delete-node-in-a-bst/description/
 *
 * algorithms
 * Medium (51.20%)
 * Likes:    9042
 * Dislikes: 276
 * Total Accepted:    518.7K
 * Total Submissions: 1M
 * Testcase Example:  '[5,3,6,2,4,null,7]\n3'
 *
 * Given a root node reference of a BST and a key, delete the node with the
 * given key in the BST. Return the root node reference (possibly updated) of
 * the BST.
 * 
 * Basically, the deletion can be divided into two stages:
 * 
 * 
 * Search for a node to remove.
 * If the node is found, delete the node.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and
 * delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's
 * also accepted.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [], key = 0
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -10^5 <= key <= 10^5
 * 
 * 
 * 
 * Follow up: Could you solve it with time complexity O(height of tree)?
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    //solution:1.
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode parrent = null;
        TreeNode current = root;
        while (current != null) {
            if (current.val < key){
                parrent = current;
                current = current.right;
            } else if (current.val > key) {
                parrent = current;
                current = current.left;
            }else {
                break;
            }

            //以下代码有BUG: 全部情况只有==，>, <3种case， 每种情况是互斥的，但是每个if之间少了else，执行完一种case判断后，应该直接跳到while（current != null）开头进行判断，
            // 但少了else，
            // 就继续往下执行了，所以是有问题的，而且 parrent没有及时更新，而且可能出现 null空指针异常，
            // test case [5,3,8,null，null,6,10], key == 7的情况，是会报null空指针异常的
//                if (current.val == key) {
//                    // find the key node
//                    break;
//                }
//                parrent = current;
//                if (current.val < key) {
//                    current = current.right;
//                }
//                if (current.val > key) {
//                    current = current.left;
//                }
        }
        //BSF只有一个节点
        if (parrent == null) {
            return deletOneNode(current);
        }
        // key 在 left
        if (parrent.left != null && key == parrent.left.val) {
            parrent.left = deletOneNode(current);
        }
        //key 在 right
        if (parrent.right != null && key == parrent.right.val) {
            parrent.right = deletOneNode(current);
        }
        //包含了  没找BSF does not contain key,此时  ,current == null jump out while loop
//        if(current == null){
//            return root;
//        }
        return root;
    }

    //delete  node current
    //删除目标根节点（要删除的节点），如果目标根节点的左，右子树都不为null時，根据BSF的特性
    // 1. 将目标节点的左子树，放在右子树的左子树的最左边的叶子节点的左孩子位置上
    // 2. 将目标节点的右孩子作为新的根节点返回
    public TreeNode deletOneNode(TreeNode target) {
        if (target == null) {
            return null;
        }
//            if (target.left == null) {
//                return target.right;
//            }
        if (target.right == null) {
            return target.left;
        }
        //左，右节点都不为空
        TreeNode cur = target.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        cur.left = target.left;
        return target.right;
    }
}
// @lc code=end

